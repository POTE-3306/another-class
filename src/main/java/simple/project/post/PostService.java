package simple.project.post;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getByUserIdPost(int userId){
        return postRepository.findByUserId(userId);
    }

    public List<HashMap<Integer, ArrayList<String>>> getPostList(ArrayList<Integer> postlist) {
        return postRepository.getPostList(postlist);
    }
    public ArrayList<Integer> getPostsByAuthor(int authorId){
        return postRepository.findPostsByAuthor(authorId);
    }

}
