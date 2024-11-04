package com.techmoveeapi.services;

import com.techmoveeapi.model.Telefones;
import com.techmoveeapi.repository.TelefonesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelefonesService {
    private final TelefonesRepository telefonesRepository;

    public TelefonesService(TelefonesRepository telefonesRepository) {
        this.telefonesRepository = telefonesRepository;
    }

    public Telefones salvarTelefone(Telefones telefones) {
        return this.telefonesRepository.save(telefones);
    }

    public Telefones buscarTelefonePorId(int id){
        return telefonesRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Telefone não encontardo"));
    }

    public List<Telefones> buscarTodosTelefones(){
        return this.telefonesRepository.findAll();
    }


    public Telefones excluirTelefone(int id) {
        Optional<Telefones> telefones = this.telefonesRepository.findById(id);
        if (telefones.isPresent()) {
            this.telefonesRepository.deleteById(id);
            return telefones.get();
        }
        return null;
    }




}
