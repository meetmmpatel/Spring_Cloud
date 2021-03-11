package com.oneToMany.api.model;

import javax.persistence.*;


@Entity
@Table(name = "posts")
public class Post extends AuditModel {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Column(unique = true, nullable = false)
  private String title;
  
  @Column(unique = true, nullable = false)
  private String description;
  
  
  @Column(nullable = false)
  private String content;
  
  public Post() {
  }
  
  public Long getId() {
	return id;
  }
  
  public void setId(Long id) {
	this.id = id;
  }
  
  public String getTitle() {
	return title;
  }
  
  public void setTitle(String title) {
	this.title = title;
  }
  
  public String getDescription() {
	return description;
  }
  
  public void setDescription(String description) {
	this.description = description;
  }
  
  public String getContent() {
	return content;
  }
  
  public void setContent(String content) {
	this.content = content;
  }
  
  @Override
  public String toString() {
	return "Post{" +
			"id=" + id +
			", title='" + title + '\'' +
			", description='" + description + '\'' +
			", content='" + content + '\'' +
			'}';
  }
}
