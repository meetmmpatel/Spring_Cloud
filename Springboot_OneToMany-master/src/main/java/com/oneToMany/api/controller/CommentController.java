package com.oneToMany.api.controller;


import com.oneToMany.api.exception.ApiException;
import com.oneToMany.api.model.Comment;
import com.oneToMany.api.model.Post;
import com.oneToMany.api.repository.CommentRepository;
import com.oneToMany.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class CommentController {
  
  @Autowired
  private CommentRepository commentRepository;
  
  @Autowired
  private PostRepository postRepository;
  
  
  //Get Comments // find comment by post id..
  @GetMapping("/posts/{postId}/comments")
  public Page<Comment> getAllComments(@PathVariable(value = "postId") Long postId, Pageable pageable) {
	return commentRepository.findByPostId(postId, pageable);
	
  }
  
  //Post Method for Comments.. only insert comment if post id exists
  // One Post ID can have multiple comments..
  
  @PostMapping("/posts/{postId}/comments")
  public Comment createComment
		  (@PathVariable(value = "postId") Long id, @RequestBody Comment comment)
		  throws ApiException {
	
	return postRepository.findById(id).map(post -> {
	  comment.setPost(post);
	  return commentRepository.save(comment);
	}).orElseThrow(() -> new ApiException("PostID" + id + "Not Found!"));
  }
  
  //Put and Delete.
  //Update Comments... postID (there is post) .. Comment ID...body.. new text comment
  
  @PutMapping("/posts/{postId}/comments/{commentId}")
  public Comment updateComment(
		  @PathVariable(value = "postId") Long postId,
		  @PathVariable(value = "commentId") Long commentId,
		  @RequestBody Comment commentUpdated) throws ApiException {
	
	//if there is no postId .. do not want update comments..
	if (!postRepository.existsById(postId)) {
	  throw new ApiException("PostID" + postId + "Not Found and so can not update comments");
	}
	
	return commentRepository.findById(commentId).map(comment -> {
	  comment.setText(commentUpdated.getText());
	  return commentRepository.save(comment);
	}).orElseThrow(() -> new ApiException("Comment ID " + commentId + "Not Found. "));
  }
  
  @DeleteMapping("/posts/{postId}/comments/{commentId}")
  public ResponseEntity<?> deleteCommnet(@PathVariable(value = "postId") Long postId,
										 @PathVariable(value = "commentId") Long commentId) throws ApiException {
	return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
	  commentRepository.delete(comment);
	  return ResponseEntity.ok().build();
	}).orElseThrow(() -> new ApiException("Comment can not delete " + commentId));
	
  }
  
  
}
