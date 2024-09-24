package com.techmoveeapi.services;

import com.techmoveeapi.model.Aluno;
import com.techmoveeapi.model.Responsaveis;
import com.techmoveeapi.repository.AlunoRepository;
import com.techmoveeapi.repository.ResponsaveisRepository;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ResponsaveisService {
    private final ResponsaveisRepository responsaveisRepository;
    public ResponsaveisService(ResponsaveisRepository responsaveisRepository) {
        this.responsaveisRepository = responsaveisRepository;
    }

    public Responsaveis salvarResponsavel(Responsaveis responsaveis){
        return this.responsaveisRepository.save(responsaveis);
    }

    public Responsaveis buscarResponsavelPorCpf(String cpf){
        return responsaveisRepository.findByCpf(cpf).orElseThrow(() ->
                new RuntimeException("Responsavel não encontardo"));
    }
    public List<Responsaveis> buscarTodosResponsaveis(){
        return this.responsaveisRepository.findAll();
    }

    public Responsaveis excluirResponsavel(String cpf) {
        Optional<Responsaveis> responsavel = this.responsaveisRepository.findByCpf(cpf);
        if (responsavel.isPresent()) {
            this.responsaveisRepository.deleteByCpf(cpf);
            return responsavel.get();
        }
        return null;
    }


}
