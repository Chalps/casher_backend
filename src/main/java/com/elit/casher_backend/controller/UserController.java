package com.elit.casher_backend.controller;

import com.elit.casher_backend.model.User;
import com.elit.casher_backend.model.dto.UserRequestDTO;
import com.elit.casher_backend.model.dto.UserResponseDTO;
import com.elit.casher_backend.model.enums.UserType;
import com.elit.casher_backend.repository.UserRepository;
import com.elit.casher_backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.jboss.logging.Logger;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String getResponse() {
        return "Chamada Funcionando";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public User createUser(@RequestBody UserRequestDTO userCreationDTO){
        logger.info(String.format("Saving new user: %s on database", userCreationDTO.getEmail()));

        userCreationDTO.setPassword(encoder.encode(userCreationDTO.getPassword()));

        User user = convertToEntity(userCreationDTO);

        user.setLeagueId(1);
        user.setLevelId(1);
        user.setType(UserType.toEnum(userCreationDTO.getType()));
        user.setCasherPoints(0);
        user.setMoney(0);
        user.setStreak(0);

        try {
            user = userRepository.save(user);
            return user;
        } catch (Exception e) {

            logger.info("cause: " + e.getCause() + "code: " + e.getClass());

            if(e.getClass().equals(DataIntegrityViolationException.class)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Violação de constraint UNIQUE");

            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    private User convertToEntity(UserRequestDTO userCreationDTO) {

        return modelMapper.map(userCreationDTO, User.class);
    }

    private UserResponseDTO convertToDto(User userModel) {

        return modelMapper.map(userModel, UserResponseDTO.class);
    }
}
