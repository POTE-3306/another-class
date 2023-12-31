package simple.project.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.project.registration.RegistrationService;
import simple.project.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class CourseController {
    private final CourseService courseService;
    private final RegistrationService registrationService;

    @Autowired
    public CourseController(CourseService courseService, RegistrationService registrationService) {
        this.courseService = courseService;
        this.registrationService = registrationService;
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

    @PostMapping("/course/modify-class")
    public String modifyClass(
            HttpServletRequest request,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("courseId") String courseId,
            Model model
    ) {
        User user = (User) request.getAttribute("user");
        if (!user.isAdmin()) {
            return "index";
        }
        courseService.modifyClass(courseId, title, content);
        return "redirect:/lecture/" + courseId + "/manage";
    }


    @GetMapping("/course/makeCoursePlan/{courseId}")
    public String makeCoursePlan(@PathVariable int courseId, Model model) {
        model.addAttribute("courseId", courseId);
        return "class/makeCoursePlan2";
    }

}
