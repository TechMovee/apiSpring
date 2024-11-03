package com.techmoveeapi.repository;

import com.techmoveeapi.model.Escola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EscolaRepository extends JpaRepository<Escola, Integer> {

    Optional<Escola> findById(Integer id);

}
