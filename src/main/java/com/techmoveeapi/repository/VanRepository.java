package com.techmoveeapi.repository;

import com.techmoveeapi.model.Van;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VanRepository extends JpaRepository<Van, String> {
    Optional<Van> findByPlaca(String placa);

    @Query("SELECT m FROM Van m ORDER BY m.capacidade DESC")
    List<Van> findAllByOrderByCapacidadeDesc();

    @Query("SELECT m FROM Van m ORDER BY m.mensalidade ASC")
    List<Van> findAllByOrderByMensalidadeAsc();

    List<Van> findByAcessibilidadeTrue();

    @Modifying
    @Transactional
    @Query("DELETE FROM Van e WHERE e.placa = ?1")
    void deleteByPlaca(String placa);
}
