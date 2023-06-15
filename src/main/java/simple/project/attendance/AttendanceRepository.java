package simple.project.attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AttendanceRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AttendanceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 과목에 담긴 모든 학생의 출석 보기
    public HashMap<Integer, ArrayList<String>> findAllAttendance(int courseId) {
        String sql = "SELECT attendances.user_id, u.name AS 학생이름, c.name AS 과목명, COUNT(attendances.user_id) AS 학생별출석수 " +
                "FROM attendances " +
                "JOIN users u ON attendances.user_id = u.id " +
                "JOIN courses c ON c.id = attendances.course_id " +
                "WHERE course_id = ? " +
                "GROUP BY attendances.user_id, u.name, c.name";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, courseId);
        HashMap<Integer, ArrayList<String>> attendanceMap = new HashMap<>();

        for (Map<String, Object> row : rows) {
            Integer userId = (Integer) row.get("user_id");
            String studentName = (String) row.get("학생이름");
            String courseName = (String) row.get("과목명");
            int attendanceCount = ((Number) row.get("학생별출석수")).intValue();

            ArrayList<String> attendanceInfo = new ArrayList<>();
            attendanceInfo.add(studentName);
            attendanceInfo.add(courseName);
            attendanceInfo.add(String.valueOf(attendanceCount));

            attendanceMap.put(userId, attendanceInfo);
        }

        return attendanceMap;
    }
}
