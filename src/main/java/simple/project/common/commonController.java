package simple.project.common;


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.project.user.JWToken;
import simple.project.user.User;
import simple.project.user.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class commonController {
    private final JWToken jwToken;
    private final UserService userService;

    @Autowired
    public commonController(JWToken jwToken, UserService userService) {
        this.jwToken = jwToken;
        this.userService = userService;
    }

    @RequestMapping("mypage")
    public String myPage(
            HttpSession session,
            Model model
    ){
        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);

            if (user == null) {
                return "index";
            }

            model.addAttribute("user", user);
        } catch (Exception e){
            e.printStackTrace();
            return "index";
        }
        return "mypage";
    }
}
