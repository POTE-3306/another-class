package simple.project.attendance;

import java.time.LocalDateTime;

public class Attendance {
    int id;
    int userId;
    int courseId;
    LocalDateTime attendanceTime;

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", attendanceTime=" + attendanceTime +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setAttendanceTime(LocalDateTime attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public LocalDateTime getAttendanceTime() {
        return attendanceTime;
    }

    public Attendance(int id, int userId, int courseId, LocalDateTime attendanceTime) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.attendanceTime = attendanceTime;
    }

    public Attendance() {
    }
}
