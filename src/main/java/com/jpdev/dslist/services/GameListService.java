package com.jpdev.dslist.services;

import com.jpdev.dslist.dtos.GameListDTO;
import com.jpdev.dslist.entities.GameList;
import com.jpdev.dslist.repositories.GameLIstRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameLIstRepository gameLIstRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> result = gameLIstRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

}
