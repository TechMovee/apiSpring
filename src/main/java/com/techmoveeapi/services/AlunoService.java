package com.techmoveeapi.services;

import com.techmoveeapi.model.Aluno;
import com.techmoveeapi.repository.AlunoRepository;

import java.util.List;
import java.util.Optional;

public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno salvarAluno(Aluno aluno){
        return this.alunoRepository.save(aluno);
    }

    public Optional<Aluno> buscarAlunoPorCpf(String cpf){
        return this.alunoRepository.findByCpf(cpf);
    }

    public List<Aluno> buscarTodosAlunos(){
        return this.alunoRepository.findAll();
    }

    public Aluno excluirAluno(String cpf){
        Optional<Aluno> alunos = this.alunoRepository.findByCpf(cpf);

        if (alunos.isPresent()){
            this.alunoRepository.deleteByCpf(cpf);
            return alunos.get();
        }
        return null;
    }
}
