package com.tongji.testserver.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tongji.testserver.domain.enums.FavoritesType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
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
    private List<Path> paths = new ArrayList();

    public PathFavorites() {
        setType(FavoritesType.PATH);
        setName("Path Favorites");
    }

    public Path deletePath(Path path){
        path.setPathFavorites(null);

        int keyLocation = paths.indexOf(path);
        if (keyLocation==-1){
            return null;
        }
        return paths.remove(keyLocation);
//        return this.psgHotNodes.remove(psgHotNode);

    }

    public List<Path> getPaths() {
        return paths;
    }

    public void setPaths(List<Path> paths) {
        this.paths = paths;
    }

    public void addPath(Path path){
        path.setPathFavorites(this);
        this.paths.add(path);
    }
}
