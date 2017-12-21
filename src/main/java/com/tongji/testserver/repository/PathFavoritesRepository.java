package com.tongji.testserver.repository;

import com.tongji.testserver.domain.PathFavorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PathFavoritesRepository  extends JpaRepository<PathFavorites, Long> {
}
