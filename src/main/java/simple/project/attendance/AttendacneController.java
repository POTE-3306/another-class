package simple.project.attendance;

import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simple.project.course.Course;
import simple.project.course.CourseController;
import simple.project.course.CourseService;
import simple.project.user.JWToken;
import simple.project.user.User;
import simple.project.user.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("lecture")
public class AttendacneController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final AttendanceService attendanceService;
    private final AttendanceRepository attendanceRepository;
    private final UserService userService;
    private final CourseService courseService;
    private final JWToken jwToken;

    @Autowired
    public AttendacneController(AttendanceService attendanceService, AttendanceRepository attendanceRepository, UserService userService, CourseService courseService, JWToken jwToken) {
        this.attendanceService = attendanceService;
        this.attendanceRepository = attendanceRepository;
        this.userService = userService;
        this.courseService = courseService;
        this.jwToken = jwToken;
    }

    @GetMapping("{classId}/attend")
    public String getAllAttend(
            HttpSession session,
            @PathVariable("classId") int classId,
            Model model
    ){
        String token = (String) session.getAttribute("token");
        if (token == null){
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
//            List<User> isAttendUserList = attendanceRepository.findCurAttendanceByCourseId(classId);
//            List<Integer> isAttendUserIds = isAttendUserList.stream().map(User::getId).collect(Collectors.toList());
//            List<User> notAttendUserList = attendanceRepository.findNotAttendanceByCourseId(classId, isAttendUserIds);
            Course course = courseService.getCourseById(classId);
            if (user == null) {
                return "index";
            }
            model.addAttribute("user", user);
//            model.addAttribute("isAttendUserList", isAttendUserList);
//            model.addAttribute("notAttendUserList", notAttendUserList);
            model.addAttribute("course", course);
        } catch (Exception e){
            e.printStackTrace();
            return "index";
        }
        return "/class/attend";
    }
    @PostMapping("{classId}/attend/createAttend")
    public String createAttend(
            HttpSession session,
            @RequestParam("code") String code,
            @PathVariable("classId") int classId,
            Model model
    ){
        System.out.println("createAttend" + code);
        String token = (String) session.getAttribute("token");
        if (token == null){
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);
            List<User> isAttendUserList = attendanceRepository.findCurAttendanceByCourseId(classId);
            List<Integer> isAttendUserIds = isAttendUserList.stream().map(User::getId).collect(Collectors.toList());
//            List<User> notAttendUserList = attendanceRepository.findNotAttendanceByCourseId(classId, isAttendUserIds);
            if(user.isAdmin()){
                courseService.insertCode(code, classId);
            } else{
                LocalDateTime currTime = LocalDateTime.now();
                LocalDateTime limitTime = courseService.getLimitTimeById(classId);
                if (currTime.isAfter(limitTime)){
                    System.out.println("expired");
                }
                else {
                    attendanceService.updateAttendence(classId, user.getId());
                    System.out.println("업데이트 완료");
                }
            }
            Course course = courseService.getCourseById(classId);
            if (user == null) {
                return "index";
            }
            model.addAttribute("user", user);
            model.addAttribute("isAttendUserIds", isAttendUserIds);
//            model.addAttribute("notAttendUserList", notAttendUserList);
            model.addAttribute("course", course);
        } catch (Exception e){
            e.printStackTrace();
            return "index";
        }
        return String.format("redirect:/lecture/%d/attend", classId);

    }

}
