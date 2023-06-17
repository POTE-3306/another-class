package simple.project.user;

public class UserQuery {
    static final String SELECT_ALL = "select * from Users";
    static final String INSERT_USER = "insert into Users (name, email, age, gender, is_admin, naver_uid) values (?, ?, ?, ?, ?, ?)";
    static final String SELECT_BY_EMAIL_NAVERID = "select * from Users where email = ? and naver_uid = ?";
    static final String SELECT_BY_ID_EMAIL = "select * from Users where id = ? and email = ?";
    static final String SELECT_BY_ID = "select * from Users where id = ?";
    static final String SELECT_Last_ID = "select id from Users order by id desc limit 1";
}