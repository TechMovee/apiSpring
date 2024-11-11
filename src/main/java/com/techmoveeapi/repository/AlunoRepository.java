package com.techmoveeapi.repository;

import com.techmoveeapi.model.Aluno;
import com.techmoveeapi.model.Responsaveis;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, String> {
    @Query("SELECT e FROM Aluno e WHERE e.cpf = ?1")
    Optional<Aluno> findByCpf(String cpf);

    @Query("SELECT e FROM Aluno e WHERE e.responsavel_cpf = ?1")
    Optional<Aluno> findByResponsavel_cpf(String cpf);

    @Modifying
    @Transactional
    @Query("DELETE FROM Aluno e WHERE e.cpf = ?1")
    void deleteByCpf(String cpf);
}

