package simple.project.attendance;

import java.time.LocalDateTime;

public class Attendance {
    int id;
    int userId;
    int courseId;;
    LocalDateTime commentTime;

    public Attendance() {
    }

    public Attendance(int id, int userId, int courseId, LocalDateTime commentTime) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.commentTime = commentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }
}
