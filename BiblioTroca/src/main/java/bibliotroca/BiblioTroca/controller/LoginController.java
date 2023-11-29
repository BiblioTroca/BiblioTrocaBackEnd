package bibliotroca.BiblioTroca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import bibliotroca.BiblioTroca.dto.UserDTO;
import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.CpfAlreadyInUseException;
import bibliotroca.BiblioTroca.service.LoginService;
import bibliotroca.BiblioTroca.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	UserController userController;
	@Autowired
	LoginService loginService;

    @GetMapping("/")
    public String getHomePage(Authentication authentication, HttpServletResponse res) throws CpfAlreadyInUseException{
    	DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = user.getAttributes();
        
        String name = attributes.getOrDefault("localizedFirstName", attributes.get("given_name")).toString();
        String surname = attributes.get("name").toString();
        surname = surname.replace(name + " ", "");
        String email = attributes.get("email").toString();
        String picture = attributes.get("picture").toString();
        
        User newUser = new User(name, surname, email);
        
        if(!this.userService.existsByEmail(email)) {
        	this.userService.createUser(newUser);
        }
        
        String token = loginService.generateToken(newUser, picture);
        
        Cookie tokenCookie = new Cookie("token", token);
        res.addCookie(tokenCookie);
        tokenCookie.setMaxAge(60*60);
        tokenCookie.setDomain("BiblioTroca");
        tokenCookie.setPath("/token");
        
        return "redirect:/api/v1/bibliotroca/livros";
    }

    @GetMapping("/login")
    public String getLoginPage(HttpServletResponse res){
    	Cookie tokenCookieRemove = new Cookie("token", "");
    	tokenCookieRemove.setMaxAge(0);
    	res.addCookie(tokenCookieRemove);
        return "login";
    }

    @PostMapping("/usuarios")
    public String loginFromMobile(@RequestBody User user, HttpServletResponse res) throws CpfAlreadyInUseException {
        if(!this.userService.existsByEmail(user.getEmail())) {
        	return "";
        }
        User userRequest = this.userService.returnUserByEmail(user.getEmail());
        System.out.println(userRequest);
        if(this.userService.verifyCredentials(userRequest)) {
            String token = loginService.generateToken(userRequest);
            return token;
        }
        return "";
    }
    
}
