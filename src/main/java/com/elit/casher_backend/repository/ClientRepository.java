package com.elit.casher_backend.repository;

import com.elit.casher_backend.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM casher.client ")
    List<Client> getClients();

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM casher.client ")
    Integer getClientsCount();

    @Query(nativeQuery = true, value = "SELECT * FROM casher.client c " +
            "WHERE (lower(c.name) like lower(concat('%', ?1,'%'))  OR ?1 IS NULL) " +
            "AND (c.cpf like concat('%', ?2,'%') OR ?2 IS NULL) " +
            "AND (c.email like concat('%', ?3,'%') OR ?3 IS NULL) ")
    Page<Client> findAllPaged(String name, String cpf, String email, Pageable pageable);
}
