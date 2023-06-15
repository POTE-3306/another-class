package simple.project.courseplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
