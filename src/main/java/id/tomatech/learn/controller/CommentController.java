package id.tomatech.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import id.tomatech.learn.entity.Comment;
import id.tomatech.learn.repository.CommentRepository;

@RestController
@RequestMapping(value="/comments")
public class CommentController {
	@Autowired
	private CommentRepository commentRepository;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Comment> addComment(@Validated @RequestBody Comment comment, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<Comment>(HttpStatus.BAD_REQUEST);
		}
		
		Comment newComment = commentRepository.save(comment);
		return new ResponseEntity<Comment>(newComment, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Comment>> listComments(Pageable pageable) {
		return new ResponseEntity<Page<Comment>>(commentRepository.findAll(pageable), HttpStatus.OK);
	}
}
