package com.tongji.testserver.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tongji.testserver.domain.enums.FavoritesType;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: testserver
 * @description: 载客热点收藏夹
 * @author: Annntn
 * @create: 2017-12-20 21:32
 **/
@Entity
public class LoadHotFavorites extends Favorites {

    @OneToMany(mappedBy = "loadHotFavorites")
    @JsonManagedReference
    private List<LoadHotNode> loadHotNodes  = new ArrayList();

    public LoadHotFavorites() {
        this.setType(FavoritesType.LOADHOTSPOT);
        setName("LoadHotNode Favorites");
    }

    public LoadHotNode deleteLoadHotNode(LoadHotNode loadHotNode){
        loadHotNode.setLoadHotFavorites(null);

        int keyLocation = loadHotNodes.indexOf(loadHotNode);
        if (keyLocation==-1){
            return null;
        }
        return loadHotNodes.remove(keyLocation);
//        return this.psgHotNodes.remove(psgHotNode);

    }


    public void addLoadHotNode(LoadHotNode loadHotNode){
        loadHotNode.setLoadHotFavorites(this);
        loadHotNodes.add(loadHotNode);
    }

    public List<LoadHotNode> getLoadHotNodes() {
        return loadHotNodes;
    }

    public void setLoadHotNodes(List<LoadHotNode> loadHotNodes) {
        this.loadHotNodes = loadHotNodes;
    }
}
