package simple.project.course;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping("class")
    public String someMethod(HttpSession session, Model model){
//        String token = (String) session.getAttribute("token");
//        if (token == null){
//            return "login/main";
//        }
        int userId = 2;
//        model.addAttribute("userId", userId);
        List<Course> courseList = courseService.getByUserIdCourse(userId);
        model.addAttribute("courseList", courseList);
        return "classList";
    }
}
