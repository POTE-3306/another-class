package simple.project.courseplan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import simple.project.course.CourseController;

@Controller
public class CoursePlanController {
    private final Logger logger = LoggerFactory.getLogger(CoursePlanController.class);
    private final CoursePlanService coursePlanService;

    @Autowired
    public CoursePlanController(CoursePlanService coursePlanService){
        this.coursePlanService = coursePlanService;
    }

    @PostMapping("/course/insert-plan")
    public String insertPlan(
            @RequestParam("courseId") String courseId,
            @RequestParam("title") String title,
            @RequestParam("content") String content){
        System.out.println("1 : " + courseId);
        CoursePlan coursePlan = new CoursePlan();
        coursePlan.setTitle(title);
        int id = Integer.parseInt(courseId);
        coursePlan.setCourseId(id);
        coursePlan.setDescription(content);
        System.out.println("courseplan : " + title);
        coursePlanService.insertCoursePlan(coursePlan);

        return "temp";
    }

}
