package com.tongji.testserver.repository;

import com.tongji.testserver.domain.LoadHotFavorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoadHotFavoritesRepository extends JpaRepository<LoadHotFavorites, Long> {
}
