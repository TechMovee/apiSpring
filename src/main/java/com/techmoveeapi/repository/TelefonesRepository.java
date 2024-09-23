package com.techmoveeapi.repository;

import com.techmoveeapi.model.Telefones;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelefonesRepository extends JpaRepository<Telefones, Integer> {
    Optional<Telefones> findByID(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Telefones e WHERE e.id = ?1")
    void deleteByCpf(int id);
}
