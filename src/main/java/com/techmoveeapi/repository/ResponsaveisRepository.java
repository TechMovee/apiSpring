package com.techmoveeapi.repository;

import com.techmoveeapi.model.Fotos;
import com.techmoveeapi.model.Responsaveis;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponsaveisRepository extends JpaRepository<Responsaveis, String> {

    @Query("SELECT e FROM Responsaveis e WHERE e.cpf = ?1")
    Optional<Responsaveis> findByCpf(String cpf);

    @Modifying
    @Transactional
    @Query("DELETE FROM Responsaveis e WHERE e.cpf = ?1")
    void deleteByCpf(String cpf);

    @Query("SELECT e FROM Responsaveis e WHERE e.email = ?1")
    Optional<Responsaveis> findByEmail(String email);
}
