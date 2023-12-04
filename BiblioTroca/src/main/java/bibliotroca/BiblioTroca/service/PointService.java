package bibliotroca.BiblioTroca.service;
   
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import bibliotroca.BiblioTroca.entity.Point;
import bibliotroca.BiblioTroca.exception.EmailNotFoundException;
import bibliotroca.BiblioTroca.exception.InsuficientPointsException;
import bibliotroca.BiblioTroca.exception.PointAlreadyCreatedException;
import bibliotroca.BiblioTroca.repository.PointRepository;
import bibliotroca.BiblioTroca.repository.UserRepository;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;
    @Autowired
	private UserRepository userRepository;

    public Point returnPointsByEmail(String email) {
    	return this.pointRepository.findByUserEmail(email);
    }
    
    public Point addPoints(int walletPoints, String email) {
        Point userPoints = pointRepository.findByUserEmail(email);
        if (userPoints == null) {
            userPoints = new Point(walletPoints, email);
        } else {
            userPoints.setWalletPoints(userPoints.getWalletPoints() + walletPoints);
        }
        return pointRepository.save(userPoints);
    }

    public Point deducePoints(int walletPoints, String email) throws InsuficientPointsException {
        Point userPoints = pointRepository.findByUserEmail(email);
        if(userPoints == null) {
        	throw new InsuficientPointsException();
        }
        int wallet = userPoints.getWalletPoints() - walletPoints;
        if (wallet >= 0) {
            userPoints.setWalletPoints(wallet);
            return pointRepository.save(userPoints);
        }
        throw new InsuficientPointsException();
    }
    
    public void verifyLoggedToday(Point point) {
    	if(point.increasePointsIfLoggedToday()) {
    		this.pointRepository.save(point);
    	}
    }

	public boolean existsByUserCpf(String email) {
		return this.pointRepository.existsByUserEmail(email);
	}

	public Point createPoint(String email) throws EmailNotFoundException, PointAlreadyCreatedException {
		if(!this.userRepository.existsByEmail(email)) {
    		throw new EmailNotFoundException(email);
    	}
		if(this.pointRepository.existsByUserEmail(email)) {
    		throw new PointAlreadyCreatedException();
    	}
		Point point = new Point(50, email);
		return this.pointRepository.save(point);
	}
}
