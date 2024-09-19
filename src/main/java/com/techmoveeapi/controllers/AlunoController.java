package com.techmoveeapi.controllers;

import com.techmoveeapi.model.Aluno;
import com.techmoveeapi.services.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import java.util.Optional;

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
    @Operation(summary = "Selecionar todos os aluno",
            description = "Selecionar todos os aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos os aluno selecionados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class)))
    })
    public ResponseEntity<List<Aluno>> listarAlunos(){
        List<Aluno> listaAlunos = alunoService.buscarTodosAlunos();
        return ResponseEntity.ok(listaAlunos);
    }

    @GetMapping("/buscarPorCpf/{cpf}")
    @Operation(summary = "Buscar um novo aluno pelo nome", description = "Busca o aluno pelo cpf e mostra seus dados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno selecionado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class)))
    })
    public ResponseEntity<?> buscarPorCpf(@Parameter(description = "Cpf do aluno") @RequestParam String cpf){
        Aluno aluno = alunoService.buscarAlunoPorCpf(cpf);
        if (aluno != null) {
            return ResponseEntity.ok(aluno);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado");
        }
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

    @DeleteMapping("/excluir/{cpf}")
    @Operation(summary = "Excluir um aluno", description = "remover um aluno do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno excluído com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class)))
    })
    public ResponseEntity<String> excluirProduto(@Parameter(description = "CPF do aluno") @Valid @PathVariable String cpf) {
        Aluno aluno = alunoService.buscarAlunoPorCpf(cpf);
        if (aluno != null){
            alunoService.excluirAluno(cpf);
            return ResponseEntity.ok("deu certo");
        }else {
            return  ResponseEntity.ok("deu ruim");
        }
    }

    @PutMapping("/atualizar/{cpf}")
    @Operation(summary = "Atualizar um aluno", description = "Atualiza um aluno ja existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "404", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class)))
    })
    public ResponseEntity<String> atualizarProduto(@Parameter(description = "ID do aluno")    @Valid @PathVariable String cpf, @RequestBody Aluno alunoAtualizado) {
        Aluno alunoExistente = alunoService.buscarAlunoPorCpf(cpf);
        if (alunoExistente != null) {
            Aluno aluno = alunoExistente;
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setSexo(alunoAtualizado.getSexo());
            aluno.setIdade(alunoAtualizado.getIdade());
            aluno.setEscola(alunoAtualizado.getEscola());
            aluno.setTurno(alunoAtualizado.getTurno());
            aluno.setPcd(alunoAtualizado.getPcd());
            aluno.setFoto(alunoAtualizado.getFoto());
            aluno.setCpf(alunoAtualizado.getCpf());
            aluno.setResponsavel_cpf(alunoAtualizado.getResponsavel_cpf());

            alunoService.salvarAluno(aluno);
            return ResponseEntity.ok("Aluno atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
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
