package bibliotroca.BiblioTroca;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class LoginController {

    @GetMapping("/")
    public String getHomePage(Authentication authentication, Model model){

        DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = user.getAttributes();

        String name = attributes.getOrDefault("localizedFirstName", attributes.get("given_name")).toString();
        String email = attributes.get("email").toString();
        String picture = attributes.get("picture").toString();

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
