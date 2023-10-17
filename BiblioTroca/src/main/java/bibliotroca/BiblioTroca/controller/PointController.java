package bibliotroca.BiblioTroca.controller;
  
import java.util.List;
  
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseStatus; 
import org.springframework.web.bind.annotation.RestController;

import bibliotroca.BiblioTroca.dto.PointDTO;
import bibliotroca.BiblioTroca.entity.Point; 
import bibliotroca.BiblioTroca.entity.User; 
import bibliotroca.BiblioTroca.service.PointService; 
import jakarta.validation.Valid;
	  
@RestController
@RequestMapping("/api/v1/bibliotroca/pontos")
public class PointController {

    @Autowired
    private PointService pointService;

    @CrossOrigin
    @GetMapping("/{user}")
    public ResponseEntity<PointDTO> getPoints(@PathVariable User user) {
        Point point = pointService.getUserWallet(user);
        if (point != null) {
            PointDTO pointDTO = new PointDTO(point.getWalletPoints(), point.getUser());
            return ResponseEntity.ok(pointDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @PostMapping("/adicionar")
    public ResponseEntity<PointDTO> addPoints(@RequestBody PointDTO pointDTO) {
        Point point = pointService.addPoints(pointDTO.getWalletPoints(), pointDTO.getUser());
        if (point != null) {
            PointDTO responseDTO = new PointDTO(point.getWalletPoints(), point.getUser());
            return ResponseEntity.ok(responseDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @CrossOrigin
    @PostMapping("/deduzir")
    public ResponseEntity<PointDTO> deducePoints(@RequestBody PointDTO pointDTO) {
        Point point = pointService.deducePoints(pointDTO.getWalletPoints(), pointDTO.getUser());
        if (point != null) {
            PointDTO responseDTO = new PointDTO(point.getWalletPoints(), point.getUser());
            return ResponseEntity.ok(responseDTO);
        }
        return ResponseEntity.badRequest().build();
        
    }
}
 