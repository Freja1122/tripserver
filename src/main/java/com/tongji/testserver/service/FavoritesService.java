package com.tongji.testserver.service;

import com.tongji.testserver.domain.*;
import com.tongji.testserver.domain.enums.FavoritesType;

import java.util.List;

public interface FavoritesService {
	
	public Favorites saveFavorites(Long userId, Long count, String name);

	public Favorites saveFavorites(User user, FavoritesType favoritesType);

	public Favorites getFavorites(User user, FavoritesType favoritesType);

	public Favorites getFavorites(User user);

	public void addItem(User user, Destination destination);

	public void addItem(User user, Path path);

	public void addItem(User user, PsgHotNode psgHotNode);

	public void addItem(User user, LoadHotNode loadHotNode);

	public void deleteItem(User user, Destination destination);

	public void deleteItem(User user, Path path);

	public void deleteItem(User user, PsgHotNode psgHotNode);

	public void deleteItem(User user, LoadHotNode loadHotNode);

	public List<Destination> getDestinationItems(User user);

	public List<Path> getPathItems(User user);

	public List<PsgHotNode> getPsgHotNodeItems(User user);

	public List<LoadHotNode> getloadHotNodeItems(User user);

}
