package simple.project.lecture;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.project.course.Course;
import simple.project.course.CourseService;
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
    private final JWToken jwToken;
    private final UserService userService;
    private final CoursePlanService coursePlanService;
    private final CourseService courseService;

    @Autowired
    public lectureController(JWToken jwToken, UserService userService, CoursePlanService coursePlanService, CourseService courseService) {
        this.jwToken = jwToken;
        this.userService = userService;
        this.coursePlanService = coursePlanService;
        this.courseService = courseService;
    }


    @RequestMapping("{class_id}")
    public String someMethod(
            HttpSession session,
            Model model,
            @PathVariable("class_id") String classId
    ){
        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "login/main";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            Course course = courseService.getCourseById(Integer.parseInt(classId));
            CoursePlan coursePlan = coursePlanService.getByCourseId(Integer.parseInt(classId));
            if (user == null) {
                return "login/main";
            }
            model.addAttribute("user", user);
            model.addAttribute("course", course);
            model.addAttribute("coursePlan", coursePlan);
            model.addAttribute("classId", classId);
        } catch (Exception e){
            e.printStackTrace();
            return "login/main";
        }
        return "class/mainClass";
    }
}
