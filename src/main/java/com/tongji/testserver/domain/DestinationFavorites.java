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
public class DestinationFavorites extends Favorites {

    @OneToMany(mappedBy = "destinationFavorites")
    private List<Destination> destinations;

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }
}
