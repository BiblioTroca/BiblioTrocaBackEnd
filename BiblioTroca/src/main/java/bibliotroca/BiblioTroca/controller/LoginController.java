package bibliotroca.BiblioTroca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import bibliotroca.BiblioTroca.dto.UserDTO;
import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.EmailAlreadyInUseException;
import bibliotroca.BiblioTroca.exception.EmailNotFoundException;
import bibliotroca.BiblioTroca.service.LoginService;
import bibliotroca.BiblioTroca.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	UserController userController;
	@Autowired
	LoginService loginService;

    @GetMapping("/")
    public RedirectView getHomePage(Authentication authentication, HttpServletResponse res) {
    	DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = user.getAttributes();
        
        String name = attributes.getOrDefault("localizedFirstName", attributes.get("given_name")).toString();
        String surname = attributes.get("name").toString();
        surname = surname.replace(name + " ", "");
        String email = attributes.get("email").toString();
        String picture = attributes.get("picture").toString();
        
        User newUser = new User(name, surname, email);
        
        if(!this.userService.existsByEmail(email)) {
        	this.userService.createUserLogin(newUser);
        }
        
        String token = loginService.generateToken(newUser, picture);
        
        Cookie tokenCookie = new Cookie("token", token);
        res.addCookie(tokenCookie);
        tokenCookie.setMaxAge(60*60);
        tokenCookie.setDomain("BiblioTroca");
        tokenCookie.setPath("/token");
        
        RedirectView redirect = new RedirectView();
        redirect.setUrl("https://bibliotroca.vercel.app/perfil/completar-perfil");
        return redirect;
    }

    @GetMapping("/login")
    public String getLoginPage(HttpServletResponse res){
    	Cookie tokenCookieRemove = new Cookie("token", "");
    	tokenCookieRemove.setMaxAge(0);
    	res.addCookie(tokenCookieRemove);
        return "login";
    }

    @PostMapping("/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUserFromMobile(@RequestBody User user) throws EmailAlreadyInUseException {
		System.out.print("a " +String.valueOf(user.getEmail().hashCode()));
    	User userCreated = this.userService.createUserLogin(user);
		return loginService.generateToken(userCreated);
    }
    
    @PostMapping("/autenticar")
    public String loginFromMobile(@RequestBody User user) throws EmailAlreadyInUseException, EmailNotFoundException {
        if(!this.userService.existsByEmail(user.getEmail())) {
        	return "";
        }
        if(this.userService.verifyCredentials(user)) {
        	User userRequest = this.userService.returnUserByEmail(user.getEmail());
            String token = loginService.generateToken(userRequest);
            return token;
        }
        return "";
    }
    
    @PostMapping("/alterar-senha")
    public UserDTO updateLoginFromMobile(@RequestBody User user) throws EmailAlreadyInUseException {
		User userUpdated = this.userService.updateUserMobile(user);
		return UserDTO.returnUserDTO(userUpdated);
    }
}
