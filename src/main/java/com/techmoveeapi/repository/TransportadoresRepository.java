package com.techmoveeapi.repository;

import com.techmoveeapi.model.Telefones;
import com.techmoveeapi.model.Transportadores;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransportadoresRepository extends JpaRepository<Transportadores, String> {

    Optional<Transportadores> findById(String cpf);


    @Modifying
    @Transactional
    @Query("DELETE FROM Transportadores e WHERE e.cpf = ?1")
    void deleteByCpf(String cpf);
}
