package simple.project.user;

public class UserQuery {
    static final String SELECT_ALL = "select * from users";
    static final String INSERT_USER = "insert into users (name, email, age, gender, is_admin, naver_uid) values (?, ?, ?, ?, ?, ?)";
    static final String SELECT_BY_EMAIL_NAVERID = "select * from users where email = ? and naver_uid = ?";
    static final String SELECT_BY_ID_EMAIL = "select * from users where id = ? and email = ?";
}