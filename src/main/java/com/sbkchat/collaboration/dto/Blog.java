package com.sbkchat.collaboration.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Component
public class Blog extends Status implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 791062887879222661L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "generator")
	@SequenceGenerator(name="generator", sequenceName = "blog_seq", allocationSize = 1)
	@Column(name="blog_id")
	private int id;
	
	@Column(name="blog_name")
	private String name;
	
	private String status;
	
	@Column(name= "create_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	private LocalDate createDate;
	
	private String description;
	
	@Column(name="no_of_likes")
	private int noOfLikes;
	
	@Column(name="no_of_comments")
	private int noOfComments;
	
	@Column(name="no_of_views")
	private int noOfViews;
	
	@Column(name="user_id")
	private int userId;
	
	private String username;
	
	@OneToMany(mappedBy="blog", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<BlogComment> blogCommnet;

	public List<BlogComment> getBlogCommnet() {
		return blogCommnet;
	}

	public void setBlogCommnet(List<BlogComment> blogCommnet) {
		this.blogCommnet = blogCommnet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNoOfLikes() {
		return noOfLikes;
	}

	public void setNoOfLikes(int noOfLikes) {
		this.noOfLikes = noOfLikes;
	}

	public int getNoOfComments() {
		return noOfComments;
	}

	public void setNoOfComments(int noOfComments) {
		this.noOfComments = noOfComments;
	}

	public int getNoOfViews() {
		return noOfViews;
	}

	public void setNoOfViews(int noOfViews) {
		this.noOfViews = noOfViews;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", name=" + name + ", status=" + status + ", createDate=" + createDate
				+ ", description=" + description + ", noOfLikes=" + noOfLikes + ", noOfComments=" + noOfComments
				+ ", noOfViews=" + noOfViews + ", userId=" + userId + ", username=" + username + "]";
	}
	
	
}
