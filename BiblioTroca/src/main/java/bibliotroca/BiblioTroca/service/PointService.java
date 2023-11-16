package bibliotroca.BiblioTroca.service;
   
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import bibliotroca.BiblioTroca.entity.Point;
import bibliotroca.BiblioTroca.exception.InsuficientPointsException;
import bibliotroca.BiblioTroca.repository.PointRepository;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

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
    	point.increasePointsIfLoggedToday();
    }
}
