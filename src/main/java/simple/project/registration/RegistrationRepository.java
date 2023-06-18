package simple.project.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import simple.project.course.Course;

import java.util.ArrayList;
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

    public boolean findRegistration(int userId, int courseId) {
        String query = String.format("SELECT * FROM Registrations where user_id=%d and course_id=%d", userId, courseId);
        List<Registration> registrationList = jdbcTemplate.query(query, getRowMapper());
        return registrationList.isEmpty();
    }

    public void register(int userId, int courseId) {
        jdbcTemplate.update("INSERT INTO Registrations (user_id, course_id, is_approved) VALUES (?, ?,?)", userId, courseId,0);
    }

    public void adminRegister(int userId, int courseId) {
        jdbcTemplate.update("INSERT INTO Registrations (user_id, course_id, is_approved) VALUES (?, ?, ?)", userId, courseId, true);
    }

    public List<Registration> findRegByCourseId(int course_id) {
        String sql = "SELECT * FROM Registrations WHERE course_id = ? AND is_approved = 0;";
        List<Registration> reglist = jdbcTemplate.query(sql, getRowMapper(), course_id);
        if (reglist.isEmpty()) {
            return new ArrayList<>();
        } else
            return reglist;
    }

    public void updateReg(int regId) {
        String sql = "UPDATE Registrations SET is_approved =1 WHERE id = ?";
        jdbcTemplate.update(sql, regId);
    }

    public void deleteReg(int regId) {
        String sql = "DELETE FROM Registrations WHERE id=?";
        jdbcTemplate.update(sql, regId);
    }

    public List<Registration> findAttendListByCourseId(int course_id) {
        String sql = "SELECT * FROM Registrations WHERE is_approved =1 AND course_id=?";
        List<Registration> attendList = jdbcTemplate.query(sql, getRowMapper(), course_id);
        return attendList;
    }


}
