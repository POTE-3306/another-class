package simple.project.lecture;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.project.courseplan.CoursePlan;
import simple.project.courseplan.CoursePlanService;
import simple.project.user.JWToken;
import simple.project.user.User;
import simple.project.user.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("class")
public class lectureController {
    private final UserService userService;
    private final CoursePlanService coursePlanService;
    private final JWToken jwToken;


    @Autowired
    public lectureController(UserService userService, CoursePlanService coursePlanService, JWToken jwToken){
        this.userService = userService;
        this.coursePlanService = coursePlanService;
        this.jwToken = jwToken;
    }

    @GetMapping("/test/{class_id}")
    public String someMethod(
            Model model,
            @PathVariable("class_id") String classId,
            HttpSession session
    ){
        User user = new User();
        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "login/main";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            user = userService.getUserByToken(claims);
        }catch (Exception e ){
            e.printStackTrace();
        }

        List<CoursePlan> coursePlanList = coursePlanService.getCoursePlanList(Integer.parseInt(classId));

        model.addAttribute("classId", classId);
        model.addAttribute("userInfo", user);
        model.addAttribute("plans", coursePlanList);

        System.out.println(classId);
        System.out.println(user);
        System.out.println(coursePlanList);

        return "class/mainClass";
    }
}
