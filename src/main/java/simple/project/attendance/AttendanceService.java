package simple.project.attendance;

import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public HashMap<Integer, ArrayList<String>> findAllAttendance(int courseId){
        return attendanceRepository.findAllAttendance(courseId);
    }

    public HashMap<Integer, String> attendUserToday(int courseId){
        return attendanceRepository.getAtendTime(courseId);
    }

}
