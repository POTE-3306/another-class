package simple.project.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class CourseRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Course course) {
        String sql = "INSERT INTO courses (uuid, logo_url, name, description) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, course.getUuid(), course.getLogoUrl(), course.getName(), course.getDescription());
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
            course.setLogoUrl(rs.getString("logo_url"));
            course.setDescription(rs.getString("description"));
            course.setAdminId(rs.getInt("admin_id"));
            course.setCode(rs.getInt("code"));
            course.setLimitTime(rs.getTimestamp("limit_time"));
            course.setLectureDays(rs.getInt("lecture_days"));
            course.setActive(rs.getBoolean("is_active"));
            return course;
        });
    }

}
