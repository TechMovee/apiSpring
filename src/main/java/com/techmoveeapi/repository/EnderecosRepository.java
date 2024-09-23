package com.techmoveeapi.repository;

import com.techmoveeapi.model.Endereco;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecosRepository extends JpaRepository<Endereco, Integer> {
    Optional<Endereco> findById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Endereco e WHERE e.id = ?1")
    void deleteById(int id);
}
