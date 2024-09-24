package com.techmoveeapi.services;

import com.techmoveeapi.model.Rotas;
import com.techmoveeapi.repository.RotasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RotasService {
    private final RotasRepository rotasRepository;

    public RotasService(RotasRepository rotasRepository) {
        this.rotasRepository = rotasRepository;
    }

    public Rotas salvarRota(Rotas rota){
        return this.rotasRepository.save(rota);
    }

    public Rotas buscarRotaPorId(int id){
        return this.rotasRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Endereço não encontrado"));
    }

    public List<Rotas> buscarTodasRotas(){
        return this.rotasRepository.findAll();
    }

    public Rotas excluirRota(int id) {
        Optional<Rotas> Rotas = this.rotasRepository.findById(id);
        if (Rotas.isPresent()) {
            this.rotasRepository.deleteById(id);
            return Rotas.get();
        }
        return null;
    }
}
