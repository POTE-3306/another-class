package simple.project.attendance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import simple.project.course.CourseController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AttendacneController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final AttendanceService attendanceService;

    @Autowired
    public AttendacneController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    @GetMapping("/{courseId}/atend")
    public String getAllAttend(@PathVariable int courseId, Model model){
        HashMap<Integer, ArrayList<String>> atendMap = attendanceService.findAllAttendance(courseId);
        model.addAttribute("atendMap", atendMap);
        return "/class/attend";
    }
}