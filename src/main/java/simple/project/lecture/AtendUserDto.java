package simple.project.lecture;

public class AtendUserDto {
    int id;
    int userId;
    int courseId;
    String name;

    @Override
    public String toString() {
        return "AtendUserDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", courseId=" + courseId +
                ", name='" + name + '\'' +
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

    public void setName(String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public AtendUserDto() {
    }
}
