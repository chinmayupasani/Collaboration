package com.sbkchat.collaboration.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Component
@Table(name="user_details")
public class User extends Status implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9207462680211879395L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE , generator ="generator")
	@SequenceGenerator(name = "generator", sequenceName = "users_seq", allocationSize=1)
	@Column(name="user_id")
	private int id; 
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String username;
	
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="birth_date")
	@JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	private LocalDate birthDate;
	
	private char gender;
	
	private String role;
	
	private String phone;
	
	private String profile;
	
	private String status;
	
	@Column(name="is_online")
	private boolean isOnline;
	
	private boolean enabled;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", emailId=" + emailId
				+ ", birthDate=" + birthDate + ", gender=" + gender + ", role=" + role + ", phone=" + phone
				+ ", profile=" + profile + ", status=" + status + ", isOnline=" + isOnline + ", enabled=" + enabled
				+ "]";
	}
	
	
}
