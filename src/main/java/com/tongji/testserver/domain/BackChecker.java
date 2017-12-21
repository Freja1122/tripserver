package com.tongji.testserver.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户
 * @author annntn
 *
 */
@Entity
public class BackChecker extends Entitys implements Serializable {

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
	private String userPhone;
	@Column(nullable = true)
	private String checkQuestion;
	@Column(nullable = true)
	private String checkAnswer;
	@Column(nullable = false)
	private Long createTime;
	@Column(nullable = false)
	private Long lastModifyTime;
	@OneToMany(mappedBy = "backChecker")
	private List<Feedback> feedbacks;


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
}