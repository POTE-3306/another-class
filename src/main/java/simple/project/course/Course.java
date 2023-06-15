package simple.project.course;

import java.time.LocalDateTime;

public class Course {
    Integer id;
    String uuid;
    String name;
    String logo_url;
    String description;
    Integer admin_id;
    Integer code;
    LocalDateTime limit_time;
    Integer lecture_dats;
    boolean in_activate;

    public Course() {
    }

    public Course(int id, String uuid, String name, String logo_url, String description, int admin_id, int code, LocalDateTime limit_time, int lecture_dats, boolean in_activate) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.logo_url = logo_url;
        this.description = description;
        this.admin_id = admin_id;
        this.code = code;
        this.limit_time = limit_time;
        this.lecture_dats = lecture_dats;
        this.in_activate = in_activate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDateTime getLimit_time() {
        return limit_time;
    }

    public void setLimit_time(LocalDateTime limit_time) {
        this.limit_time = limit_time;
    }

    public int getLecture_dats() {
        return lecture_dats;
    }

    public void setLecture_dats(int lecture_dats) {
        this.lecture_dats = lecture_dats;
    }

    public boolean isIn_activate() {
        return in_activate;
    }

    public void setIn_activate(boolean in_activate) {
        this.in_activate = in_activate;
    }
}