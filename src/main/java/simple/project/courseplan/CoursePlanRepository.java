package simple.project.courseplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoursePlanRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CoursePlanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<CoursePlan> getRowMapper(){
        return (rs, rowNum) -> new CoursePlan(
                rs.getInt("id"),
                rs.getInt("course_id"),
                rs.getString("title"),
                rs.getString("description")
        );
    }

    public void insert(CoursePlan coursePlan){
        String sql = "INSERT INTO courseplans (course_id, title, description) VALUES (?,?,?)";
        jdbcTemplate.update(sql, coursePlan.getCourseId(), coursePlan.getTitle(), coursePlan.getDescription());
    }

    public CoursePlan findByCourseId(int courseId){
        String query = String.format("select * from CoursePlans where course_id=%d", courseId);
        CoursePlan coursePlan = jdbcTemplate.queryForObject(query, getRowMapper());
        return coursePlan;

    }
}
