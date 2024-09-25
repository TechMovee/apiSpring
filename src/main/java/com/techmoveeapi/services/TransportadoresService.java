package com.techmoveeapi.services;

import com.techmoveeapi.model.Transportadores;
import com.techmoveeapi.repository.TransportadoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportadoresService {

    private final TransportadoresRepository transportadoresRepository;
    public TransportadoresService(TransportadoresRepository transportadoresRepository) {
        this.transportadoresRepository = transportadoresRepository;
    }

    public Transportadores salvarTransportador(Transportadores transportadores){
        return this.transportadoresRepository.save(transportadores);
    }

    public Transportadores buscarTransportadorPorCpf(String cpf){
        return transportadoresRepository.findByCpf(cpf).orElseThrow(() ->
                new RuntimeException("Transportador não encontardo"));
    }
    public List<Transportadores> buscarTodosTransportadores(){
        return this.transportadoresRepository.findAll();
    }

    public Transportadores excluirTransportador(String cpf) {
        Optional<Transportadores> transportador = this.transportadoresRepository.findByCpf(cpf);
        if (transportador.isPresent()) {
            this.transportadoresRepository.deleteByCpf(cpf);
            return transportador.get();
        }
        return null;
    }

}
