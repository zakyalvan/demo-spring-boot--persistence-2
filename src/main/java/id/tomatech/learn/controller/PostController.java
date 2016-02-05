package id.tomatech.learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import id.tomatech.learn.entity.Comment;
import id.tomatech.learn.entity.Post;
import id.tomatech.learn.repository.CommentRepository;
import id.tomatech.learn.repository.PostRepository;

@RestController
@RequestMapping(value="/posts")
public class PostController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	/**
	 * Create posting.
	 * 
	 * @param post
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Post> createPost(@RequestBody @Validated Post post, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			LOGGER.error("Error found : {}", bindingResult.getFieldErrors());
			return new ResponseEntity<Post>(HttpStatus.BAD_REQUEST);
		}
		
		Post newPost = postRepository.save(post);
		return new ResponseEntity<>(newPost, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Post>> listPost(Pageable pageable) {
		Page<Post> posts = postRepository.findAll(pageable);
		return new ResponseEntity<Page<Post>>(posts, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{postId}/comments")
	public ResponseEntity<Page<Comment>> listCommentsForPost(@PathVariable Long postId, Pageable pageable) {
		if(!postRepository.exists(postId)) {
			return new ResponseEntity<Page<Comment>>(HttpStatus.NOT_FOUND);
		}
		
		Page<Comment> comments = commentRepository.findAllByPostId(postId, pageable);
		return new ResponseEntity<Page<Comment>>(comments, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{postId}/comments/{commentId}")
	public ResponseEntity<Comment> detailCommentForPost(@PathVariable Long postId, @PathVariable Long commentId) {
		if(!postRepository.exists(postId)) {
			return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
		}
		
		if(!commentRepository.exists(commentId)) {
			return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
		}
		
		Comment comment = commentRepository.findOneByPostIdAndId(postId, commentId);
		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}
}
