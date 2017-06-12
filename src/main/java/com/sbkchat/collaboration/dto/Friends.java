package com.sbkchat.collaboration.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="friends_list")
public class Friends implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2397635129162780394L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="generator")
	@SequenceGenerator(name = "generator", sequenceName="friend_list_seq",allocationSize=1)
	private int id;
	
	@Column(name = "initiator_id")
	private int initiatorId;
	
	@Column(name = "friend_id")
	private int friendId;
	
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInitiatorId() {
		return initiatorId;
	}

	public void setInitiatorId(int initiatorId) {
		this.initiatorId = initiatorId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Friends [id=" + id + ", initiatorId=" + initiatorId + ", friendId=" + friendId + ", status=" + status
				+ "]";
	}
	
	
}
