package simple.project.attendance;

import java.time.LocalDateTime;

public class Attendance {
    int id;
    int postId;
    int authorId;
    String content;
    LocalDateTime commentTime;

    public Attendance() {
    }

    public Attendance(int id, int postId, int authorId, String content, LocalDateTime commentTime) {
        this.id = id;
        this.postId = postId;
        this.authorId = authorId;
        this.content = content;
        this.commentTime = commentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }
}
