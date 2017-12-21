package com.tongji.testserver.domain;

import javax.persistence.Embeddable;
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
public class LoadHotFavorites extends Favorites {

    @OneToMany(mappedBy = "loadHotFavorites")
    private List<LoadHotNode> loadHotNodes;

    public List<LoadHotNode> getLoadHotNodes() {
        return loadHotNodes;
    }

    public void setLoadHotNodes(List<LoadHotNode> loadHotNodes) {
        this.loadHotNodes = loadHotNodes;
    }
}
