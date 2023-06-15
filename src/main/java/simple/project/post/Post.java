package simple.project.post;

import java.time.LocalDateTime;

public class Post {
    int id;
    int courseId;
    int userId;
    String title;
    String content;
    LocalDateTime postTime;
    String boardType;

    public Post(int id, int courseId, int userId, String title, String content, LocalDateTime postTime, String boardType) {
        this.id = id;
        this.courseId = courseId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.postTime = postTime;
        this.boardType = boardType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }
}
