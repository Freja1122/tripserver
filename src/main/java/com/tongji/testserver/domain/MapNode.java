package com.tongji.testserver.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @program: testserver
 * @description:
 * @author: Annntn
 * @create: 2017-12-19 23:14
 **/
@Entity
public class MapNode extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;
    @ManyToOne
    private MapSegment mapSegment;

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

    public MapSegment getMapSegment() {
        return mapSegment;
    }

    public void setMapSegment(MapSegment mapSegment) {
        this.mapSegment = mapSegment;
    }
}
