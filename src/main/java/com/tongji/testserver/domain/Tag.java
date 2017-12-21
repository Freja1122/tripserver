package com.tongji.testserver.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * @program: testserver
 * @description:
 * @author: Annntn
 * @create: 2017-12-19 23:47
 **/
@Entity
public class Tag extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String tagTitle;
    @Column(nullable = false)
    private Long createTime;
    @ManyToOne
    private User user;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagTitle() {
        return tagTitle;
    }

    public void setTagTitle(String tagTitle) {
        this.tagTitle = tagTitle;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
