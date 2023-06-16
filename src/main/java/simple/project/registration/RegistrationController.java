package simple.project.registration;

import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.project.course.CourseService;
import simple.project.user.JWToken;
import simple.project.user.User;
import simple.project.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final CourseService courseService;


    public RegistrationController(RegistrationService registrationService, CourseService courseService) {
        this.registrationService = registrationService;
        this.courseService = courseService;
    }

    @RequestMapping("{uuid}")
    public ResponseEntity<?> someMethod5(@PathVariable String uuid,
                                         HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        try {
            int courseId = courseService.findId(uuid);
            if (courseId == 0) {
                return ResponseEntity.badRequest().build();
            }
            if (!registrationService.register(user.getId(), courseId)) {
                return ResponseEntity.unprocessableEntity().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
