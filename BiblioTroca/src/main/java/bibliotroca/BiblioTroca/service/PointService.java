/*
 * package bibliotroca.BiblioTroca.service;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import bibliotroca.BiblioTroca.entity.Point; import
 * bibliotroca.BiblioTroca.entity.User; import
 * bibliotroca.BiblioTroca.repository.PointRepository;
 * 
 * @Service public class PointService {
 * 
 * @Autowired PointRepository pointRepository;
 * 
 * public Point createPoint(Point point) { return
 * this.pointRepository.save(point); }
 * 
 * public List<Point> returnAllPoints() { return this.pointRepository.findAll();
 * }
 * 
 * public Point updatePoints(int walletPoints, Point point, User user) { return
 * this.pointRepository.updatePoints(walletPoints, user); }
 * 
 * }
 */