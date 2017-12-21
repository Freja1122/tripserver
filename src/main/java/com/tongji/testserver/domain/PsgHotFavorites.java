package com.tongji.testserver.domain;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @program: testserver
 * @description: 载客热点收藏夹
 * @author: Annntn
 * @create: 2017-12-20 21:32
 **/
@Entity
public class PsgHotFavorites extends Favorites {

    @OneToMany(mappedBy = "psgHotFavorites")
    private List<PsgHotNode> psgHotNodes;

    public List<PsgHotNode> getPsgHotNodes() {
        return psgHotNodes;
    }

    public void setPsgHotNodes(List<PsgHotNode> psgHotNodes) {
        this.psgHotNodes = psgHotNodes;
    }
}
