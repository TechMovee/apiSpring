package com.techmoveeapi.services;

import com.techmoveeapi.model.Aluno;
import com.techmoveeapi.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno salvarAluno(Aluno aluno){
        return this.alunoRepository.save(aluno);
    }

    public Aluno buscarAlunoPorCpf(String cpf){
        return alunoRepository.findByCpf(cpf).orElseThrow(() ->
                new RuntimeException("Aluno não encontardo"));
    }

    public List<Aluno> buscarTodosAlunos(){
        return this.alunoRepository.findAll();
    }

    public Aluno excluirAluno(String cpf) {
        Optional<Aluno> aluno = this.alunoRepository.findByCpf(cpf); // Renomeia para aluno
        if (aluno.isPresent()) {
            this.alunoRepository.deleteByCpf(cpf); // Deleta pelo CPF
            return aluno.get(); // Retorna o aluno deletado
        }
        return null; // Pode ser uma exceção ou outro valor adequado
    }
}
