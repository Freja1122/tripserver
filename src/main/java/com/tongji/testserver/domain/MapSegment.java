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
public class MapSegment extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private long mapSegmentId;
    @ManyToOne
    private MapPattern mapPattern;
    @OneToMany(mappedBy = "mapSegment")
    private List<MapNode> mapNodes;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMapSegmentId() {
        return mapSegmentId;
    }

    public void setMapSegmentId(long mapSegmentId) {
        this.mapSegmentId = mapSegmentId;
    }
}
