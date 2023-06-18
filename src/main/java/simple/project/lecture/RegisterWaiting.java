package simple.project.lecture;

public class RegisterWaiting {
    int regId;
    int userId;
    String userName;

    @Override
    public String toString() {
        return "RegisterWaiting{" +
                "regId=" + regId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRegId() {
        return regId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public RegisterWaiting() {
    }
}
