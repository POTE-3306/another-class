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

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {
    private final RegistrationService registrationService;
    private final CourseService courseService;
    private final UserService userService;
    private final JWToken jwToken;


    public RegistrationController(RegistrationService registrationService, CourseService courseService, UserService userService, JWToken jwToken) {
        this.registrationService = registrationService;
        this.courseService = courseService;
        this.userService = userService;
        this.jwToken = jwToken;
    }

    @RequestMapping("register/{uuid}")
    public ResponseEntity<?> someMethod5(@PathVariable String uuid,
                                         HttpServletResponse response,
                                         HttpSession session) {
        String token = (String) session.getAttribute("token");
        if (token == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            if (user == null) {
                return ResponseEntity.badRequest().build();
            }
            int courseId = courseService.findId(uuid);
            if (courseId == 0) {
                return ResponseEntity.badRequest().build();
            }
            registrationService.register(user.getId(), courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
