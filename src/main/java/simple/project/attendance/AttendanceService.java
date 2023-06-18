package simple.project.attendance;

import org.springframework.stereotype.Service;
import simple.project.user.User;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public HashMap<Integer, ArrayList<String>> findAllAttendance(int courseId){
        return attendanceRepository.findAllAttendance(courseId);
    }

    public void updateAttendence(int courseId, int userId){
        attendanceRepository.insertAttendance(courseId, userId);
    }

    public HashMap<Integer, String> attendUserToday(int courseId){
        return attendanceRepository.getAtendTime(courseId);
    }
    public List<Integer> findUserIdBycourseIdToday(int courseId){
        List<Attendance> attendanceList = attendanceRepository.findAttendanceByCourseIdToday(courseId);
        List<Integer> userIds = new ArrayList<>();
        for (Attendance attendance :
                attendanceList) {
            userIds.add(attendance.getUserId());
        }
        return userIds;
    }

}
