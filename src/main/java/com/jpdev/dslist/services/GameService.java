package com.jpdev.dslist.services;

import com.jpdev.dslist.dtos.GameDTO;
import com.jpdev.dslist.dtos.GameMinDTO;
import com.jpdev.dslist.entities.Game;
import com.jpdev.dslist.projections.GameMinProjection;
import com.jpdev.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll(){
        List<Game> result = gameRepository.findAll();
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }
    @Transactional(readOnly = true)
    public GameDTO findById(Long id){
        return new GameDTO(gameRepository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findByList(Long listId){
        List<GameMinProjection> result = gameRepository.searchByList(listId);
        return result.stream().map(x -> new GameMinDTO(x)).toList();

    }

}
