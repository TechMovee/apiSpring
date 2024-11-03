package com.techmoveeapi.controllers;

import com.techmoveeapi.model.*;
import com.techmoveeapi.model.Escola;
import com.techmoveeapi.model.Escola;
import com.techmoveeapi.model.Escola;
import com.techmoveeapi.services.EscolaService;
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
@RequestMapping("api/escolas")
@Validated
@Controller
public class EscolaController {
    private final EscolaService escolaService;
    private final Validator validador;

    public EscolaController(Validator validador, EscolaService escolaService) {
        this.escolaService = escolaService;
        this.validador = validador;
    }

    @GetMapping("/selecionar")
    @Operation(summary = "Selecionar todas as escolas",
            description = "Selecionar todas as escolas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas os escola selecionadas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno da servidar",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class)))
    })
    public ResponseEntity<List<Escola>> listarEnderencos(){
        List<Escola> listaEscolas = escolaService.buscarTodosEscolas();
        return ResponseEntity.ok(listaEscolas);
    }

    @GetMapping("/buscarPorId/{id}")
    @Operation(summary = "Buscar uma escola pelo id", description = "Busca a escola pelo id e mostra seus dadas ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Escola selecionada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno da servidar",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class)))
    })
    public ResponseEntity<?> buscarPorId(@Parameter(description = "Id da escola") @RequestParam int id){
        Escola escola = escolaService.buscarEscolaPorId(id);
        if (escola != null) {
            return ResponseEntity.ok(escola);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Escola não encontrada");
        }
    }

    @PostMapping("/inserirEscola")
    @Operation(summary = "Insere uma nova escola",
            description = "Insere uma nova escola")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Escola inserida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidar",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class)))
    })
    public ResponseEntity<?> inserirEscola(@Valid @RequestBody Escola escola) {
        escolaService.salvarEscola(escola);
        return ResponseEntity.ok().body("Escola inserida");
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir uma escola", description = "remover uma escola do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Escola excluída com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class))),
            @ApiResponse(responseCode = "404", description = "Escola não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class)))
    })
    public ResponseEntity<String> excluirEscola(@Parameter(description = "Id da escola") @Valid @PathVariable int id) {
        Escola escola = escolaService.buscarEscolaPorId(id);
        if (escola != null){
            escolaService.excluirEscola(id);
            return ResponseEntity.ok("deu certo");
        }else {
            return  ResponseEntity.ok("deu ruim");
        }
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar uma escola", description = "Atualiza uma escola ja existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Escola atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class))),
            @ApiResponse(responseCode = "404", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class)))
    })
    public ResponseEntity<String> atualizarEscola(@Parameter(description = "ID da escola")    @Valid @PathVariable int id, @RequestBody Escola escolaAtualizada) {
        Escola escolaExistente = escolaService.buscarEscolaPorId(id);
        if (escolaExistente != null) {
            Escola escola = escolaExistente;
            escola.setNome(escolaAtualizada.getNome());
            escola.setEndereco_id(escolaAtualizada.getEndereco_id());

            escolaService.salvarEscola(escola);
            return ResponseEntity.ok("Escola atualizada com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente as informações de um escola", description = "Atualiza uma parte das informações do escola")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Escola atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class))),
            @ApiResponse(responseCode = "404", description = "Escola não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Escola.class)))
    })
    public  ResponseEntity<?> atualizarEscolaParcial(@Parameter(description = "Id do escola") @PathVariable int id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto com as novas informações",content = @Content(schema = @Schema(type = "object",example = "{\"nome\": \"NOME\","+"\"endere_id\": \"ENDERECO_ID\"}")) ) @RequestBody Map<String, Object> updates) {
        try{
            Escola escola = escolaService.buscarEscolaPorId(id);
            if (updates.containsKey("nome")) {
                escola.setNome((String) updates.get("nome"));
            }
            if (updates.containsKey("endereco_id")) {
                escola.setEndereco_id((Endereco) updates.get("endereco_id"));
            }


            //validar
            DataBinder binder = new DataBinder(escola);
            binder.setValidator(validador);
            binder.validate();
            BindingResult result = binder.getBindingResult();
            if (result.hasErrors()){
                Map erros = handlerValidator(result);
                return ResponseEntity.badRequest().body(erros);
            }
            Escola produtosalvo = escolaService.salvarEscola(escola);
            return ResponseEntity.ok("Escola atualizada parcialmente com sucesso");
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Escola com Id " + id + " não encontrada");
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
