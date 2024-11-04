package com.techmoveeapi.services;

import com.techmoveeapi.model.*;
import com.techmoveeapi.repository.EnderecosRepository;
import com.techmoveeapi.repository.FotosRepository;
import com.techmoveeapi.repository.ResponsaveisRepository;
import com.techmoveeapi.repository.TelefonesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResponsaveisService {
    @Autowired
    private ResponsaveisRepository responsavelRepository;

    @Autowired
    private TelefonesRepository telefonesRepository;

    @Autowired
    private FotosRepository fotoRepository;

    @Autowired
    private EnderecosRepository enderecosRepository;

    public List<Responsaveis> getAllResponsaveis() {
        return responsavelRepository.findAll();
    }

    public Responsaveis getResponsaveisByCpf(String cpf) {
        return responsavelRepository.findByCpf(cpf).orElseThrow(() ->
                new RuntimeException("Responsável não encontardo"));
    }

    public Responsaveis getResponsaveisByEmail(String email) {
        return responsavelRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException("Responsável não encontardo"));
    }

    public Responsaveis createResponsaveis(Responsaveis responsavel) {
        // Salvar o responsável
        return responsavelRepository.save(responsavel);

    }

    public Responsaveis updateResponsaveis(String cpf, Responsaveis updatedResponsaveis) {
        return responsavelRepository.findById(cpf)
                .map(responsavel -> {
                    responsavel.setNome(updatedResponsaveis.getNome());
                    responsavel.setDt_nascimento(updatedResponsaveis.getDt_nascimento());
                    responsavel.setEmail(updatedResponsaveis.getEmail());
                    responsavel.setSenha(updatedResponsaveis.getSenha());
                    responsavel.setFoto_id(updatedResponsaveis.getFoto_id());
                    responsavel.setEndereco_id(updatedResponsaveis.getEndereco_id());
                    return responsavelRepository.save(responsavel);
                })
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));
    }

    public void deleteResponsaveis(String cpf) {
        responsavelRepository.deleteById(cpf);
    }





    public Responsaveis partialUpdateResponsavel(String cpf, Map<String, Object> updates) {
        Responsaveis responsavel = responsavelRepository.findById(cpf)
                .orElseThrow(() -> new RuntimeException("Responsável não encontrado"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "nome":
                    responsavel.setNome((String) value);
                    break;
                case "dt_nascimento":
                    responsavel.setDt_nascimento(LocalDate.parse((String) value));
                    break;
                case "email":
                    responsavel.setEmail((String) value);
                    break;
                case "senha":
                    responsavel.setSenha((String) value);
                    break;
                case "foto":
                    responsavel.setFoto_id((Fotos) value);  // Supondo que seja um objeto Foto
                    break;
                case "endereco":
                    responsavel.setEndereco_id((Endereco) value);  // Supondo que seja um objeto Endereco
                    break;
                // Adicione outros campos que podem ser atualizados parcialmente
            }
        });

        return responsavelRepository.save(responsavel);
    }
}
