package simple.project.course;

import java.time.LocalDateTime;

public class Course {
    int id;
    int userId;
    int courseId;
    LocalDateTime attendanceTime;

    public Course(int id, int userId, int courseId, LocalDateTime attendanceTime) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.attendanceTime = attendanceTime;
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

    public LocalDateTime getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(LocalDateTime attendanceTime) {
        this.attendanceTime = attendanceTime;
    }
}
