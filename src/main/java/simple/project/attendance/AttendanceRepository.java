package simple.project.attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import simple.project.course.Course;
import simple.project.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    public List<User> findCurAttendanceByCourseId(int courseId){
        String query = String.format("select Users.id AS id, Users.name AS name, Users.email AS email from " +
                "Attendances join Users on Attendances.user_id = Users.id " +
                "where course_id=%d AND DATE(Attendances.attendance_time)=curdate();", courseId);
        List<User> userList = jdbcTemplate.query(query, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        });
        return userList;
    }
    private RowMapper<Attendance> getRowMapper() {
        return (rs, rowNum) -> new Attendance(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("course_id"),
                Objects.isNull(rs.getTimestamp("attendance_time")) ? LocalDateTime.now() : rs.getTimestamp("attendance_time").toLocalDateTime()
        );
    }

    public List<User> findNotAttendanceByCourseId(int courseId, List<Integer> idList){
        String formattedIdList = idList.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        String query = String.format("SELECT Users.id AS id, Users.name AS name, Users.email AS email " +
                "FROM Users " +
                "JOIN Registrations R ON Users.id = R.user_id " +
                "WHERE R.course_id = %d AND Users.id NOT IN (%s)", courseId, formattedIdList);
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            return user;
        });
    }

    public void insertAttendance(int courseId, int userId){
        String query = String.format("INSERT INTO Attendances(user_id, course_id) VALUES (%d, %d);",userId, courseId);
        jdbcTemplate.update(query);
    }
    public List<Attendance> findAllAttendance(){
        String query = "select * from Attendances";
        List<Attendance> attendanceList = jdbcTemplate.query(query, getRowMapper());
        return attendanceList;
    }
}
