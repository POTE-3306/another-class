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
    public String makeClassPage(Model model,HttpSession session) {
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
        return "/makeClass";
    }

    @PostMapping("/course/insert-class")
    public String makeClass(
            HttpSession session,
            @RequestParam("image") MultipartFile image,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("userId") int adminId,
            Model model
    ) {
        User user = new User();
        String token = (String) session.getAttribute("token");
        if (token == null) {
            return "index";
        }
        try{
            Claims claims = jwToken.getClaims(token);
            user = userService.getUserByToken(claims);
            if (user == null || !user.isAdmin()) {
                return "index";
            }
        } catch (Exception e){
            e.printStackTrace();
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

        // Call the service method passing the Course instance
        courseService.makeClass(course);
        int courseId = courseService.findId(uuid);
        registrationService.adminRegister(user.getId(), courseId);

        model.addAttribute("courseId", courseId);
        return "/makeCoursePlan";
    }


    @GetMapping("/course/makeCoursePlan")
    public String makeCoursePlan(@RequestParam("courseId") int courseId, Model model) {
        // Retrieve the course details based on the courseId
        Course course = courseService.getCourseById(courseId);

        // Pass the course object to the makeCoursePlan.jsp view
        model.addAttribute("course", course);

        // Return the makeCoursePlan.jsp view
        return "makeCoursePlan";
    }

}
