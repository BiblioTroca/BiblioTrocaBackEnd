package bibliotroca.BiblioTroca.controller;
  
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController;
import bibliotroca.BiblioTroca.dto.PointDTO;
import bibliotroca.BiblioTroca.entity.Point; 
import bibliotroca.BiblioTroca.exception.CpfNotFoundException;
import bibliotroca.BiblioTroca.exception.InsuficientPointsException;
import bibliotroca.BiblioTroca.exception.PointAlreadyCreatedException;
import bibliotroca.BiblioTroca.service.PointService; 
	  
@RestController
@RequestMapping("/api/v1/bibliotroca/pontos")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/{cpf}/criar")
    public PointDTO createPoint(@PathVariable String cpf) throws CpfNotFoundException, PointAlreadyCreatedException {
    	Point point = this.pointService.createPoint(cpf);
        return PointDTO.returnPointDTO(point);
    }
    
    @GetMapping("/{cpf}")
    public PointDTO getPoints(@PathVariable String cpf) throws CpfNotFoundException, PointAlreadyCreatedException {
        if(!this.pointService.existsByUserCpf(cpf)) {
        	return this.createPoint(cpf);
        }
    	Point point = pointService.returnPointsByCpf(cpf);       
        this.pointService.verifyLoggedToday(point);
        PointDTO pointDTO = new PointDTO(point.getWalletPoints());
        return pointDTO;        
    }

    @GetMapping("/{cpf}/adicionar/{pontos}")
    public PointDTO addPoints(@PathVariable("cpf") String cpf, @PathVariable("pontos") String points) throws CpfNotFoundException {
        Point pointRequest = pointService.addPoints(Integer.parseInt(points), cpf);
        if (pointRequest == null) {
        	throw new CpfNotFoundException(cpf);
        }
        return PointDTO.returnPointDTO(pointRequest);
    }

    @GetMapping("/{cpf}/remover/{pontos}")
    public PointDTO deducePoints(@PathVariable("cpf") String cpf, @PathVariable("pontos") String points) throws CpfNotFoundException, InsuficientPointsException {
    	Point pointRequest = pointService.deducePoints(Integer.parseInt(points), cpf);
        if (pointRequest == null) {
        	throw new CpfNotFoundException(cpf);
        }
        return PointDTO.returnPointDTO(pointRequest);
        
    }
}
 