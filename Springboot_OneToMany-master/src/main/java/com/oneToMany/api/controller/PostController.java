package com.oneToMany.api.controller;

import com.oneToMany.api.exception.ApiException;
import com.oneToMany.api.model.Post;
import com.oneToMany.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class PostController {
  
  @Autowired
  private PostRepository postRepository;
  
  @GetMapping("/posts")
  public Page<Post> getAllPosts(Pageable pageable) {
	return postRepository.findAll(pageable);
  }
  
  @PostMapping("/posts")
  public Post createPost(@RequestBody Post post) {
	return postRepository.save(post);
  }
  
  //PUT and Delete..
  
  @PutMapping("/posts/{postId}")
  public Post updatePost(@PathVariable Long postId, @RequestBody Post postRequest) throws ApiException {
	return postRepository.findById(postId).map(post -> {
	  post.setTitle(postRequest.getTitle());
	  post.setDescription(postRequest.getDescription());
	  post.setContent(postRequest.getContent());
	  return postRepository.save(post);
	}).orElseThrow(() -> new ApiException("PostId " + postId + " not found"));
  }
  
  @DeleteMapping("/posts/{postId}")
  public ResponseEntity<?> deletePost(@PathVariable Long postId) throws ApiException {
	return postRepository.findById(postId).map(post -> {
	  postRepository.delete(post);
	  return ResponseEntity.ok().build();
	}).orElseThrow(() -> new ApiException("PostId " + postId + " not found"));
  }
  
}
