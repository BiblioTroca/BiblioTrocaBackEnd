package bibliotroca.BiblioTroca.service;
   
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import bibliotroca.BiblioTroca.entity.Point;
import bibliotroca.BiblioTroca.exception.CpfNotFoundException;
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

    public Point returnPointsByCpf(String userCpf) {
    	return this.pointRepository.findByUserCpf(userCpf);
    }
    
    public Point addPoints(int walletPoints, String userCpf) {
        Point userPoints = pointRepository.findByUserCpf(userCpf);
        if (userPoints == null) {
            userPoints = new Point(walletPoints, userCpf);
        } else {
            userPoints.setWalletPoints(userPoints.getWalletPoints() + walletPoints);
        }
        return pointRepository.save(userPoints);
    }

    public Point deducePoints(int walletPoints, String userCpf) throws InsuficientPointsException {
        Point userPoints = pointRepository.findByUserCpf(userCpf);
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

	public boolean existsByUserCpf(String userCpf) {
		return this.pointRepository.existsByUserCpf(userCpf);
	}

	public Point createPoint(String cpf) throws CpfNotFoundException, PointAlreadyCreatedException {
		if(!this.userRepository.existsByCpf(cpf)) {
    		throw new CpfNotFoundException(cpf);
    	}
		if(this.pointRepository.existsByUserCpf(cpf)) {
    		throw new PointAlreadyCreatedException();
    	}
		Point point = new Point(50, cpf);
		return this.pointRepository.save(point);
	}
}
