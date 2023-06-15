package simple.project.course;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {this.courseRepository = courseRepository;}

    public List<Course> getAllCourse(){
        return courseRepository.findAll();
    }

    public List<Course> getByAdminIdCourse(int adminId){
        return courseRepository.findByAdminId(adminId);
    }

    public List<Course> getByUserIdCourse(int userId) {
        return courseRepository.findByUserId(userId);
    }

}
