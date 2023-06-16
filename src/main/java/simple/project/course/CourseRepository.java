package simple.project.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
        String query = String.format("select * from Registrations A join Courses B on A.course_id=B.id where A.user_id=%d;", userId);
        List<Course> userCourse = jdbcTemplate.query(query, getRowMapper());
        return userCourse;
    }

    public void insert(Course course) {
        String sql = "INSERT INTO COURSES (uuid, logo_url, name, description,admin_id) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, course.getUuid(), course.getLogo_url(), course.getName(), course.getDescription(), course.getAdmin_id());
    }

    public int findId(String uuid) {
        String sql = "SELECT id FROM Courses WHERE uuid = ?";
        Integer id = jdbcTemplate.queryForObject(sql, new Object[]{uuid}, Integer.class);
        return (id != null) ? id : 0;
    }

    public Course getCourseById(int courseId) {
        String sql = "SELECT * FROM Courses WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{courseId}, (rs, rowNum) -> {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setUuid(rs.getString("uuid"));
            course.setName(rs.getString("name"));
            course.setLogo_url(rs.getString("logo_url"));
            course.setDescription(rs.getString("description"));
            course.setAdmin_id(rs.getInt("admin_id"));
            course.setCode(rs.getInt("code"));
            course.setLimit_time(rs.getTimestamp("limit_time").toLocalDateTime());
            course.setLecture_days(rs.getInt("lecture_days"));
            course.setIn_activate(rs.getBoolean("is_active"));
            return course;
        });
    }
}
