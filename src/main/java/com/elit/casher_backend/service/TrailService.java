package com.elit.casher_backend.service;

import com.elit.casher_backend.model.Trail;
import com.elit.casher_backend.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrailService {

    @Autowired
    TrailRepository trailRepository;

    public Trail getTrailById(Long id){
        return trailRepository.getReferenceById(id);
    }

}
