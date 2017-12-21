package com.tongji.testserver.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @program: testserver
 * @description:
 * @author: Annntn
 * @create: 2017-12-20 22:08
 **/
@Entity
public class PathFavorites extends Favorites {
    @OneToMany(mappedBy = "pathFavorites")
    @JsonManagedReference
    private List<Path> paths;

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }
}
