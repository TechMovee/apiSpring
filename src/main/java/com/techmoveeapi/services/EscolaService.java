package com.techmoveeapi.services;

import com.techmoveeapi.model.Escola;
import com.techmoveeapi.repository.EscolaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscolaService {
    private final EscolaRepository escolaRepository;

    public EscolaService(EscolaRepository escolaRepository) {
        this.escolaRepository = escolaRepository;
    }

    public Escola salvarEscola(Escola escola){
        return this.escolaRepository.save(escola);
    }

    public Escola buscarEscolaPorId(Integer id){
        return this.escolaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Escola não encontrada"));
    }

    public List<Escola> buscarTodosEscolas(){
        return this.escolaRepository.findAll();
    }

    public Escola excluirEscola(Integer id) {
        Optional<Escola> escola = this.escolaRepository.findById(id);
        if (escola.isPresent()) {
            this.escolaRepository.deleteById(id);
            return escola.get();
        }
        return null;
    }
}
