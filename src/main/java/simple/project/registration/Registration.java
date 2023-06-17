package simple.project.registration;

public class Registration {
    Integer id;
    Integer userId;
    Integer courseId;
    Boolean isApproved;

    public Registration(Integer id, Integer userId, Integer courseId, Boolean isApproved) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.isApproved = isApproved;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public void setApproved(Boolean approved) {
        isApproved = approved;
    }

}
