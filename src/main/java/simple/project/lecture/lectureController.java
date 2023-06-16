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
import simple.project.course.Course;
import simple.project.course.CourseService;
import simple.project.courseplan.CoursePlan;
import simple.project.courseplan.CoursePlanService;
import simple.project.post.PostService;
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
    private final PostService postService;

    @Autowired
    public lectureController(JWToken jwToken, UserService userService, CoursePlanService coursePlanService, CourseService courseService, PostService postService) {
        this.jwToken = jwToken;
        this.userService = userService;
        this.coursePlanService = coursePlanService;
        this.courseService = courseService;
        this.postService = postService;
    }


    @RequestMapping("{class_id}")
    public String mainClass(
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

    @RequestMapping("{class_id}/community")
    public String communityPage(
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


            if (user == null) {
                return "login/main";
            }
            model.addAttribute("user", user);

        } catch (Exception e){
            e.printStackTrace();
            return "login/main";
        }
        return "redirect:class/community/notice";
    }
    @RequestMapping("{class_id}/community/notice")
    public String communityNoticePage(
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


            if (user == null) {
                return "login/main";
            }
            model.addAttribute("user", user);

        } catch (Exception e){
            e.printStackTrace();
            return "login/main";
        }
        return "class/community";
    }
    @RequestMapping("{class_id}/community/task")
    public String communityTaskPage(
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


            if (user == null) {
                return "login/main";
            }
            model.addAttribute("user", user);

        } catch (Exception e){
            e.printStackTrace();
            return "login/main";
        }
        return "class/community";
    }
}
