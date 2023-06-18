package simple.project.comment;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import simple.project.course.Course;
import simple.project.user.JWToken;
import simple.project.user.User;
import simple.project.user.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("comment")
public class CommentController {
    private final JWToken jwToken;
    private final UserService userService;
    private final CommentService commentService;

    public CommentController(JWToken jwToken, UserService userService, CommentService commentService) {
        this.jwToken = jwToken;
        this.userService = userService;
        this.commentService = commentService;
    }

    @PostMapping("createComment")
    public String createComment(
            HttpSession session,
            @RequestParam("content") String content,
            @RequestParam("user_id") Integer userId,
            @RequestParam("post_id") Integer postId,
            @RequestParam("boardType") String boardType
    ){
        String token = (String) session.getAttribute("token");
        if (token == null){
            return "index";
        }
        try {
            Claims claims = jwToken.getClaims(token);
            User user = userService.getUserByToken(claims);

            if (user == null) {
                return "index";
            }
            commentService.insertComment(postId, userId, content);
        } catch (Exception e){
            e.printStackTrace();
            return "index";
        }
        return String.format("redirect:/post/%d?boardType=%s", postId, boardType);
    }

}
