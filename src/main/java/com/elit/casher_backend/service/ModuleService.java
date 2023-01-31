package com.elit.casher_backend.service;

import com.elit.casher_backend.model.Module;
import com.elit.casher_backend.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    public Module getModuleById(Long id){
        return moduleRepository.getReferenceById(id);
    }

}
