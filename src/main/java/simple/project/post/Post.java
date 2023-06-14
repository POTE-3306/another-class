package simple.project.post;

public class Post {
    int id;
    int userId;
    int courseId;
    boolean isApproved;

    public Post(int id, int userId, int courseId, boolean isApproved) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.isApproved = isApproved;
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

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
