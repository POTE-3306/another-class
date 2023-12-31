package simple.project.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public List<Comment> getComments(int postId){
        return commentRepository.findByPostId(postId);
    }

    public void insertComment(Integer postId, Integer author_id, String content){
        commentRepository.insertComment(postId, author_id, content);
    }
}
