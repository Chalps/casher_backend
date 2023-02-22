package com.elit.casher_backend.repository;

import com.elit.casher_backend.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM casher_db.level")
    List<Level> getLevels();

    @Query(nativeQuery = true, value = "SELECT * FROM casher_db.level l " +
            "WHERE l.name = ?1 ")
    Level findByName(String levelName);
}
