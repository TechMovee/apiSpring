package com.techmoveeapi.controllers;

import com.techmoveeapi.model.Endereco;
import com.techmoveeapi.services.EnderecosService;
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
@RequestMapping("api/enderecos")
@Validated
@Controller
public class EnderecosController {

    private final EnderecosService enderecosService;
    private final Validator validador;

    public EnderecosController(Validator validador, EnderecosService enderecosService){
        this.enderecosService = enderecosService;
        this.validador = validador;
    }

    @GetMapping("/selecionar")
    @Operation(summary = "Selecionar todos os enderecos",
            description = "Selecionar todos os enderecos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos os enderecos selecionados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class)))
    })
    public ResponseEntity<List<Endereco>> listarEnderencos(){
        List<Endereco> listaEnderecos = enderecosService.buscarTodosEnderecos();
        return ResponseEntity.ok(listaEnderecos);
    }

    @GetMapping("/buscarPorId/{id}")
    @Operation(summary = "Buscar um novo endereco pelo id", description = "Busca o endereco pelo id e mostra seus dados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco selecionado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class)))
    })
    public ResponseEntity<?> buscarPorId(@Parameter(description = "Id do endereco") @RequestParam int id){
        Endereco endereco = enderecosService.buscarEnderecoPorId(id);
        if (endereco != null) {
            return ResponseEntity.ok(endereco);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco não encontrado");
        }
    }

    @PostMapping("/inserirEndereco")
    @Operation(summary = "Insere um novo endereco",
            description = "Insere um novo endereco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco inserido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class)))
    })
    public ResponseEntity<?> inserirEndereco(@Valid @RequestBody Endereco endereco) {
        enderecosService.salvarEndereco(endereco);
        return ResponseEntity.ok().body("Endereco inserido");
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir um endereco", description = "remover um endereco do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco excluído com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "404", description = "Endereco não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class)))
    })
    public ResponseEntity<String> excluirEndereco(@Parameter(description = "Id do endereco") @Valid @PathVariable int id) {
        Endereco endereco = enderecosService.buscarEnderecoPorId(id);
        if (endereco != null){
            enderecosService.excluirEndereco(id);
            return ResponseEntity.ok("deu certo");
        }else {
            return  ResponseEntity.ok("deu ruim");
        }
    }



    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar um endereco", description = "Atualiza um endereco ja existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "404", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class)))
    })
    public ResponseEntity<String> atualizarEndereco(@Parameter(description = "ID do endereco")    @Valid @PathVariable int id, @RequestBody Endereco enderecoAtualizado) {
        Endereco enderecoExistente = enderecosService.buscarEnderecoPorId(id);
        if (enderecoExistente != null) {
            Endereco endereco = enderecoExistente;
            endereco.setCep(enderecoAtualizado.getCep());
            endereco.setBairro(enderecoAtualizado.getBairro());
            endereco.setRua(enderecoAtualizado.getRua());
            endereco.setNumero(enderecoAtualizado.getNumero());

            enderecosService.salvarEndereco(endereco);
            return ResponseEntity.ok("Endereco atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente as informações de um endereco", description = "Atualiza uma parte das informações do endereco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "404", description = "Endereco não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class)))
    })
    public  ResponseEntity<?> atualizarEnderecoParcial(@Parameter(description = "Id do endereco") @PathVariable int id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto com as novas informações",content = @Content(schema = @Schema(type = "object",example = "{\"cep\": \"CEP\","+"\"bairro\": \"BAIRRO\","+"\"rua\": \"RUA\","+"\"numero\": \"NUMERO\"}")) ) @RequestBody Map<String, Object> updates) {
        try{
            Endereco endereco = enderecosService.buscarEnderecoPorId(id);
            if (updates.containsKey("cep")) {
                endereco.setCep((String) updates.get("cep"));
            }
            if (updates.containsKey("bairro")) {
                endereco.setBairro((String) updates.get("bairro"));
            }
            if (updates.containsKey("rua")) {
                endereco.setRua((String) updates.get("rua"));
            }
            if (updates.containsKey("numero")) {
                endereco.setNumero((String) updates.get("numero"));
            }

            //validar
            DataBinder binder = new DataBinder(endereco);
            binder.setValidator(validador);
            binder.validate();
            BindingResult result = binder.getBindingResult();
            if (result.hasErrors()){
                Map erros = handlerValidator(result);
                return ResponseEntity.badRequest().body(erros);
            }
            Endereco produtosalvo = enderecosService.salvarEndereco(endereco);
            return ResponseEntity.ok("Endereco atualizado parcialmente com sucesso");
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Endereco com Id " + id + " não encontrado");
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
