package com.techmoveeapi.controllers;

import com.techmoveeapi.model.Transportadores;
import com.techmoveeapi.services.TransportadoresService;
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

@RestController
@RequestMapping("api/transportador")
@Validated
@Controller
public class TransportadoresController {
    private final TransportadoresService transportadoresService;
    private final Validator validador;

    public TransportadoresController(Validator validador, TransportadoresService transportadoresService){

        this.transportadoresService = transportadoresService;
        this.validador = validador;
    }


    @GetMapping("/selecionar")
    @Operation(summary = "Selecionar todos os transportadores",
            description = "Selecionar todos os transportadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos os transportadores selecionados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class)))
    })
    public ResponseEntity<List<Transportadores>> listarTransportadores(){
        List<Transportadores> listaTransportadores = transportadoresService.buscarTodosTransportadores();
        return ResponseEntity.ok(listaTransportadores);
    }

    @GetMapping("/buscarPorCpf/{cpf}")
    @Operation(summary = "Buscar um novo transportador pelo cpf", description = "Busca o transportador pelo cpf e mostra seus dados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transportador selecionado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class)))
    })
    public ResponseEntity<?> buscarPorCpf(@Parameter(description = "Cpf do transportadores") @RequestParam String cpf){
        Transportadores transportador = transportadoresService.buscarTransportadorPorCpf(cpf);
        if (transportador != null) {
            return ResponseEntity.ok(transportador);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transportador não encontrado");
        }
    }

    @PostMapping("/inserirTransportador")
    @Operation(summary = "Insere um novo transpotador",
            description = "Insere um novo transpotador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transportador inserido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class)))
    })
    public ResponseEntity<?> inserirTransportadoress(@Valid @RequestBody Transportadores transpotador) {
        transportadoresService.salvarTransportador(transpotador);
        return ResponseEntity.ok().body("Transportador inserido");
    }


    @DeleteMapping("/excluir/{cpf}")
    @Operation(summary = "Excluir um transportador", description = "remover um transportador do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transportador excluído com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class))),
            @ApiResponse(responseCode = "404", description = "Transportadores não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class)))
    })
    public ResponseEntity<String> excluirTransportador(@Parameter(description = "CPF do transportador") @Valid @PathVariable String cpf) {
        Transportadores transportador = transportadoresService.buscarTransportadorPorCpf(cpf);
        if (transportador != null){
            transportadoresService.excluirTransportador(cpf);
            return ResponseEntity.ok("deu certo");
        }else {
            return  ResponseEntity.ok("deu ruim");
        }
    }

    @PutMapping("/atualizar/{cpf}")
    @Operation(summary = "Atualizar um transportador", description = "Atualiza um transportador ja existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transportador atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class))),
            @ApiResponse(responseCode = "404", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class)))
    })
    public ResponseEntity<String> atualizarTransportador(@Parameter(description = "Id do transportador")    @Valid @PathVariable String cpf, @RequestBody Transportadores transportadorAtualizado) {
        Transportadores transportadorExistente = transportadoresService.buscarTransportadorPorCpf(cpf);
        if (transportadorExistente != null) {
            Transportadores transportador = transportadorExistente;
            transportador.setEmail(transportadorAtualizado.getEmail());
            transportador.setSenha(transportadorAtualizado.getSenha());
            transportador.setDt_nascimento(transportadorAtualizado.getDt_nascimento());
            transportador.setFoto_id(transportadorAtualizado.getFoto_id());
            transportador.setCpf(transportadorAtualizado.getCpf());
            transportador.setNome(transportadorAtualizado.getNome());
            transportador.setCnh(transportadorAtualizado.getCnh());

            transportadoresService.salvarTransportador(transportador);
            return ResponseEntity.ok("Transportador atualizado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PatchMapping("/atualizarParcial/{cpf}")
    @Operation(summary = "Atualizar parcialmente as informações de um transportador", description = "Atualiza uma parte das informações do transportador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transportador atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class))),
            @ApiResponse(responseCode = "404", description = "Transportadores não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Transportadores.class)))
    })
    //arrumar aqui o swagger
    public  ResponseEntity<?> atualizarTransportadoresParcial(@Parameter(description = "Cpf do transportador") @PathVariable String cpf, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto com as novas informações",content = @Content(schema = @Schema(type = "object",example = "{\"cep\": \"CEP\","+"\"email\": \"Email\","+"\"senha\": \"SENHA\","+"\"dt_nascimento\": \"DT_NASCIMENTO\","+"\"foto\": \"FOTO\","+"\"cpf\": \"CPF\","+"\"nome\": \"nome\","+"\"sexo\": \"SEXO\","+"\"cnh\": \"CNH\"}")) ) @RequestBody Map<String, Object> updates) {
        try{
            Transportadores transportador = transportadoresService.buscarTransportadorPorCpf(cpf);
            if (updates.containsKey("email")){
                transportador.setEmail((String) updates.get("email"));
            }
            if (updates.containsKey("senha")){
                transportador.setSenha((String) updates.get("senha"));
            }
            if (updates.containsKey("dt_nascimento")){
                transportador.setDt_nascimento((LocalDate) updates.get("dt_nascimento"));
            }
            if (updates.containsKey("foto")){
                transportador.setFoto_id((int) updates.get("foto"));
            }
            if (updates.containsKey("cpf")){
                transportador.setCpf((String) updates.get("cpf"));
            }
            if (updates.containsKey("nome")){
                transportador.setNome((String) updates.get("nome"));
            }
            if (updates.containsKey("cnh")){
                transportador.setCnh((String) updates.get("cnh"));
            }

            //validar
            DataBinder binder = new DataBinder(transportador);
            binder.setValidator(validador);
            binder.validate();
            BindingResult result = binder.getBindingResult();
            if (result.hasErrors()){
                Map erros = handlerValidator(result);
                return ResponseEntity.badRequest().body(erros);
            }
            Transportadores transportadorSalvo = transportadoresService.salvarTransportador(transportador);
            return ResponseEntity.ok("Transportador atualizado parcialmente com sucesso");
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Transportador com CPF " + cpf + " não encontrado");
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
