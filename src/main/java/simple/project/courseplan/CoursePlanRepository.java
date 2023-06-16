package simple.project.courseplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoursePlanRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CoursePlanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(CoursePlan coursePlan){
        String sql = "INSERT INTO courseplans (course_id, title, description) VALUES (?,?,?)";
        jdbcTemplate.update(sql, coursePlan.getCourseId(), coursePlan.getTitle(), coursePlan.getDescription());
    }
    public List<CoursePlan> getCoursePlanList(int courseId) {
        String sql = "SELECT ID, TITLE, DESCRIPTION FROM COURSEPLANS WHERE COURSE_ID = ?";
        return jdbcTemplate.query(sql, new Object[]{courseId}, (rs, rowNum) -> {
            CoursePlan coursePlan = new CoursePlan();
            coursePlan.setId(rs.getInt("ID"));
            coursePlan.setTitle(rs.getString("TITLE"));
            coursePlan.setDescription(rs.getString("DESCRIPTION"));
            return coursePlan;
        });
    }
}
