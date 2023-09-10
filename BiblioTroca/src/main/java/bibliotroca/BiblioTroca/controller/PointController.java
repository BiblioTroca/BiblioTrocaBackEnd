/*
 * package bibliotroca.BiblioTroca.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.ResponseStatus; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import bibliotroca.BiblioTroca.entity.Point; import
 * bibliotroca.BiblioTroca.entity.User; import
 * bibliotroca.BiblioTroca.service.PointService; import
 * jakarta.validation.Valid;
 * 
 * @RestController
 * 
 * @RequestMapping("/api/v1/bibliotroca/pontos") public class PointController {
 * 
 * @Autowired PointService pointService;
 * 
 * @PostMapping
 * 
 * @ResponseStatus(HttpStatus.CREATED) public Point
 * createPoint(@RequestBody @Valid Point point) { return
 * this.pointService.createPoint(point); }
 * 
 * @GetMapping public List<Point> returnAllPoints() { return
 * this.pointService.returnAllPoints(); }
 * 
 * @PutMapping public Point updatePoints(@PathVariable int walletPoints, Point
 * point, User user) { return this.pointService.updatePoints(walletPoints,
 * point, user); }
 * 
 * }
 */