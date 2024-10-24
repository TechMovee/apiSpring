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
        // Busca o maior enderecoId existente e incrementa
        Integer maxEnderecoId = enderecosRepository.findMaxEnderecoId();
        Integer novoEnderecoId = (maxEnderecoId != null ? maxEnderecoId + 1 : 1);

        // Define o enderecoId no novo endereço
        endereco.setId(novoEnderecoId);
        return this.enderecosRepository.save(endereco);
    }

    public Endereco buscarEnderecoPorId(int id){
        return this.enderecosRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Endereço não encontrado"));
    }

    public List<Endereco> buscarTodosEnderecos(){
        return this.enderecosRepository.findAll();
    }

    public Endereco excluirEndereco(int id) {
        Optional<Endereco> Endereco = this.enderecosRepository.findById(id);
        if (Endereco.isPresent()) {
            this.enderecosRepository.deleteById(id);
            return Endereco.get();
        }
        return null;
    }
}
