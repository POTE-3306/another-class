package simple.project.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import simple.project.courseplan.CoursePlan;
import simple.project.courseplan.CoursePlanService;

import java.util.UUID;

@Controller
@RequestMapping("course")
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;
    private final CoursePlanService coursePlanService;

    @Autowired
    public CourseController(CourseService courseService, CoursePlanService coursePlanService) {
        this.courseService = courseService;
        this.coursePlanService = coursePlanService;
    }

    @GetMapping("make-class")
    public String makeClassPage() {
        return "/makeClass";
    }

    @PostMapping("insert-class")
    public String makeClass(
            @RequestParam("image") MultipartFile image,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        Course course = new Course();
        String filePath = courseService.saveImage(image);
        String uuid = UUID.randomUUID().toString();
        course.setUuid(uuid);
        course.setLogoUrl(filePath);
        course.setName(title);
        course.setDescription(content);

        // Call the service method passing the Course instance
        courseService.makeClass(course);

        int courseId = courseService.findId(uuid);
        System.out.println(courseId);
        return "/makeCoursePlan";
    }


    @GetMapping("makeCoursePlan")
    public String makeCoursePlan(@RequestParam("courseId") int courseId, Model model) {
        // Retrieve the course details based on the courseId
        Course course = courseService.getCourseById(courseId);

        // Pass the course object to the makeCoursePlan.jsp view
        model.addAttribute("course", course);

        // Return the makeCoursePlan.jsp view
        return "makeCoursePlan";
    }

}
