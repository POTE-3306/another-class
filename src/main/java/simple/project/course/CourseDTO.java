package simple.project.course;

import org.springframework.web.multipart.MultipartFile;

public class CourseDTO {
    MultipartFile image;
    String title;
    String contet;
    String plan;

    public CourseDTO() {
    }

    public CourseDTO(MultipartFile image, String title, String contet, String plan) {
        this.image = image;
        this.title = title;
        this.contet = contet;
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "image=" + image +
                ", title='" + title + '\'' +
                ", contet='" + contet + '\'' +
                ", plan='" + plan + '\'' +
                '}';
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContet(String contet) {
        this.contet = contet;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public MultipartFile getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getContet() {
        return contet;
    }

    public String getPlan() {
        return plan;
    }
}
