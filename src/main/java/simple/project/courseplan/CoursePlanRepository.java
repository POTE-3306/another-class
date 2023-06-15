package simple.project.courseplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
