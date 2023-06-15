package simple.project.user;

public class APIUserDTO {
    String name;
    String email;
    int age;
    char gender;
    String naverId;

    public APIUserDTO(String name, String email, int age, char gender, String naverId) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.naverId = naverId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public String getNaverId() {
        return naverId;
    }
}
