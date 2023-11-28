package bibliotroca.BiblioTroca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import bibliotroca.BiblioTroca.entity.User;
import bibliotroca.BiblioTroca.exception.CpfAlreadyInUseException;
import bibliotroca.BiblioTroca.service.LoginService;
import bibliotroca.BiblioTroca.service.UserService;

import java.util.Map;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	@Autowired
	UserController userController;
	@Autowired
	LoginService loginService;

    @GetMapping("/")
    public String getHomePage(Authentication authentication, Model model) throws CpfAlreadyInUseException{
    	DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = user.getAttributes();
        
        String name = attributes.getOrDefault("localizedFirstName", attributes.get("given_name")).toString();
        String surname = attributes.get("name").toString();
        surname = surname.replace(name + " ", "");
        String email = attributes.get("email").toString();
        String picture = attributes.get("picture").toString();
        
        if(!this.userService.existsByEmail(email)) {
        	User newUser = new User(name, surname, email);
        	this.userService.createUser(newUser);
        }
        User newUser = new User(name, surname, email);
        System.out.print(loginService.generateToken(newUser));
        
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("picture", picture);
        
        
        
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

}
