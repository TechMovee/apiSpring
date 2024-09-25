package com.techmoveeapi.controllers;

import com.techmoveeapi.model.*;
import com.techmoveeapi.model.Rotas;
import com.techmoveeapi.model.Rotas;
import com.techmoveeapi.model.Rotas;
import com.techmoveeapi.services.RotasService;
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
@RequestMapping("api/rotas")
@Validated
@Controller
public class RotasController {
    private final RotasService rotasService;
    private final Validator validador;

    public RotasController(Validator validador, RotasService rotasService){
        this.rotasService = rotasService;
        this.validador = validador;
    }

    @GetMapping("/selecionar")
    @Operation(summary = "Selecionar todas as rotas",
            description = "Selecionar todas as rotas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas as rotas selecionadas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class)))
    })
    public ResponseEntity<List<Rotas>> listarRotas(){
        List<Rotas> listaRotas = rotasService.buscarTodasRotas();
        return ResponseEntity.ok(listaRotas);
    }

    @GetMapping("/buscarPorId/{id}")
    @Operation(summary = "Buscar uma nova rota pelo id", description = "Busca a rota pelo id e mostra seus dados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rotas selecionado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class)))
    })
    public ResponseEntity<?> buscarPorId(@Parameter(description = "Id da rota") @RequestParam int id){
        Rotas rota = rotasService.buscarRotaPorId(id);
        if (rota != null) {
            return ResponseEntity.ok(rota);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rota não encontrada");
        }
    }


    @PostMapping("/inserirRota")
    @Operation(summary = "Insere uma nova rota",
            description = "Insere uma nova rota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rota inserida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class)))
    })
    public ResponseEntity<?> inserirRota(@Valid @RequestBody Rotas rota) {
        rotasService.salvarRota(rota);
        return ResponseEntity.ok().body("Rota inserida");
    }


    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir uma rota", description = "remover uma rota do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rota excluída com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class))),
            @ApiResponse(responseCode = "404", description = "Rota não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class)))
    })
    public ResponseEntity<String> excluirRota(@Parameter(description = "Id da rota") @Valid @PathVariable int id) {
        Rotas rota = rotasService.buscarRotaPorId(id);
        if (rota != null){
            rotasService.excluirRota(id);
            return ResponseEntity.ok("deu certo");
        }else {
            return  ResponseEntity.ok("deu ruim");
        }
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar uma rota", description = "Atualiza uma rota ja existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rotas atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class))),
            @ApiResponse(responseCode = "404", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class)))
    })
    public ResponseEntity<String> atualizarRota(@Parameter(description = "Id da rota")    @Valid @PathVariable int id, @RequestBody Rotas rotaAtualizada) {
        Rotas rotaExistente = rotasService.buscarRotaPorId(id);
        if (rotaExistente != null) {
            Rotas rota = rotaExistente;
            rota.setNome(rotaAtualizada.getNome());
            rota.setPeriodo(rotaAtualizada.getPeriodo());
            rota.setQuantidade_paradas(rotaAtualizada.getQuantidade_paradas());
            rota.setTempo(rotaAtualizada.getTempo());
            rota.setEndereco_id(rotaAtualizada.getEndereco_id());


            rotasService.salvarRota(rota);
            return ResponseEntity.ok("Rota atualizada com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente as informações de uma rota", description = "Atualiza uma parte das informações do rota")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rotas atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class))),
            @ApiResponse(responseCode = "404", description = "Rotas não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Rotas.class)))
    })
    public  ResponseEntity<?> atualizarRotaParcial(@Parameter(description = "Id da rota") @PathVariable int id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto com as novas informações",content = @Content(schema = @Schema(type = "object",example = "{\"nome\": \"NOME\","+"\"periodo\": \"PERIODO\","+"\"quantidade_paradas\": \"QUANTIDADE_PARADAS\","+"\"tempo\": \"TEMPO\","+"\"endereco_id\": \"ENDERECO_ID\" }")) ) @RequestBody Map<String, Object> updates) {
        try{
            Rotas rota = rotasService.buscarRotaPorId(id);
            if (updates.containsKey("nome")) {
                rota.setNome((String) updates.get("nome"));
            }
            if (updates.containsKey("periodo")) {
                rota.setPeriodo((String) updates.get("periodo"));
            }
            if (updates.containsKey("quantidade_paradas")) {
                rota.setQuantidade_paradas((Integer) updates.get("quantidade_paradas"));
            }
            if (updates.containsKey("tempo")) {
                rota.setTempo((String) updates.get("tempo"));
            }
            if (updates.containsKey("endereco_id")) {
                rota.setEndereco_id((Integer) updates.get("endereco_id"));
            }


            //validar
            DataBinder binder = new DataBinder(rota);
            binder.setValidator(validador);
            binder.validate();
            BindingResult result = binder.getBindingResult();
            if (result.hasErrors()){
                Map erros = handlerValidator(result);
                return ResponseEntity.badRequest().body(erros);
            }
            Rotas produtosalvo = rotasService.salvarRota(rota);
            return ResponseEntity.ok("Rota atualizada parcialmente com sucesso");
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Rota com Id " + id + " não encontrada");
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handlerValidator(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()){
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }


}
