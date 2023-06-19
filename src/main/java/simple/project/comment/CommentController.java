package simple.project.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import simple.project.user.JWToken;
import simple.project.user.UserService;

@Controller
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("createComment")
    public String createComment(
            @RequestParam("content") String content,
            @RequestParam("user_id") Integer userId,
            @RequestParam("post_id") Integer postId,
            @RequestParam("boardType") String boardType
    ){
        try {
            commentService.insertComment(postId, userId, content);
        } catch (Exception e){
            e.printStackTrace();
            return "index";
        }
        return String.format("redirect:/post/%d?boardType=%s", postId, boardType);
    }

}
