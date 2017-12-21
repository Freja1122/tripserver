package com.tongji.testserver.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import springfox.documentation.spring.web.json.Json;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @program: testserver
 * @description:
 * @author: Annntn
 * @create: 2017-12-19 23:30
 **/
@Entity
public class Path extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = true)
    private String segmentIds;
    @Column(nullable = true)
    private String recommendTime;
    @Column(nullable = true)
    private Long preferLeaveTime;
    @OneToMany(mappedBy = "path")
    private List<Destination> destinations;
    @Column(nullable = true)
    private String recommendPath;
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
    private User user;
    @OneToMany(mappedBy = "path")
    @JsonManagedReference
    private List<MapPattern> mapPatterns;
    @OneToOne(mappedBy = "path")
    private Notice notice;
    @ManyToOne
    @JsonBackReference
    private PathFavorites pathFavorites;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return id == path.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
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

    public String getSegmentIds() {
        return segmentIds;
    }

    public void setSegmentIds(String segmentIds) {
        this.segmentIds = segmentIds;
    }

    public String getRecommendTime() {
        return recommendTime;
    }

    public void setRecommendTime(String recommendTime) {
        this.recommendTime = recommendTime;
    }

    public Long getPreferLeaveTime() {
        return preferLeaveTime;
    }

    public void setPreferLeaveTime(Long preferLeaveTime) {
        this.preferLeaveTime = preferLeaveTime;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }

    public String getRecommendPath() {
        return recommendPath;
    }

    public void setRecommendPath(String recommendPath) {
        this.recommendPath = recommendPath;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MapPattern> getMapPatterns() {
        return mapPatterns;
    }

    public void setMapPatterns(List<MapPattern> mapPatterns) {
        this.mapPatterns = mapPatterns;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public PathFavorites getPathFavorites() {
        return pathFavorites;
    }

    public void setPathFavorites(PathFavorites pathFavorites) {
        this.pathFavorites = pathFavorites;
    }
}
