package simple.project.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import simple.project.post.Post;

import java.util.List;

@Repository
public class CommentRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Comment> findByPostId(int postId) {
        String sql = "SELECT * FROM Comments WHERE post_id = ?";
        List<Comment> commentList = jdbcTemplate.query(sql, getCommentRowMapper(), postId);
        return commentList;
    }

    private RowMapper<Comment> getCommentRowMapper() {
        return ((rs, rowNum) -> new Comment(
                rs.getInt("id"),
                rs.getInt("course_id"),
                rs.getInt("author_id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("post_time").toLocalDateTime(),
                rs.getString("board_type")
        ));
    }

}
