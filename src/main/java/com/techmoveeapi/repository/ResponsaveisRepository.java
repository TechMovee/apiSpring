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
//    @Query("SELECT t1, t2 FROM Responsaveis t1 JOIN Endereco t2 ON t1.endereco_id = t2.id WHERE t1.endereco_id = ?1")
//    Optional<Responsaveis> findByCpf(String cpf);

    @Query("SELECT r FROM Responsaveis r " +
            "JOIN Endereco e ON r.endereco_id.id = e.id " +
            "JOIN Fotos f ON r.foto_id.id = f.id " +
            "JOIN Aluno a ON a.responsavel_cpf = r.cpf " +
//            "JOIN Telefones t ON r.telefones.id = t.id " +
            "WHERE r.cpf = ?1")
    Optional<Responsaveis> findByCpf(String cpf);

    @Modifying
    @Transactional
    @Query("DELETE FROM Responsaveis e WHERE e.cpf = ?1")
    void deleteByCpf(String cpf);
}
