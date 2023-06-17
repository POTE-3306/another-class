package simple.project.comment;

import java.time.LocalDateTime;

public class CommentDto {
    String author;
    String content;
    LocalDateTime postTime;

    public CommentDto() {
    }

    public CommentDto( String content, LocalDateTime postTime) {
        this.content = content;
        this.postTime = postTime;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }
}
