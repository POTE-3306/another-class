package simple.project.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void makeClass(Course course) {
        // Call the repository method passing the file path instead of the MultipartFile
        courseRepository.insert(course);
    }

    public String saveImage(MultipartFile image) {
        final String MY_PROJECT_PATH = "C:/Users/djfwk/source/repos/another-class";
        String imagePath = null;
        try {
            // Generate a unique file name or use the original file name as needed
            String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
            String uploadDir = MY_PROJECT_PATH + "/filestream/class"; // Set the appropriate directory path

            // Create the directory if it doesn't exist
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the image file to the specified directory
            String filePath = uploadDir + "/" + fileName;
            image.transferTo(new File(filePath));
            // Set the file path to be stored in the database
            imagePath = filePath;
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the error appropriately
        }
        return imagePath;
    }

    public int findId(String uuid){
        return courseRepository.findId(uuid);
    }

    public Course getCourseById(int courseId) {
        // Call the appropriate method in your repository or data access layer to retrieve the course by courseId
        return courseRepository.getCourseById(courseId);
    }

}
