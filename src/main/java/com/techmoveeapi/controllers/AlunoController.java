package com.techmoveeapi.controllers;

import com.techmoveeapi.model.Aluno;
import com.techmoveeapi.model.Escola;
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
import java.util.Optional;

@RestController
@RequestMapping("api/aluno")
@Validated
@Controller
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



    @GetMapping("/buscarPorResponsavelCpf/{cpf}")
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
    public ResponseEntity<?> buscarPorResponsavelCpf(@Parameter(description = "Cpf do responsavel do aluno") @RequestParam String cpf){
        Aluno aluno = alunoService.buscarAlunoPorRespCpf(cpf);
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
                            schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class)))
    })
    public ResponseEntity<String> excluirAluno(@Parameter(description = "CPF do aluno") @Valid @PathVariable String cpf) {
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
                            schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class)))
    })
    public ResponseEntity<String> atualizarAluno(@Parameter(description = "ID do aluno")    @Valid @PathVariable String cpf, @RequestBody Aluno alunoAtualizado) {
        Aluno alunoExistente = alunoService.buscarAlunoPorCpf(cpf);
        if (alunoExistente != null) {
            Aluno aluno = alunoExistente;
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setDt_nascimento(alunoAtualizado.getDt_nascimento());
            aluno.setEscola_id(alunoAtualizado.getEscola_id());
            aluno.setTurno(alunoAtualizado.getTurno());
            aluno.setPcd(alunoAtualizado.getPcd());
            aluno.setId_foto(alunoAtualizado.getId_foto());
            aluno.setCpf(alunoAtualizado.getCpf());
            aluno.setResponsavel_cpf(alunoAtualizado.getResponsavel_cpf());

            alunoService.salvarAluno(aluno);
            return ResponseEntity.ok("Aluno atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @PatchMapping("/atualizarParcial/{cpf}")
    @Operation(summary = "Atualizar parcialmente as informações de um aluno", description = "Atualiza uma parte das informações do aluno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class)))
    })
    public  ResponseEntity<?> atualizarAlunoParcial(@Parameter(description = "Cpf do aluno") @PathVariable String cpf, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto com as novas informações",content = @Content(schema = @Schema(type = "object",example = "{\"nome\": \"NOME\","+"\"sexo\": \"SEXO\","+"\"idade\": \"IDADE\","+"\"escola\": \"ESCOLA\","+"\"turno\": \"TURNO\","+"\"pcd\": \"PCD\","+"\"foto\": \"FOTO\","+"\"cpf\": \"CPF\","+"\"responsavel_cpf\": \"RESPONSAVEL_CPF\"}")) ) @RequestBody Map<String, Object> updates) {
        try{
            Aluno aluno = alunoService.buscarAlunoPorCpf(cpf);
            if (updates.containsKey("nome")) {
                aluno.setNome((String) updates.get("nome"));
            }
            if (updates.containsKey("idade")){
                aluno.setDt_nascimento((LocalDate) updates.get("idade"));
            }
            if (updates.containsKey("escola")){
                aluno.setEscola_id((Integer) updates.get("escola"));
            }
            if (updates.containsKey("turno")){
                aluno.setTurno((String) updates.get("turno"));
            }
            if (updates.containsKey("pcd")){
                aluno.setPcd((String) updates.get("pcd"));
            }
            if (updates.containsKey("foto")){
                aluno.setId_foto((int) updates.get("foto"));
            }
            if (updates.containsKey("cpf")){
                aluno.setCpf((String) updates.get("cpf"));
            }
            if (updates.containsKey("responsavel_cpf")){
                aluno.setResponsavel_cpf((String) updates.get("responsavel_cpf"));
            }




            //validar
            DataBinder binder = new DataBinder(aluno);
            binder.setValidator(validador);
            binder.validate();
            BindingResult result = binder.getBindingResult();
            if (result.hasErrors()){
                Map erros = handlerValidator(result);
                return ResponseEntity.badRequest().body(erros);
            }
            Aluno produtosalvo = alunoService.salvarAluno(aluno);
            return ResponseEntity.ok("Aluno atualizado parcialmente com sucesso");
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Aluno com CPF " + cpf + " não encontrado");
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
