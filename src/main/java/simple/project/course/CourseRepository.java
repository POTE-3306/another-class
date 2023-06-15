package simple.project.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
}
