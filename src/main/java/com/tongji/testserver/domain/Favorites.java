package com.tongji.testserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tongji.testserver.domain.enums.CollectType;
import com.tongji.testserver.domain.enums.FavoritesType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 收藏夹
 * @author annntn
 *
 */
@Entity
public class Favorites extends Entitys implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Long count;
	@Column(nullable = false)
	private Long createTime;
	@Column(nullable = false)
	private Long lastModifyTime;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private FavoritesType type;
	@Transient
	private Long publicCount;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
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

	public FavoritesType getType() {
		return type;
	}

	public void setType(FavoritesType type) {
		this.type = type;
	}

	public Long getPublicCount() {
		return publicCount;
	}

	public void setPublicCount(Long publicCount) {
		this.publicCount = publicCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}