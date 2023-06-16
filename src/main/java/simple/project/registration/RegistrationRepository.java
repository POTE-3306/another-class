package simple.project.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import simple.project.course.Course;

import java.util.List;

@Repository
public class RegistrationRepository {
    private final JdbcTemplate jdbcTemplate;

    private RowMapper<Registration> getRowMapper() {
        return (rs, rowNum) -> new Registration(
                rs.getInt("id"),
                rs.getInt("user_id"),
                rs.getInt("course_id"),
                rs.getBoolean("is_approved")
        );
    }

    @Autowired
    public RegistrationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void register(int userId, int courseId) {
        jdbcTemplate.update("INSERT INTO Registrations (user_id, course_id) VALUES (?, ?)", userId, courseId);
    }
    public void adminRegister(int userId, int courseId) {
        jdbcTemplate.update("INSERT INTO Registrations (user_id, course_id, is_approved) VALUES (?, ?, ?)", userId, courseId, true);
    }
}
