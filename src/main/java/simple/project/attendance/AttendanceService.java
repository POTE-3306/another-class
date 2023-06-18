package simple.project.attendance;

import org.springframework.stereotype.Service;
import simple.project.course.CourseService;
import simple.project.user.User;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final CourseService courseService;

    public AttendanceService(AttendanceRepository attendanceRepository, CourseService courseService) {
        this.attendanceRepository = attendanceRepository;
        this.courseService = courseService;
    }

    public HashMap<Integer, ArrayList<String>> findAllAttendance(int courseId) {
        return attendanceRepository.findAllAttendance(courseId);
    }

    public void updateAttendence(int courseId, int userId) {
        attendanceRepository.insertAttendance(courseId, userId);
    }

    public HashMap<Integer, String> attendUserToday(int courseId) {
        return attendanceRepository.getAtendTime(courseId);
    }

    public List<Integer> findUserIdBycourseIdToday(int courseId) {
        List<Attendance> attendanceList = attendanceRepository.findAttendanceByCourseIdToday(courseId);
        List<Integer> userIds = new ArrayList<>();
        for (Attendance attendance :
                attendanceList) {
            userIds.add(attendance.getUserId());
        }
        return userIds;
    }

    public Integer getAtendDays(int courseId, int userId) {
        return attendanceRepository.getAtendDays(courseId, userId);
    }

    public Integer atendRate(int courseId, int userId) {
        Integer num = getAtendDays(courseId, userId);
        Integer div = courseService.getDays(courseId);
        if (div != 0) {
            double ratetemp = (double) num / div;
            System.out.println("ratetemp :" +ratetemp );
            Integer rate = (int) (ratetemp * 100);
            System.out.println("attend rate : " + rate);
            return rate;
        } else {
            return 0;
        }
    }
}
