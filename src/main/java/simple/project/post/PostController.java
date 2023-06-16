package simple.project.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.project.course.Course;
import simple.project.course.CourseService;
import simple.project.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("post")
public class PostController {
    private final PostService postService;
    private final CourseService courseService;

    @Autowired
    public PostController(PostService postService, CourseService courseService) {
        this.postService = postService;
        this.courseService = courseService;
    }
    @RequestMapping("/classMain")
    public String getClassMain(Model model) {
        int authorId = 1;

        ArrayList<Integer> postIds = postService.getPostsByAuthor(authorId);
        List<HashMap<Integer, ArrayList<String>>> postInfoList = postService.getPostList(postIds);

        model.addAttribute("postInfoList", postInfoList);

        return "class/mainClass";
    }

    @RequestMapping("main")
    public String main(HttpServletRequest request, Model model) {
        User user = (User) request.getAttribute("user");
        try {
            List<Post> postList = postService.getByUserIdPost(user.getId());
            List<Course> courseList = courseService.getByUserIdCourse(user.getId());
            model.addAttribute("postList", postList);
            model.addAttribute("user", user);
            model.addAttribute("courseList", courseList);
        } catch (Exception e) {
            e.printStackTrace();
            return "index";
        }
        return "main/main";
    }
}

