package com.jpdev.dslist.services;

import com.jpdev.dslist.dtos.GameListDTO;
import com.jpdev.dslist.entities.GameList;
import com.jpdev.dslist.projections.GameMinProjection;
import com.jpdev.dslist.repositories.GameLIstRepository;
import com.jpdev.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameLIstRepository gameLIstRepository;

    @Autowired
    GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll(){
        List<GameList> result = gameLIstRepository.findAll();
        return result.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex){
        List<GameMinProjection> list = gameRepository.searchByList(listId);
        GameMinProjection obj = list.remove(sourceIndex);
        list.add(destinationIndex, obj);

        int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
        int max = sourceIndex > destinationIndex ? sourceIndex : destinationIndex;

        for (int i = min; i <= max; i++){
            gameLIstRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
        }
    }

}
