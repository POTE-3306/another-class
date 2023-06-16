package simple.project.courseplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CoursePlan getByCourseId(int courseId){ return coursePlanRepository.findByCourseId(courseId);}
}
