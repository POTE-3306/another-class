package simple.project.course;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.project.registration.RegistrationService;
import simple.project.user.JWToken;
import simple.project.user.User;
import simple.project.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;
    private final UserService userService;
    private final JWToken jwToken;
    private final RegistrationService registrationService;

    @Autowired
    public CourseController(CourseService courseService, UserService userService, JWToken jwToken, RegistrationService registrationService) {
        this.courseService = courseService;
        this.jwToken= jwToken;
        this.userService =userService;
        this.registrationService = registrationService;
    }

    @RequestMapping("class")
    public String someMethod(HttpSession session, Model model){
//        String token = (String) session.getAttribute("token");
//        if (token == null){
//            return "login/main";
//        }
        int userId = 2;
//        model.addAttribute("userId", userId);
//        List<Course> courseList = courseService.getByUserIdCourse(userId, user.isAdmin);
//        model.addAttribute("courseList", courseList);
        return "classList";
    }
    @GetMapping("/course/make-class")
    public String makeClassPage(Model model, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        model.addAttribute("user", user);
        return "class/makeClass";
    }

    @PostMapping("/course/insert-class")
    public String makeClass(
            HttpServletRequest request,
            @RequestParam("image") MultipartFile image,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("userId") int adminId,
            Model model
    ) {
        User user = (User) request.getAttribute("user");
        if (!user.isAdmin()) {
            return "index";
        }
        Course course = new Course();
        String filePath = courseService.saveImage(image);
        String uuid = UUID.randomUUID().toString();
        course.setUuid(uuid);
        course.setLogo_url(filePath);
        course.setName(title);
        course.setDescription(content);
        course.setAdmin_id(adminId);
        courseService.makeClass(course);
        int courseId = courseService.findId(uuid);
        registrationService.adminRegister(user.getId(), courseId);
        model.addAttribute("courseId", courseId);
        return "class/makeCoursePlan";
    }


    @GetMapping("/course/makeCoursePlan/{courseId}")
    public String makeCoursePlan(@PathVariable int courseId, Model model) {
        model.addAttribute("courseId", courseId);
        return "class/makeCoursePlan2";
    }

}
