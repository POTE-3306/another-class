package simple.project.post;

import java.time.LocalDateTime;

public class PostDto {
    String title;
    String author;
    String content;
    LocalDateTime postTime;
    Integer postId;

    public PostDto() {
    }

    public PostDto(String title, String content, LocalDateTime postTime, Integer postId) {
        this.title = title;
        this.content = content;
        this.postTime = postTime;
        this.postId = postId;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
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
