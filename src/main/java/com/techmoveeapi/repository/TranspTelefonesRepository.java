package com.techmoveeapi.repository;

import com.techmoveeapi.model.TransportadoresTelefones;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranspTelefonesRepository extends JpaRepository<TransportadoresTelefones, String> {
    Optional<TransportadoresTelefones> findByID(String transportador_cnh);

    @Modifying
    @Transactional
    @Query("DELETE FROM TransportadoresTelefones e WHERE e.transportador_cnh = ?1")
    void deleteByCpf(String transportador_cnh);
}
