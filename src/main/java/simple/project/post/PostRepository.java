package simple.project.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Post> getRowMapper(){
        return ((rs, rowNum) -> new Post(
                rs.getInt("id"),
                rs.getInt("course_id"),
                rs.getInt("author_id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("post_time").toLocalDateTime(),
                rs.getString("board_type")
        ));
    }

    public List<Post> findByUserId(int userId){
        String query = String.format("select * from Posts where author_id=%d", userId);
        List<Post> userPosts = jdbcTemplate.query(query, getRowMapper());
        return userPosts;
    }
}
