package com.techmoveeapi.controllers;

import com.techmoveeapi.model.Aluno;
import com.techmoveeapi.services.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/aluno")
@Validated
public class AlunoController {


    private final AlunoService alunoService;
    private final Validator validador;

    public AlunoController(Validator validador, AlunoService alunoService){

        this.alunoService = alunoService;
        this.validador = validador;
    }

    @GetMapping("/selecionar")
    @Operation(summary = "Selecionar todos os produtos",
            description = "Selecionar todos os produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos os produtos selecionados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class)))
    })
    public ResponseEntity<List<Aluno>> listarProdutos(){
        List<Aluno> listaProdutos = alunoService.buscarTodosAlunos();
        return ResponseEntity.ok(listaProdutos);
    }

    @PostMapping("/inserirAluno")
    @Operation(summary = "Insere um novo aluno",
            description = "Insere um novo aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno inserido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class)))
    })
    public ResponseEntity<?> inserirAluno(@Valid @RequestBody Aluno aluno) {
        alunoService.salvarAluno(aluno);
        return ResponseEntity.ok().body("Aluno inserido");
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
