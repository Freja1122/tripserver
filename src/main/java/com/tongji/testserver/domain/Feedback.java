package com.tongji.testserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tongji.testserver.domain.enums.IsSolved;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户反馈javaBean类
 * Created by annntn
 */
@Entity
public class Feedback  extends Entitys implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JsonBackReference
    private User user;
    @ManyToOne
    private BackChecker backChecker;
    @Column(nullable = false)
    private String feedbackAdvice;
    @Column(nullable = true)
    private String feedbackName;
    @Column(nullable = true)
    private String solution;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private Long createTime;
    @Column(nullable = false)
    private Long lastModifyTime;
    @Column(nullable = true)
    private String dealTime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IsSolved solved;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BackChecker getBackChecker() {
        return backChecker;
    }

    public void setBackChecker(BackChecker backChecker) {
        this.backChecker = backChecker;
    }

    public String getFeedbackAdvice() {
        return feedbackAdvice;
    }

    public void setFeedbackAdvice(String feedbackAdvice) {
        this.feedbackAdvice = feedbackAdvice;
    }

    public String getFeedbackName() {
        return feedbackName;
    }

    public void setFeedbackName(String feedbackName) {
        this.feedbackName = feedbackName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public IsSolved getSolved() {
        return solved;
    }

    public void setSolved(IsSolved solved) {
        this.solved = solved;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
