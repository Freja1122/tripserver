package com.tongji.testserver.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tongji.testserver.domain.enums.FavoritesType;

import javax.persistence.CascadeType;
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
public class PsgHotFavorites extends Favorites {

    @OneToMany(mappedBy = "psgHotFavorites",cascade= CascadeType.ALL)
    @JsonManagedReference
    private List<PsgHotNode> psgHotNodes  = new ArrayList();

    public PsgHotFavorites() {
        setType(FavoritesType.PSGHOTNODE);
        setName("LoadHotFavorites");
    }

    public void addPsgHotNode(PsgHotNode psgHotNode){
        psgHotNode.setPsgHotFavorites(this);
        this.psgHotNodes.add(psgHotNode);
    }

    public PsgHotNode deletePsgHotNode(PsgHotNode psgHotNode){
        psgHotNode.setPsgHotFavorites(null);

        int keyLocation = psgHotNodes.indexOf(psgHotNode);
        if (keyLocation==-1){
            return null;
        }
        return psgHotNodes.remove(keyLocation);
//        return this.psgHotNodes.remove(psgHotNode);

    }

    public List<PsgHotNode> getPsgHotNodes() {
        return psgHotNodes;
    }

    public void setPsgHotNodes(List<PsgHotNode> psgHotNodes) {
        this.psgHotNodes = psgHotNodes;
    }
}
