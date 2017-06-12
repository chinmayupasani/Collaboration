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
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@Entity
@Component
public class Events implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1119120981923061058L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name="generator",sequenceName="events_seq",allocationSize=1)
	@Column(name="event_id")
	private int id;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="event_name")
	private String name;
	
	private String venue;
	
	private String description;
	
	private String status;
	
	@Column(name="start_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	private LocalDate startDate;
	
	@Column(name="end_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	private LocalDate endDate;
	
	@Column(name="post_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	private LocalDate postDate;
	
	@OneToMany(mappedBy="events",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<EventJoined> eventJoined;

	public List<EventJoined> getEventJoined() {
		return eventJoined;
	}

	public void setEventJoined(List<EventJoined> eventJoined) {
		this.eventJoined = eventJoined;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public LocalDate getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return "Events [id=" + id + ", userId=" + userId + ", userName=" + userName + ", name=" + name + ", venue="
				+ venue + ", description=" + description + ", status=" + status + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", postDate=" + postDate + "]";
	}
	
	
}
