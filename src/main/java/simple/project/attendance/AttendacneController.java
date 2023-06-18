package simple.project.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simple.project.course.Course;
import simple.project.course.CourseService;
import simple.project.user.JWToken;
import simple.project.user.User;
import simple.project.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("lecture")
public class AttendacneController {
    private final AttendanceService attendanceService;
    private final CourseService courseService;

    @Autowired
    public AttendacneController(AttendanceService attendanceService, CourseService courseService) {
        this.attendanceService = attendanceService;
        this.courseService = courseService;
    }

    @GetMapping("{classId}/attend")
    public String getAllAttend(
            HttpServletRequest request,
            @PathVariable("classId") int classId,
            Model model
    ){
        User user = (User) request.getAttribute("user");
        try {
            Course course = courseService.getCourseById(classId);
            List<Integer> userIds = attendanceService.findUserIdBycourseIdToday(classId);
            model.addAttribute("user", user);
            model.addAttribute("attendList", userIds);
            model.addAttribute("course", course);
            model.addAttribute("classId", classId);
        } catch (Exception e){
            e.printStackTrace();
            return "index";
        }
        return "/class/attend";
    }
    @PostMapping("{classId}/attend/createAttend")
    public String createAttend(
            HttpServletRequest request,
            @RequestParam("code") String code,
            @PathVariable("classId") int classId
    ){
        User user = (User) request.getAttribute("user");
        try {
            if (user.isAdmin()){
                courseService.insertCode(code, classId);
            } else {
                LocalDateTime currTime = LocalDateTime.now();
                LocalDateTime limitTime = courseService.getLimitTimeById(classId);
                if (!currTime.isAfter(limitTime)) {
                    attendanceService.updateAttendence(classId, user.getId());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            return "index";
        }
        return "redirect:/lecture/" + classId + "/attend";
    }
}
