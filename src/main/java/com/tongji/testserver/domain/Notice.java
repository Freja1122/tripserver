package com.tongji.testserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tongji.testserver.domain.enums.IsReaded;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

/**
 * 消息
 * 
 * @author annntn
 * 
 */
@Entity
public class Notice extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@OneToOne(cascade=CascadeType.ALL)
	private Path path;
	@Column(nullable = false)
	private String type;
	@Column(nullable = false)
	private String readed;
	@Column(nullable = false)
	private Long createTime;
	@Column(nullable = false)
	private Long noticeTime;
	@Column(nullable = false)
	private Long lastModifyTime;
	@Column(nullable = false)
	private String description;
	@ManyToOne
	@JsonBackReference
	private User user;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReaded() {
		return readed;
	}

	public void setReaded(String readed) {
		this.readed = readed;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Long noticeTime) {
		this.noticeTime = noticeTime;
	}

	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}