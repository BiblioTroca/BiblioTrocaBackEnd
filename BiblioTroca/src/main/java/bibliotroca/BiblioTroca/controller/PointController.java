package bibliotroca.BiblioTroca.controller;
  
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import bibliotroca.BiblioTroca.dto.PointDTO;
import bibliotroca.BiblioTroca.entity.Point; 
import bibliotroca.BiblioTroca.exception.EmailNotFoundException;
import bibliotroca.BiblioTroca.exception.InsuficientPointsException;
import bibliotroca.BiblioTroca.exception.PointAlreadyCreatedException;
import bibliotroca.BiblioTroca.service.PointService; 
	  
@RestController
@RequestMapping("/api/v1/bibliotroca/pontos")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/{email}/criar")
    public PointDTO createPoint(@PathVariable String email) throws EmailNotFoundException, PointAlreadyCreatedException {
    	Point point = this.pointService.createPoint(email);
        return PointDTO.returnPointDTO(point);
    }
    
    @GetMapping("/{email}")
    public PointDTO getPoints(@PathVariable String email) throws EmailNotFoundException, PointAlreadyCreatedException {
        if(!this.pointService.existsByUserCpf(email)) {
        	return this.createPoint(email);
        }
    	Point point = pointService.returnPointsByEmail(email);       
        this.pointService.verifyLoggedToday(point);
        PointDTO pointDTO = new PointDTO(point.getWalletPoints());
        return pointDTO;        
    }

    @GetMapping("/{email}/adicionar/{pontos}")
    public PointDTO addPoints(@PathVariable("email") String email, @PathVariable("pontos") String points) throws EmailNotFoundException {
        Point pointRequest = pointService.addPoints(Integer.parseInt(points), email);
        if (pointRequest == null) {
        	throw new EmailNotFoundException(email);
        }
        return PointDTO.returnPointDTO(pointRequest);
    }

    @GetMapping("/{email}/remover/{pontos}")
    public PointDTO deducePoints(@PathVariable("email") String email, @PathVariable("pontos") String points) throws EmailNotFoundException, InsuficientPointsException {
    	Point pointRequest = pointService.deducePoints(Integer.parseInt(points), email);
        if (pointRequest == null) {
        	throw new EmailNotFoundException(email);
        }
        return PointDTO.returnPointDTO(pointRequest);
        
    }
}
 