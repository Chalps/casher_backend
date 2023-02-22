package com.elit.casher_backend.controller;

import com.elit.casher_backend.model.Level;
import com.elit.casher_backend.model.dto.LevelDTO;
import com.elit.casher_backend.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/level")
public class LevelController {

    @Autowired
    LevelService levelService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Level> getLevel(@PathVariable Long id) {
        return levelService.getLevelById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Level> getLevels() {
        return levelService.getLevels();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> post(@RequestBody LevelDTO levelDTO) {
        return levelService.save(levelDTO);
    }

//    @RequestMapping(value = "/", method = RequestMethod.PUT)
//    public ResponseEntity<?> put(@RequestBody LevelDTO levelDTO) {
//        return levelService.save(levelDTO);
//    }
}
