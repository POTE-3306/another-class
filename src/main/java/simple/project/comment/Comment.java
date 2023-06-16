package simple.project.comment;

import java.time.LocalDateTime;

public class Comment {
    int id;
    int courseId;
    int authorId;
    String title;
    String content;
    LocalDateTime postTime;
    String boardType;

    public Comment() {
    }

    public Comment(int id, int courseId, int authorId, String title, String content, LocalDateTime postTime, String boardType) {
        this.id = id;
        this.courseId = courseId;
        this.authorId = authorId;
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
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

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", authorId=" + authorId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", postTime=" + postTime +
                ", boardType='" + boardType + '\'' +
                '}';
    }
}
