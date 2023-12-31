package simple.project.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import simple.project.comment.Comment;
import simple.project.comment.CommentDto;
import simple.project.comment.CommentService;
import simple.project.course.Course;
import simple.project.course.CourseController;
import simple.project.course.CourseService;
import simple.project.user.User;
import simple.project.user.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("post")
public class PostController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final PostService postService;
    private final CourseService courseService;
    private final CommentService commentService;
    private final UserService userService;


    @Autowired
    public PostController(PostService postService, CourseService courseService, CommentService commentService, UserService userService) {
        this.postService = postService;
        this.courseService = courseService;
        this.commentService = commentService;
        this.userService = userService;
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

    @RequestMapping("/{postId}")
    public String noticePage(
            HttpServletRequest request,
            @PathVariable("postId") int postId,
            @RequestParam("boardType") String boardtype,
            Model model
    ) {
        User user = (User) request.getAttribute("user");
        try {
            HashMap<String, Integer> boardMapper = new HashMap<>();
            boardMapper.put("NOTICE", 1);
            boardMapper.put("ASSIGNMENT", 2);
            boardMapper.put("MATERIAL", 3);
            boardMapper.put("CHAT", 4);
            List<Post> posts = postService.getPosts(boardMapper.get(boardtype));
            List<Comment> comments = commentService.getComments(postId);
            int courseId = postService.getCourseId(postId);
            List<User> users = userService.findAllUser();
            List<CommentDto> commentDtos = new ArrayList<>();
            for (Post post : posts) {
                PostDto postDto = new PostDto(post.getTitle(), post.getContent(), post.getPostTime(), postId);
                User author = getUserById(users, post.getUserId());
                if (author != null) {
                    postDto.setAuthor(author.getName());
                }
                if (post.getId() == postId)
                    model.addAttribute("postDto", postDto);
            }
            for (Comment comment : comments) {
                CommentDto commentDto = new CommentDto(comment.getContent(), comment.getPostTime());
                User author = getUserById(users, comment.getAuthorId());
                if (author != null) {
                    commentDto.setAuthor(author.getName());
                }
                commentDtos.add(commentDto);
            }
            model.addAttribute("commentDto", commentDtos);
            model.addAttribute("user", user);
            model.addAttribute("boardType", boardtype);
            model.addAttribute("classId", courseId);
        } catch (Exception e){
            e.printStackTrace();
            return "index";
        }
        return "class/subCommunity";
    }

    private User getUserById(List<User> users, int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().orElse(null);
    }
}

