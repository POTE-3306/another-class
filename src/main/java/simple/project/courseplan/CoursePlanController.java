package simple.project.courseplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import simple.project.course.Course;
import simple.project.course.CourseService;
import simple.project.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CoursePlanController {
    private final CoursePlanService coursePlanService;
    private final CourseService courseService;

    @Autowired
    public CoursePlanController(CoursePlanService coursePlanService, CourseService courseService) {
        this.coursePlanService = coursePlanService;
        this.courseService = courseService;
    }

    @PostMapping("/course/insert-plan")
    public String insertPlan(
            @RequestParam("courseId") String courseId,
            @RequestParam("title") String title,
            @RequestParam("content") String content){
        CoursePlan coursePlan = new CoursePlan();
        coursePlan.setTitle(title);
        int id = Integer.parseInt(courseId);
        coursePlan.setCourseId(id);
        coursePlan.setDescription(content);
        coursePlanService.insertCoursePlan(coursePlan);
        return "redirect:/lecture/" + courseId;
    }

    @RequestMapping("lecture/{class_id}/plan")
    public String mainClass(
            HttpServletRequest request,
            Model model,
            @PathVariable("class_id") int classId
    ){
        User user = (User) request.getAttribute("user");
        try {
            List<CoursePlan> coursePlanList = coursePlanService.getCoursePlanList(classId);
            model.addAttribute("user", user);
            model.addAttribute("classId", classId);
            model.addAttribute("coursePlanList", coursePlanList);
        } catch (Exception e){
            e.printStackTrace();
            return "index";
        }
        return "class/plan";
    }

}
