package simple.project.registration;

public class Registration {
    int id;
    String uuid;
    String name;
    String logoUrl;
    String description;
    int adminId;
    int code;
    String limitTime;
    int lectureDays;
    boolean isActive;

    public Registration(int id, String uuid, String name, String logoUrl, String description, int adminId, int code, String limitTime, int lectureDays, boolean isActive) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.logoUrl = logoUrl;
        this.description = description;
        this.adminId = adminId;
        this.code = code;
        this.limitTime = limitTime;
        this.lectureDays = lectureDays;
        this.isActive = isActive;
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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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

    public String getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(String limitTime) {
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

    public void setActive(boolean active) {
        isActive = active;
    }
}
