package com.tongji.testserver.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @program: testserver
 * @description:
 * @author: Annntn
 * @create: 2017-12-19 23:28
 **/

@Entity
public class Destination extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;
    @Column(nullable = false)
    private int desOrder;
    @Column(nullable = false)
    private int dayOfWeek;
    @Column(nullable = false)
    private Long arriveTime;
    @Column(nullable = false)
    private Long createTime;
    @Column(nullable = true)
    private String title;
    @Column(nullable = true)
    private String type;
    @Column(nullable = true)
    private String description;
    @ManyToMany
    private List<Tag> tags;
    @ManyToOne
    @JsonBackReference
    private Path path;
    @ManyToOne
    @JsonBackReference
    private User user;
    @ManyToOne
    @JsonBackReference
    private DestinationFavorites destinationFavorites;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Destination that = (Destination) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getDesOrder() {
        return desOrder;
    }

    public void setDesOrder(int desOrder) {
        this.desOrder = desOrder;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DestinationFavorites getDestinationFavorites() {
        return destinationFavorites;
    }

    public void setDestinationFavorites(DestinationFavorites destinationFavorites) {
        this.destinationFavorites = destinationFavorites;
    }
}
