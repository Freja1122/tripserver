package com.tongji.testserver.repository;

import com.tongji.testserver.domain.Collect;
import com.tongji.testserver.domain.DestinationFavorites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationFavoritesRepository  extends JpaRepository<DestinationFavorites, Long> {
}
