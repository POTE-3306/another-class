package simple.project.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import simple.project.user.User;

import java.util.UUID;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public class CourseRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Course> findAll() {
        String query = "SELECT * FROM Courses";
        List<Course> courseList = jdbcTemplate.query(query, getRowMapper());
        return courseList;
    }

    private RowMapper<Course> getRowMapper() {
        return (rs, rowNum) -> new Course(
                rs.getInt("id"),
                rs.getString("uuid"),
                rs.getString("name"),
                rs.getString("logo_url"),
                rs.getString("description"),
                rs.getInt("admin_id"),
                rs.getInt("code"),
                Objects.isNull(rs.getTimestamp("limit_time")) ? LocalDateTime.now() : rs.getTimestamp("limit_time").toLocalDateTime(),
                rs.getInt("lecture_days"),
                rs.getBoolean("is_active")
        );
    }

    public List<Course> findByAdminId(int admin_id){
        String query = String.format("SELECT * FROM Courses where admin_id=%d", admin_id);
        List<Course> courseList = jdbcTemplate.query(query, getRowMapper());
        return courseList;
    }
    public List<Course> findByUserId(int userId) {
        String query = String.format("select B.id id, B.uuid uuid, B.name name, B.logo_url logo_url, B.description description, B.admin_id admin_id, B.code code, B.limit_time limit_time, B.lecture_days lecture_days, B.is_active is_active from Registrations A join Courses B on A.course_id=B.id where A.user_id=%d and A.is_approved = true;", userId);
        List<Course> userCourse = jdbcTemplate.query(query, getRowMapper());
        return userCourse;
    }

    public void insert(Course course) {
        String sql = "INSERT INTO Courses (uuid, logo_url, name, description,admin_id) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, course.getUuid(), course.getLogo_url(), course.getName(), course.getDescription(), course.getAdmin_id());
    }

    public int findId(String uuid) {
        String sql = "SELECT id FROM Courses WHERE uuid = ?";
        Integer id = jdbcTemplate.queryForObject(sql, new Object[]{uuid}, Integer.class);
        return (id != null) ? id : 0;
    }

    public Course getCourseById(int courseId) {
        String sql = String.format("SELECT * FROM Courses WHERE id = %d", courseId);
        List<Course> courses = jdbcTemplate.query(sql, getRowMapper());
        if (courses.isEmpty()) {
            return null; // 결과가 없을 경우 null을 반환하거나 적절한 처리를 수행하세요.
        } else {
            return courses.get(0); // 결과가 있다면 첫 번째 레코드를 반환하도록 처리하세요.
        }
    }
    public void insertCode(String code, int courseId){
        String query = String.format("UPDATE Courses SET code = %s, limit_time=now()+INTERVAL 10 MINUTE, lecture_days=lecture_days+1 WHERE id=%d", code, courseId);
        jdbcTemplate.update(query);
    }
    public LocalDateTime findLimitTimeById(int courseId) {
        String query = "select limit_time from Courses where id=?";
        return jdbcTemplate.queryForObject(query, new Object[]{courseId}, new RowMapper<LocalDateTime>() {
            @Override
            public LocalDateTime mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getObject("limit_time", LocalDateTime.class);
            }
        });
    }
}
