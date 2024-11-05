package com.techmoveeapi.services;

import com.techmoveeapi.model.Van;
import com.techmoveeapi.repository.VanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VanService {
    private final VanRepository vanRepository;
    public VanService(VanRepository vanRepository) {
        this.vanRepository = vanRepository;
    }

    public Van salvarVan(Van van){
        return this.vanRepository.save(van);
    }

    public Van buscarVanPorPlaca(String placa){
        return vanRepository.findByPlaca(placa).orElseThrow(() ->
                new RuntimeException("Van não encontardo"));
    }

    public List<Van> buscarVanPorOrdemCapacidade(){
        return this.vanRepository.findAllByOrderByCapacidadeDesc();
    }

    public List<Van> buscarVanPorMenorMensalidade(){
        return this.vanRepository.findAllByOrderByMensalidadeAsc();
    }

    public List<Van> buscarVanAcessivel(){
        return this.vanRepository.findByAcessibilidadeTrue();
    }

    public List<Van> buscarTodasVans(){
        return this.vanRepository.findAll();
    }

    public Van excluirVan(String placa) {
        Optional<Van> van = this.vanRepository.findByPlaca(placa);
        if (van.isPresent()) {
            this.vanRepository.deleteByPlaca(placa);
            return van.get();
        }
        return null;
    }

}
