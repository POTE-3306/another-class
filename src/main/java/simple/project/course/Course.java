package simple.project.course;
import java.util.UUID;
import java.sql.Timestamp;

public class Course {
    private int id;
    private String uuid;
    private String name;
    private String logoUrl;
    private String description;
    private int adminId;
    private int code;
    private Timestamp limitTime;
    private int lectureDays;
    private boolean isActive;

    // Constructors, getters, and setters

    public Course() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Course(String name, String logoUrl, String description, int adminId, int code, Timestamp limitTime, int lectureDays) {
        this();
        this.name = name;
        this.logoUrl = logoUrl;
        this.description = description;
        this.adminId = adminId;
        this.code = code;
        this.limitTime = limitTime;
        this.lectureDays = lectureDays;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", name='" + name + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", description='" + description + '\'' +
                ", adminId=" + adminId +
                ", code=" + code +
                ", limitTime=" + limitTime +
                ", lectureDays=" + lectureDays +
                ", isActive=" + isActive +
                '}';
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Timestamp getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Timestamp limitTime) {
        this.limitTime = limitTime;
    }

    public int getLectureDays() {
        return lectureDays;
    }

    public void setLectureDays(int lectureDays) {
        this.lectureDays = lectureDays;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
