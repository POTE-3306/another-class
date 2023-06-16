package simple.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;
    private final NaverAPI naverAPI;
    private final JWToken jwToken;

    @Autowired
    public UserController(UserService userService, NaverAPI naverAPI, JWToken jwToken) {
        this.userService = userService;
        this.naverAPI = naverAPI;
        this.jwToken = jwToken;
    }

    @RequestMapping("callback")
    public String callback(@RequestParam("code") String code,
                              @RequestParam("state") String state,
                              Model model, HttpSession session) {
        String access_token = userService.getAccessToken(code, state);
        if (access_token.equals("")) {
            return "index";
        }
        try {
            APIUserDTO apiUser = naverAPI.getProfile(access_token);
            if (apiUser == null) {
                return "index";
            }
            User user = userService.userSelectByEmailAndNaverId(apiUser);
            if (user == null) {
                model.addAttribute("apiUser", apiUser);
                return "login/signup";
            }
            String token = jwToken.getToken(user);
            session.setAttribute("token", token);
        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "redirect:/post/main";
    }

    @RequestMapping("signup")
    public String signup(HttpSession session,
                              @ModelAttribute User user) {
        user = userService.insertUser(user);
        try {
            String token = jwToken.getToken(user);
            session.setAttribute("token", token);
        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "redirect:/post/main";
    }
}
