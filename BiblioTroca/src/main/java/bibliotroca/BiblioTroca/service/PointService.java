package bibliotroca.BiblioTroca.service;
  
import java.util.List;
  
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import bibliotroca.BiblioTroca.entity.Book;
import bibliotroca.BiblioTroca.entity.Point; 
import bibliotroca.BiblioTroca.entity.User; 
import bibliotroca.BiblioTroca.repository.PointRepository;
import bibliotroca.BiblioTroca.repository.UserRepository;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    public Point addPoints(int walletPoints, User user) {
        Point userPoints = pointRepository.findByUser(user);
        if (userPoints == null) {
            userPoints = new Point(walletPoints, user);
        } else {
            userPoints.setWalletPoints(userPoints.getWalletPoints() + walletPoints);
        }
        return pointRepository.save(userPoints);
    }

    public Point deducePoints(int walletPoints, User user) {
        Point userPoints = pointRepository.findByUser(user);
        if (userPoints != null) {
            int wallet = userPoints.getWalletPoints() - walletPoints;
            if (wallet >= 0) {
                userPoints.setWalletPoints(wallet);
                return pointRepository.save(userPoints);
            }
        }
        return null; // Não há pontos suficientes para deduzir
    }
    
    public Point getUserWallet(User user) {
		return pointRepository.findByUser(user);
	}
}
