package com.techmoveeapi.services;

import com.techmoveeapi.model.Fotos;
import com.techmoveeapi.repository.FotosRepository;
import com.techmoveeapi.repository.FotosRepository;
import com.techmoveeapi.repository.FotosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FotosService {
    private final FotosRepository fotosRepository;

    public FotosService(FotosRepository fotosRepository) {
        this.fotosRepository = fotosRepository;
    }

    public Fotos salvarFotos(Fotos foto){
        return this.fotosRepository.save(foto);
    }

    public Fotos buscarFotosPorID(int id){
        return this.fotosRepository.findByID(id).orElseThrow(() ->
                new RuntimeException("Foto não encontrada"));
    }

    public List<Fotos> buscarTodosFotos(){
        return this.fotosRepository.findAll();
    }

    public Fotos excluirFotos(int id) {
        Optional<Fotos> Fotos = this.fotosRepository.findByID(id);
        if (Fotos.isPresent()) {
            this.fotosRepository.deleteById(id);
            return Fotos.get();
        }
        return null;
    }
}
