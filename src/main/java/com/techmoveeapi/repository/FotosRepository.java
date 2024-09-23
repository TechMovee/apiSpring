package com.techmoveeapi.repository;

import com.techmoveeapi.model.Endereco;
import com.techmoveeapi.model.Fotos;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FotosRepository extends JpaRepository<Fotos, Integer> {
    Optional<Fotos> findById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Fotos e WHERE e.id = ?1")
    void deleteById(int id);
}
