package simple.project.courseplan;

import org.springframework.stereotype.Service;

@Service
public class CoursePlanService {
    private final CoursePlanRepository coursePlanRepository;

    public CoursePlanService(CoursePlanRepository coursePlanRepository) {
        this.coursePlanRepository = coursePlanRepository;
    }
}
