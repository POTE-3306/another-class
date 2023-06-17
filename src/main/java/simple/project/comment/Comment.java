package simple.project.comment;

import java.time.LocalDateTime;

public class Comment {
    int id;
    int postId;
    int authorId;
    String content;
    LocalDateTime postTime;

    public Comment() {
    }

    public Comment(int id, int postId, int authorId, String content, LocalDateTime postTime) {
        this.id = id;
        this.postId = postId;
        this.authorId = authorId;
        this.content = content;
        this.postTime = postTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", content='" + content + '\'' +
                ", postTime=" + postTime +
                '}';
    }
}
