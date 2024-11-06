package com.techmoveeapi.controllers;

import com.techmoveeapi.model.Fotos;
import com.techmoveeapi.model.Fotos;
import com.techmoveeapi.model.Fotos;
import com.techmoveeapi.services.FotosService;
import com.techmoveeapi.services.FotosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/fotos")
@Validated
@Controller
public class FotosController {

    private final FotosService fotosService;
    private final Validator validador;

    public FotosController(Validator validador, FotosService fotosService){
        this.fotosService = fotosService;
        this.validador = validador;
    }

    @GetMapping("/selecionar")
    @Operation(summary = "Selecionar todos as fotos",
            description = "Selecionar todos as fotos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas as fotos selecionadas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class)))
    })
    public ResponseEntity<List<Fotos>> listarEnderencos(){
        List<Fotos> listaFotos = fotosService.buscarTodosFotos();
        return ResponseEntity.ok(listaFotos);
    }

    @GetMapping("/buscarPorId/{id}")
    @Operation(summary = "Buscar uma nova foto pelo id", description = "Busca a foto pelo id e mostra seus dados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Foto selecionada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class)))
    })
    public ResponseEntity<?> buscarPorId(@Parameter(description = "Id da foto") @RequestParam int id){
        Fotos foto = fotosService.buscarFotosPorID(id);
        if (foto != null) {
            return ResponseEntity.ok(foto);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto não encontrada");
        }
    }

    @PostMapping("/inserirFotos")
    @Operation(summary = "Insere uma nova foto",
            description = "Insere uma nova foto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Foto inserida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class)))
    })
    public ResponseEntity<?> inserirFotos(@Valid @RequestBody Fotos foto) {
        fotosService.salvarFotos(foto);
        return ResponseEntity.ok(foto);
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir uma foto", description = "remover uma foto do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Foto excluída com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class))),
            @ApiResponse(responseCode = "404", description = "Foto não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class)))
    })
    public ResponseEntity<String> excluirFotos(@Parameter(description = "Id da foto") @Valid @PathVariable int id) {
        Fotos foto = fotosService.buscarFotosPorID(id);
        if (foto != null){
            fotosService.excluirFotos(id);
            return ResponseEntity.ok("deu certo");
        }else {
            return  ResponseEntity.ok("deu ruim");
        }
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar uma foto", description = "Atualiza uma foto ja existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Foto atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class))),
            @ApiResponse(responseCode = "404", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Fotos.class)))
    })
    public ResponseEntity<String> atualizarFoto(@Parameter(description = "ID da foto")    @Valid @PathVariable int id, @RequestBody Fotos fotoAtualizada) {
        Fotos fotoExistente = fotosService.buscarFotosPorID(id);
        if (fotoExistente != null) {
            Fotos foto = fotoExistente;
            foto.setUrl(fotoAtualizada.getUrl());

            fotosService.salvarFotos(foto);
            return ResponseEntity.ok("Fotos atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
