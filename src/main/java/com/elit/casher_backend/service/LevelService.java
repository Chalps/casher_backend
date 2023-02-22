package com.elit.casher_backend.service;

import com.elit.casher_backend.model.Level;
import com.elit.casher_backend.model.dto.LevelDTO;
import com.elit.casher_backend.repository.LevelRepository;
import com.github.dockerjava.api.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LevelService {

    @Autowired
    LevelRepository levelRepository;

    public Optional<Level> getLevelById(Long id){
        return levelRepository.findById(id);
    }

    public List<Level> getLevels(){
        return levelRepository.getLevels();
    }

    public ResponseEntity<?> save(LevelDTO levelDTO) {
        Level level = new Level();

        if (levelDTO.getId() > 0) {
            level = levelRepository.findById(levelDTO.getId()).orElse(null);
        } else {
            boolean valid = isValidName(levelDTO.getName(), 0);
            if (!valid) {
                throw new BadRequestException("Já existe uma região com este nome.");
            }
        }

        try {
            assert level != null;
            level.setId(levelDTO.getId());
            level.setName(levelDTO.getName());
            level.setDescription(levelDTO.getDescription());
            level.setPosition(levelDTO.getPosition());

            levelRepository.save(level);

            return ResponseEntity.ok(level);
        }catch (Exception e){
           return new ResponseEntity<String>("Erro ao criar Level: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public Boolean isValidName(String levelName, Integer id) {
        Level level = levelRepository.findByName(levelName.trim());

        if (level == null)
            return true;

        if (id != 0) {
            return Objects.equals(level.getId(), id);
        } else {
            return false;
        }
    }
}
