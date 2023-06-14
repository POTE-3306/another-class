package simple.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void someMethod() {
        String sql = "SELECT * FROM users";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        rows.forEach(row -> {
            System.out.println(row);
        });
    }
}
