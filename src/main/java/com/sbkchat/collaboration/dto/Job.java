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
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Component
@Table(name="job_detail")
public class Job implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6324297488573911118L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="generator")
	@SequenceGenerator(name="generator",sequenceName="job_detail_seq",allocationSize=1)
	@Column(name="job_id")
	private int id;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="sub_title")
	private String subTitle;
	
	@Column(name="about")
	private String about;
	
	@Column(name="job_profile")
	private String jobProfile;
	
	private String qualification;
	
	@Column(name="contact_info")
	private String contactInfo;
	
	private String status;
	
	@Column(name="post_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	private LocalDate postDate;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name")
	private String userName;
	
	@OneToMany(mappedBy="job",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<JobApplied> jobApplied;

	public List<JobApplied> getJobApplied() {
		return jobApplied;
	}

	public void setJobApplied(List<JobApplied> jobApplied) {
		this.jobApplied = jobApplied;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getJobProfile() {
		return jobProfile;
	}

	public void setJobProfile(String jobProfile) {
		this.jobProfile = jobProfile;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDate postDate) {
		this.postDate = postDate;
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

	@Override
	public String toString() {
		return "Job [id=" + id + ", companyName=" + companyName + ", subTitle=" + subTitle + ", about=" + about
				+ ", jobProfile=" + jobProfile + ", qualification=" + qualification + ", contactInfo=" + contactInfo
				+ ", status=" + status + ", postDate=" + postDate + ", userId=" + userId + ", userName=" + userName
				+ "]";
	}
	
	
}
