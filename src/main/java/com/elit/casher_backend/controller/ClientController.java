package com.elit.casher_backend.controller;

import com.elit.casher_backend.model.Client;
import com.elit.casher_backend.model.dto.ClientDTO;
import com.elit.casher_backend.model.dto.ClientRequestDTO;
import com.elit.casher_backend.model.dto.ClientResponseDTO;
import com.elit.casher_backend.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ClientService clientService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Client> getClient(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @RequestMapping(value = "/paged", method = RequestMethod.GET)
    public Page<ClientDTO> getPaged(@RequestParam String name, @RequestParam String email, @RequestParam String cpf, @RequestParam Integer page, @RequestParam Integer size) {
        Page<Client> clients = clientService.getAllPaged(name, email, cpf, page, size);
        return convertPageToPageDTO(clients);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Integer getClientsCount() {
        return clientService.getClientsCount();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Client createClient(@RequestBody ClientRequestDTO clientCreationDTO) throws Exception {
        return clientService.saveClient(clientCreationDTO);
    }

    private Page<ClientDTO> convertPageToPageDTO(Page<Client> clients) {
        Page<ClientDTO> clientDTO = clients.map(new Function<Client, ClientDTO>() {
            @Override
            public ClientDTO apply(Client entity) {
                ClientDTO dto = new ClientDTO();

                dto.setId(entity.getId());
                dto.setName(entity.getName());
                dto.setEmail(entity.getEmail());
                dto.setCpf(entity.getCpf());
                dto.setProvider(entity.getProvider());
                dto.setType(entity.getType());
                dto.setCreated_at(entity.getCreatedAt());
                dto.setUpdated_at(entity.getUpdatedAt());
                dto.setActive(entity.getActive());

                return dto;
            }
        });
        return clientDTO;
    }
}
