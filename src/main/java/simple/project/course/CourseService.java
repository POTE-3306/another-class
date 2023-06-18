package simple.project.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ServletContext servletContext;

    @Autowired
    public CourseService(CourseRepository courseRepository, ServletContext servletContext) {
        this.courseRepository = courseRepository;
        this.servletContext = servletContext;
    }

    public void makeClass(Course course) {
        // Call the repository method passing the file path instead of the MultipartFile
        courseRepository.insert(course);
    }

    public void modifyClass(String courseId, String title, String content) {
        courseRepository.update(courseId, title, content);
    }

    public String saveImage(MultipartFile image) {
        final String MY_PROJECT_PATH = servletContext.getRealPath("/");
        String fileName = null;
        try {
            // Generate a unique file name or use the original file name as needed
            fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            String uploadDir = MY_PROJECT_PATH + "resources/main/assets/image"; // Set the appropriate directory path

            // Create the directory if it doesn't exist
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the image file to the specified directory
            String filePath = uploadDir + "/" + fileName;
            image.transferTo(new File(filePath));
            // Set the file path to be stored in the database
            String imagePath = filePath;
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error appropriately
        }
        return fileName;
    }

    public int findId(String uuid){
        return courseRepository.findId(uuid);
    }

    public Course getCourseById(int courseId) {
        // Call the appropriate method in your repository or data access layer to retrieve the course by courseId
        return courseRepository.getCourseById(courseId);
    }
    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    public List<Course> getByAdminIdCourse(int adminId){
        return courseRepository.findByAdminId(adminId);
    }

    public List<Course> getByUserIdCourse(int userId) {
        return courseRepository.findByUserId(userId);
    }
    public void insertCode(String code, int courseId){
        courseRepository.insertCode(code, courseId);
    }
    public LocalDateTime getLimitTimeById(int courseId){
        return courseRepository.findLimitTimeById(courseId);
    }
    public Integer getDays(int courseId){
        return courseRepository.getCountDays(courseId);
    }
}
