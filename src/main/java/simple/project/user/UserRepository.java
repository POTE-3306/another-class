package simple.project.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import simple.project.post.Post;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private User getUser(Map<String, Object> row) {
        User user = new User();
        user.setId((int) row.get("id"));
        user.setName((String) row.get("name"));
        user.setEmail((String) row.get("email"));
        user.setAge((int) row.get("age"));
        user.setAdmin((boolean) row.get("is_admin"));
        user.setGender(row.get("gender").toString().charAt(0));
        user.setNaverId((String) row.get("naver_uid"));
        return user;
    }


    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(UserQuery.SELECT_ALL);

        rows.forEach(row -> userList.add(getUser(row)));
        return userList;
    }

    public User insertUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pst = con.prepareStatement(UserQuery.INSERT_USER, new String[]{"id"});
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setInt(3, user.getAge());
            pst.setString(4, String.valueOf(user.getGender()));
            pst.setBoolean(5, user.isAdmin());
            pst.setString(6, user.getNaverId());
            return pst;
        }, keyHolder);
        user.setId(keyHolder.getKey().intValue());
        return user;
    }

    public User selectUserByIdAndEmail(int id, String email) {
        User user = null;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(UserQuery.SELECT_BY_ID_EMAIL, id, email);
        for (Map<String, Object> row : rows) {
            user = getUser(row);
        }
        return user;
    }

    public User selectUserById(int id) {
        User user = null;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(UserQuery.SELECT_BY_ID, id);
        for (Map<String, Object> row : rows) {
            user = getUser(row);
        }
        return user;
    }

    public int getLastId() {
        int id = 0;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(UserQuery.SELECT_Last_ID);
        for (Map<String, Object> row : rows) {
            id = (int) row.get("id");
        }
        return id;
    }

    public User userSelectByEmailAndNaverId(APIUserDTO apiUser) {
        User user = null;
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(UserQuery.SELECT_BY_EMAIL_NAVERID, apiUser.getEmail(), apiUser.getNaverId());

        for (Map<String, Object> row : rows) {
            user = getUser(row);
        }
        return user;
    }

    public List<User> findAllUser() {
        String sql = "SELECT * FROM Users";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<User> userList = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            User user = getUser(row);
            userList.add(user);
        }
        return userList;

    }



//    public List<User> findCurAttendanceByCourseId(int courseId){
//        String query = String.format("select Users.id AS id, Users.name AS name, Users.email AS email from " +
//                "Attendances join Users on Attendances.user_id = Users.id " +
//                "where course_id=%d AND DATE(Attendances.attendance_time)=curdate();", courseId);
//        List<User> userList = jdbcTemplate.query(query, (rs, rowNum) -> {
//            User user = new User();
//            user.setId(rs.getInt("id"));
//            user.setName(rs.getString("name"));
//            user.setEmail(rs.getString("email"));
//            return user;
//        });
//        return userList;
//    }
//
//    public List<User> findNotAttendanceByCourseId(int courseId, List<Integer> idList){
//        String formattedIdList = idList.stream()
//                .map(Object::toString)
//                .collect(Collectors.joining(", "));
//        String query = String.format("SELECT Users.id AS id, Users.name AS name, Users.email AS email " +
//                "FROM Users " +
//                "JOIN Registrations R ON Users.id = R.user_id " +
//                "WHERE R.course_id = %d AND Users.id NOT IN (%s)", courseId, formattedIdList);
//        return jdbcTemplate.query(query, (rs, rowNum) -> {
//            User user = new User();
//            user.setId(rs.getInt("id"));
//            user.setName(rs.getString("name"));
//            user.setEmail(rs.getString("email"));
//            return user;
//        });
//}
}
