package simple.project.user;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import simple.project.course.Course;
import simple.project.course.CourseService;
import simple.project.post.Post;
import simple.project.post.PostService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final NaverAPI naverAPI;
    private final JWToken jwToken;
    private final PostService postService;
    private final CourseService courseService;

    @Autowired
    public UserController(UserService userService, CourseService courseService, PostService postService, NaverAPI naverAPI, JWToken jwToken) {
        this.postService = postService;
        this.userService = userService;
        this.naverAPI = naverAPI;
        this.jwToken = jwToken;
        this.courseService = courseService;
    }

    @RequestMapping("t1")
    public String someMethod() {
        userService.someMethod();
        return "login/main";
    }

    @RequestMapping("t3")
    public String someMethod2(@RequestParam("code") String code,
                              @RequestParam("state") String state,
                              Model model, HttpSession session) {
        String access_token = userService.getAccessToken(code, state);
        if (access_token.equals("")) {
            return "login/main";
        }
        try {
            APIUserDTO apiUser = naverAPI.getProfile(access_token);
            if (apiUser == null) {
                return "login/main";
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
            return "login/main";
        }
        return "redirect:/t5";
    }

    @RequestMapping("t4")
    public String someMethod3(HttpSession session,
                              @ModelAttribute User user) {
        user = userService.insertUser(user);
        try {
            String token = jwToken.getToken(user);
            session.setAttribute("token", token);
        } catch (Exception e) {
            e.printStackTrace();
            return "login/main";
        }
        return "redirect:/t5";
    }

    @RequestMapping("t5")
    public String someMethod4(HttpSession session, Model model) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "login/main";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            List<Post> postList = postService.getByUserIdPost(user.getId());
            List<Course> courseList = courseService.getByUserIdCourse(user.getId(), user.isAdmin());
            if (user == null) {
                return "login/main";
            }
            model.addAttribute("postList", postList);
            model.addAttribute("user", user);
            model.addAttribute("courseList", courseList);

        } catch (Exception e) {
            e.printStackTrace();
            return "login/main";
        }
        return "main/main";
    }
}
