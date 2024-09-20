package com.techmoveeapi.services;

import com.techmoveeapi.model.Endereco;
import com.techmoveeapi.repository.EnderecosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecosService {
    private final EnderecosRepository enderecosRepository;

    public EnderecosService(EnderecosRepository enderecosRepository) {
        this.enderecosRepository = enderecosRepository;
    }

    public Endereco salvarEndereco(Endereco endereco){
        return this.enderecosRepository.save(endereco);
    }

    public Endereco buscarEnderecoPorID(int id){
        return this.enderecosRepository.findByID(id).orElseThrow(() ->
                new RuntimeException("Endereço não encontrado"));
    }

    public List<Endereco> buscarTodosEnderecos(){
        return this.enderecosRepository.findAll();
    }

    public Endereco excluirEndereco(int id) {
        Optional<Endereco> Endereco = this.enderecosRepository.findByID(id);
        if (Endereco.isPresent()) {
            this.enderecosRepository.deleteById(id);
            return Endereco.get();
        }
        return null;
    }
}
