package com.jpdev.dslist.repositories;

import com.jpdev.dslist.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameLIstRepository extends JpaRepository<GameList, Long> {
}
