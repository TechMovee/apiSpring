package com.techmoveeapi.controllers;


import com.techmoveeapi.model.Responsaveis;
import com.techmoveeapi.services.ResponsaveisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.Id;
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

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/responsavel")
@Validated
@Controller
public class ResponsaveisController {
    private final ResponsaveisService responsaveisService;
    private final Validator validador;

    public ResponsaveisController(Validator validador, ResponsaveisService responsaveisService){

        this.responsaveisService = responsaveisService;
        this.validador = validador;
    }
    @GetMapping("/selecionar")
    @Operation(summary = "Selecionar todos os responsaveis",
            description = "Selecionar todos os responsaveis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos os responsaveis selecionados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class)))
    })
    public ResponseEntity<List<Responsaveis>> listarResponsaveis(){
        List<Responsaveis> listaResponsaveis = responsaveisService.buscarTodosResponsaveis();
        return ResponseEntity.ok(listaResponsaveis);
    }

    @GetMapping("/buscarPorCpf/{cpf}")
    @Operation(summary = "Buscar um novo responsavel pelo cpf", description = "Busca o responsavel pelo cpf e mostra seus dados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Responsavel selecionado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class)))
    })
    public ResponseEntity<?> buscarPorCpf(@Parameter(description = "Cpf do responsavel") @RequestParam String cpf){
        Responsaveis responsavel = responsaveisService.buscarResponsavelPorCpf(cpf);
        if (responsavel != null) {
            return ResponseEntity.ok(responsavel);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Responsavel não encontrado");
        }
    }

    @PostMapping("/inserirResponsavel")
    @Operation(summary = "Insere um novo responsavel",
            description = "Insere um novo responsavel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Responsaveis inserido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class)))
    })
    public ResponseEntity<?> inserirResponsaveis(@Valid @RequestBody Responsaveis responsavel) {
        responsaveisService.salvarResponsavel(responsavel);
        return ResponseEntity.ok().body("Responsaveis inserido");
    }


    @DeleteMapping("/excluir/{cpf}")
    @Operation(summary = "Excluir um responsavel", description = "remover um responsavel do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Responsaveis excluído com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class))),
            @ApiResponse(responseCode = "404", description = "Responsaveis não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class)))
    })
    public ResponseEntity<String> excluirResponsaveis(@Parameter(description = "CPF do responsavel") @Valid @PathVariable String cpf) {
        Responsaveis responsavel = responsaveisService.buscarResponsavelPorCpf(cpf);
        if (responsavel != null){
            responsaveisService.excluirResponsavel(cpf);
            return ResponseEntity.ok("deu certo");
        }else {
            return  ResponseEntity.ok("deu ruim");
        }
    }

    @PutMapping("/atualizar/{cpf}")
    @Operation(summary = "Atualizar um responsavel", description = "Atualiza um responsavel ja existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Responsavel atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class))),
            @ApiResponse(responseCode = "404", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class)))
    })
    public ResponseEntity<String> atualizarResponsavel(@Parameter(description = "Id do resposavel")    @Valid @PathVariable String cpf, @RequestBody Responsaveis resposavelAtualizado) {
        Responsaveis responsavelExistente = responsaveisService.buscarResponsavelPorCpf(cpf);
        if (responsavelExistente != null) {
            Responsaveis responsavel = responsavelExistente;
            responsavel.setDt_nascimento(resposavelAtualizado.getDt_nascimento());
            responsavel.setSexo(resposavelAtualizado.getSexo());
            responsavel.setCpf(resposavelAtualizado.getCpf());
            responsavel.setFoto(resposavelAtualizado.getFoto());
            responsavel.setSenha(resposavelAtualizado.getSenha());
            responsavel.setNome(resposavelAtualizado.getNome());
            responsavel.setEndereco_id(resposavelAtualizado.getEndereco_id());


            responsaveisService.salvarResponsavel(responsavel);
            return ResponseEntity.ok("Responsavel atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/atualizarParcial/{cpf}")
    @Operation(summary = "Atualizar parcialmente as informações de um responsavel", description = "Atualiza uma parte das informações do responsavel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Respçonsavel atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class))),
            @ApiResponse(responseCode = "404", description = "Responsaveis não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Responsaveis.class)))
    })
    public  ResponseEntity<?> atualizarResponsaveisParcial(@Parameter(description = "Cpf do responsavel") @PathVariable String cpf, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto com as novas informações",content = @Content(schema = @Schema(type = "object",example = "{\"dt_nascimento\": \"DT_NASCIMENTO\","+"\"sexo\": \"SEXO\","+"\"cpf\": \"CPF\","+"\"foto\": \"FOTO\","+"\"senha\": \"SENHA\","+"\"nome\": \"NOME\","+"\"endereco_id\": \"ENDERECO_ID\"}")) ) @RequestBody Map<String, Object> updates) {
        try{
            Responsaveis responsavel = responsaveisService.buscarResponsavelPorCpf(cpf);
            if (updates.containsKey("dt_nascimento")){
                responsavel.setDt_nascimento((LocalDate) updates.get("dt_nascimento"));
            }
            if (updates.containsKey("sexo")){
                responsavel.setSexo((String) updates.get("sexo"));
            }
            if (updates.containsKey("cpf")){
                responsavel.setCpf((String) updates.get("cpf"));
            }
            if (updates.containsKey("foto")){
                responsavel.setFoto((String) updates.get("foto"));
            }
            if (updates.containsKey("senha")){
                responsavel.setSenha((String) updates.get("senha"));
            }
            if (updates.containsKey("nome")){
                responsavel.setNome((String) updates.get("nome"));
            }
            if (updates.containsKey("endereco_id")){
                responsavel.setEndereco_id((Integer) updates.get("endereco_id"));
            }




            //validar
            DataBinder binder = new DataBinder(responsavel);
            binder.setValidator(validador);
            binder.validate();
            BindingResult result = binder.getBindingResult();
            if (result.hasErrors()){
                Map erros = handlerValidator(result);
                return ResponseEntity.badRequest().body(erros);
            }
            Responsaveis produtosalvo = responsaveisService.salvarResponsavel(responsavel);
            return ResponseEntity.ok("Responsavel atualizado parcialmente com sucesso");
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Responsavel com CPF " + cpf + " não encontrado");
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
