package com.tongji.testserver.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 用户
 * @author annntn
 *
 */
@Entity
public class User extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false, unique = true)
	private String userName;
	@Column(nullable = false)
	private String passWord;
	@Column(nullable = false, unique = true)
	private String email;	
	@Column(nullable = true)
	private String profilePicture;
	@Column(nullable = true)
	private String userPhone;
	@Column(nullable = true)
	private String checkQuestion;
	@Column(nullable = true)
	private String checkAnswer;
	@Column(nullable = true,length = 65535,columnDefinition="Text")
	private String introduction;
	@Column(nullable = false)
	private Long createTime;
	@Column(nullable = false)
	private Long lastModifyTime;

	@Column(nullable = true)
	private String outDate;
	@Column(nullable = true)
	private String validataCode;

	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<Favorites> favorites;
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Role> roles;
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<Path> paths;
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<Destination> destinations;
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<Feedback> feedbacks;
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER)
	@JsonManagedReference
	private Set<Notice> notices;



	public User() {
		super();
	}
	public User(String email, String passWord, String userName) {
		super();
		this.email = email;
		this.passWord = passWord;
		this.userName = userName;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getCheckQuestion() {
		return checkQuestion;
	}

	public void setCheckQuestion(String checkQuestion) {
		this.checkQuestion = checkQuestion;
	}

	public String getCheckAnswer() {
		return checkAnswer;
	}

	public void setCheckAnswer(String checkAnswer) {
		this.checkAnswer = checkAnswer;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getValidataCode() {
		return validataCode;
	}

	public void setValidataCode(String validataCode) {
		this.validataCode = validataCode;
	}

	public Set<Favorites> getFavorites() {
		return favorites;
	}

	public void setFavorites(Set<Favorites> favorites) {
		this.favorites = favorites;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Path> getPaths() {
		return paths;
	}

	public void setPaths(Set<Path> paths) {
		this.paths = paths;
	}

	public Set<Destination> getDestinations() {
		return destinations;
	}

	public void setDestinations(Set<Destination> destinations) {
		this.destinations = destinations;
	}

	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Set<Notice> getNotices() {
		return notices;
	}

	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}
}