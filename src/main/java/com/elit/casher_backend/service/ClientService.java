package com.elit.casher_backend.service;

import com.elit.casher_backend.model.Client;
import com.elit.casher_backend.model.dto.ClientRequestDTO;
import com.elit.casher_backend.model.enums.ClientType;
import com.elit.casher_backend.repository.ClientRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ClientRepository clientRepository;


    public Optional<Client> getClientById(Long id){
        return clientRepository.findById(id);
    }

    public List<Client> getClients() {
        return clientRepository.getClients();
    }

    public Integer getClientsCount() {
        return clientRepository.getClientsCount();
    }

    public Page<Client> getAllPaged(String name, String email, String cpf, Integer page, Integer size) {
        if (name.isEmpty()) name = null;
        if (cpf.isEmpty()) cpf = null;
        if (email.isEmpty()) email = null;
        Pageable paging = PageRequest.of(page, size);
        return clientRepository.findAllPaged(name, email, cpf, paging);
    }

    public Client saveClient(ClientRequestDTO clientRequestDTO) throws Exception {
        Client user = new Client();
        if (clientRequestDTO.getCpf() == null) throw new Exception("O CPF é obrigatório");
        logger.info(String.format("Saving new user: %s on database", clientRequestDTO.getEmail()));

        if (clientRequestDTO.getPassword() != null)
            clientRequestDTO.setPassword(encoder.encode(clientRequestDTO.getPassword()));

        user.setName(clientRequestDTO.getName());
        user.setNickName(clientRequestDTO.getNickName());
        user.setEmail(clientRequestDTO.getEmail());
        user.setPassword(clientRequestDTO.getPassword());
        user.setPhoneNumber(clientRequestDTO.getPhoneNumber());
        user.setCpf(clientRequestDTO.getCpf());
        user.setBirthDate(DateUtils.parseDate(clientRequestDTO.getBirthDate(), "dd/MM/yyyy"));
        user.setLeagueId(1);
        user.setLevelId(1);
        user.setType(ClientType.toEnum(clientRequestDTO.getType()));
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());
        user.setCasherPoints(0);
        user.setMoney(0);
        user.setStreak(0);

        try {
            user = clientRepository.save(user);
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

    private Client convertToEntity(ClientRequestDTO userCreationDTO) {
        return modelMapper.map(userCreationDTO, Client.class);
    }
}
