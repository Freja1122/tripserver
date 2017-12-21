package com.tongji.testserver.domain;

import springfox.documentation.spring.web.json.Json;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @program: testserver
 * @description: 载客热点
 * @author: Annntn
 * @create: 2017-12-20 15:21
 **/

@Entity
public class LoadHotNode extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;
    @Column(nullable = false)
    private String hotExtent;
    @Column(nullable = false)
    private long mapNodeId;
    @Column(nullable = true)
    private String title;
    @ManyToOne
    private LoadHotFavorites loadHotFavorites;

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

    public String getHotExtent() {
        return hotExtent;
    }

    public void setHotExtent(String hotExtent) {
        this.hotExtent = hotExtent;
    }

    public long getMapNodeId() {
        return mapNodeId;
    }

    public void setMapNodeId(long mapNodeId) {
        this.mapNodeId = mapNodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LoadHotFavorites getLoadHotFavorites() {
        return loadHotFavorites;
    }

    public void setLoadHotFavorites(LoadHotFavorites loadHotFavorites) {
        this.loadHotFavorites = loadHotFavorites;
    }
}
