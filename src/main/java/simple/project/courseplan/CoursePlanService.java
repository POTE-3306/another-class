package simple.project.courseplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.project.course.Course;

import java.util.List;

@Service
public class CoursePlanService {
    private final CoursePlanRepository coursePlanRepository;

    @Autowired
    public CoursePlanService(CoursePlanRepository coursePlanRepository) {
        this.coursePlanRepository = coursePlanRepository;
    }

    public void insertCoursePlan(CoursePlan coursePlan){
        coursePlanRepository.insert(coursePlan);
    }

    public List<CoursePlan> getCoursePlanList(int courseId){return coursePlanRepository.getCoursePlanList(courseId);}
}
