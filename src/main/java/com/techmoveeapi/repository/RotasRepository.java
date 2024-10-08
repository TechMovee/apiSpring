package com.techmoveeapi.repository;

import com.techmoveeapi.model.Rotas;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RotasRepository extends JpaRepository<Rotas, Integer> {
    Optional<Rotas> findById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Rotas e WHERE e.id = ?1")
    void deleteByCpf(int id);
}
