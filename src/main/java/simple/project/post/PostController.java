package simple.project.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import simple.project.course.CourseController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/classMain")
    public String getClassMain(Model model) {
        int authorId = 1;

        ArrayList<Integer> postIds = postService.getPostsByAuthor(authorId);
        List<HashMap<Integer, ArrayList<String>>> postInfoList = postService.getPostList(postIds);

        model.addAttribute("postInfoList", postInfoList);

        return "class/mainClass";
    }
}

