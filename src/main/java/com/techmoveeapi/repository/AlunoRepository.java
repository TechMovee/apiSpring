package com.techmoveeapi.repository;

import com.techmoveeapi.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, String> {
    Optional<Aluno> findByCpf(String cpf);

    @Modifying
    @Query("DELETE FROM Aluno e WHERE e.cpf = ?1")
    void deleteByCpf(String cpf);
}

