package simple.project.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Post> findByUserIdOrderByPostTime(int userId){
        String query = String.format("select * from Posts where author_id=%d order by post_time desc limit 5", userId);
        List<Post> userPosts = jdbcTemplate.query(query, getRowMapper());
        return userPosts;
    }

    public ArrayList<Integer> findPostsByAuthor(int authorId) {
        String sql = "SELECT Posts.id " +
                "FROM Posts " +
                "JOIN Users u ON u.id = Posts.author_id " +
                "WHERE Posts.author_id = ? " +
                "LIMIT 5";

        ArrayList<Integer> postList = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, authorId);

        for (Map<String, Object> row : rows) {
            int postId = (int) row.get("id");
            postList.add(postId);
        }

        return postList;
    }

    public List<HashMap<Integer, ArrayList<String>>> getPostList(ArrayList<Integer> postlist) {
        String sql = "SELECT Posts.id, title, COUNT(c.post_id) AS 댓글수 " +
                "FROM Posts " +
                "JOIN Comments c ON Posts.id = c.post_id " +
                "JOIN Users u ON u.id = c.author_id " +
                "WHERE Posts.id = ? " +
                "GROUP BY Posts.id, title, c.post_id";

        List<HashMap<Integer, ArrayList<String>>> postList = new ArrayList<>();

        for (Integer postId : postlist) {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, postId);
            HashMap<Integer, ArrayList<String>> postMap = new HashMap<>();

            for (Map<String, Object> row : rows) {
                String title = (String) row.get("title");
                int commentCount = ((Number) row.get("댓글수")).intValue();
                ArrayList<String> postInfo = new ArrayList<>();
                postInfo.add(title);
                postInfo.add(String.valueOf(commentCount));

                postMap.put(postId, postInfo);
            }

            postList.add(postMap);
        }

        return postList;
    }
    public List<Post> findByClassIdAndBoardType(int classId, int boardType){
        String query = String.format("select * from Posts where course_id=%d and board_type=%d", classId, boardType);
        List<Post> classPostList = jdbcTemplate.query(query, getRowMapper());
        return classPostList;
    }


}
