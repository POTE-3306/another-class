package simple.project.common;


import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.project.attendance.AttendanceService;
import simple.project.course.Course;
import simple.project.course.CourseService;
import simple.project.user.JWToken;
import simple.project.user.User;
import simple.project.user.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class commonController {
    private final JWToken jwToken;
    private final UserService userService;
    private final AttendanceService attendanceService;
    private final CourseService courseService;

    @Autowired
    public commonController(JWToken jwToken, UserService userService, AttendanceService attendanceService, CourseService courseService) {
        this.jwToken = jwToken;
        this.userService = userService;
        this.attendanceService = attendanceService;
        this.courseService = courseService;
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
            List<Course> courseList = courseService.getByUserIdCourse(user.getId());
            HashMap<String, Integer> rateMap = new HashMap<>();
            for (Course course : courseList) {
                Integer rate = attendanceService.atendRate(course.getId(), user.getId());
                rateMap.put(course.getName(), rate);
            }
            model.addAttribute("rate", rateMap);
            model.addAttribute("user", user);

        } catch (Exception e){
            e.printStackTrace();
            return "index";
        }
        return "mypage";
    }
}
