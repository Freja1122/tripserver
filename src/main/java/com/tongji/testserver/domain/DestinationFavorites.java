package com.tongji.testserver.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tongji.testserver.domain.enums.FavoritesType;

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
public class DestinationFavorites extends Favorites {

    @OneToMany(mappedBy = "destinationFavorites")
    @JsonManagedReference
    private List<Destination> destinations  = new ArrayList();

    public DestinationFavorites() {
        this.setType(FavoritesType.DESTINATION);
        setName("Destination Favorites");
    }

    public void addDestination(Destination destination){
        destination.setDestinationFavorites(this);
        this.destinations.add(destination);
    }

    public Destination deleteDestination(Destination destination){
        destination.setDestinationFavorites(null);

        int keyLocation = destinations.indexOf(destination);
        if (keyLocation==-1){
            return null;
        }
        return destinations.remove(keyLocation);
//        return this.psgHotNodes.remove(psgHotNode);

    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Destination> destinations) {
        this.destinations = destinations;
    }
}
