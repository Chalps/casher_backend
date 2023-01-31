package com.elit.casher_backend.service;

import com.elit.casher_backend.model.Level;
import com.elit.casher_backend.model.User;
import com.elit.casher_backend.repository.LevelRepository;
import com.elit.casher_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelService {

    @Autowired
    LevelRepository levelRepository;

    public Level getLevelById(Long id){
        return levelRepository.getReferenceById(id);
    }

}
