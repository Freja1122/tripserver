package com.tongji.testserver.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @program: testserver
 * @description:
 * @author: Annntn
 * @create: 2017-12-20 18:23
 **/
@Entity
public class MapPattern extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private long mapPatternId;
    @ManyToOne
    private Path path;
    @OneToMany(mappedBy = "mapPattern")
    private List<MapSegment> mapSegments;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMapPatternId() {
        return mapPatternId;
    }

    public void setMapPatternId(long mapPatternId) {
        this.mapPatternId = mapPatternId;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public List<MapSegment> getMapSegments() {
        return mapSegments;
    }

    public void setMapSegments(List<MapSegment> mapSegments) {
        this.mapSegments = mapSegments;
    }
}
