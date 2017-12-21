package com.tongji.testserver.service.impl;

import com.tongji.testserver.domain.*;
import com.tongji.testserver.domain.enums.FavoritesType;
import com.tongji.testserver.repository.FavoritesRepository;
import com.tongji.testserver.service.FavoritesService;
import com.tongji.testserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("favoritesService")
public class FavoritesServiceImpl implements FavoritesService {
	
	@Autowired
	private FavoritesRepository favoritesRepository;
	
	/**
	 * 保存
	 * @param userId
	 * @param count
	 * @param name
	 * @return
	 */
	public Favorites saveFavorites(Long userId, Long count, String name){
		Favorites favorites = new Favorites();
		favorites.setName(name);
//		favorites.setUserId(userId);
		favorites.setCount(count);
		favorites.setCreateTime(DateUtils.getCurrentTime());
		favorites.setLastModifyTime(DateUtils.getCurrentTime());
		favoritesRepository.save(favorites);
		return favorites;
	}


	@Override
	public Favorites saveFavorites(User user, FavoritesType favoritesType) {
		return null;
	}

	@Override
	public Favorites getFavorites(User user, FavoritesType favoritesType) {
		return null;
	}

	@Override
	public Favorites getFavorites(User user) {
		return null;
	}

	@Override
	public void addItem(User user, Destination destination) {

	}

	@Override
	public void addItem(User user, Path path) {

	}

	@Override
	public void addItem(User user, PsgHotNode psgHotNode) {

	}

	@Override
	public void addItem(User user, LoadHotNode loadHotNode) {

	}

	@Override
	public void deleteItem(User user, Destination destination) {

	}

	@Override
	public void deleteItem(User user, Path path) {

	}

	@Override
	public void deleteItem(User user, PsgHotNode psgHotNode) {

	}

	@Override
	public void deleteItem(User user, LoadHotNode loadHotNode) {

	}

	@Override
	public List<Destination> getDestinationItems(User user) {
		return null;
	}

	@Override
	public List<Path> getPathItems(User user) {
		return null;
	}

	@Override
	public List<PsgHotNode> getPsgHotNodeItem(User user) {
		return null;
	}

	@Override
	public List<LoadHotNode> getloadHotNodeItem(User user) {
		return null;
	}
}
