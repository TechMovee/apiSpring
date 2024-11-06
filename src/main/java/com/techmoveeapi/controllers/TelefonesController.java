package com.techmoveeapi.controllers;

import com.techmoveeapi.model.Telefones;
import com.techmoveeapi.model.Telefones;
import com.techmoveeapi.services.TelefonesService;
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
@RequestMapping("api/telefones")
@Validated
@Controller
public class TelefonesController {
    private final TelefonesService telefonesService;
    private final Validator validador;

    public TelefonesController(Validator validador, TelefonesService telefonesService){

        this.telefonesService = telefonesService;
        this.validador = validador;
    }

    @GetMapping("/selecionar")
    @Operation(summary = "Selecionar todas os telefones",
            description = "Selecionar todas os telefones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos os telefones selecionados",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class)))
    })
    public ResponseEntity<List<Telefones>> listarTelefones(){
        List<Telefones> listaTelefones = telefonesService.buscarTodosTelefones();
        return ResponseEntity.ok(listaTelefones);
    }

    @GetMapping("/buscarPorId/{id}")
    @Operation(summary = "Buscar um telefone pelo id", description = "Busca o telefone pelo id e mostra seus dados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Telefones selecionada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class)))
    })
    public ResponseEntity<?> buscarPorId(@Parameter(description = "Id de telefone") @RequestParam int id){
        Telefones telefone = telefonesService.buscarTelefonePorId(id);
        if (telefone != null) {
            return ResponseEntity.ok(telefone);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Telefone não encontrado");
        }
    }

    @PostMapping("/inserirTelefone")
    @Operation(summary = "Insere um novo telefone",
            description = "Insere um novo telefone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Telefone inserido",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class)))
    })
    public ResponseEntity<?> inserirTelefone(@Valid @RequestBody Telefones telefone) {
        telefonesService.salvarTelefone(telefone);
        return ResponseEntity.ok(telefone);
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir um telefone", description = "remover um telefone do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Telefone excluído com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class))),
            @ApiResponse(responseCode = "404", description = "Telefone não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class)))
    })
    public ResponseEntity<String> excluirTelefones(@Parameter(description = "Id do telefone") @Valid @PathVariable int id) {
        Telefones telefone = telefonesService.buscarTelefonePorId(id);
        if (telefone != null){
            telefonesService.excluirTelefone(id);
            return ResponseEntity.ok("deu certo");
        }else {
            return  ResponseEntity.ok("deu ruim");
        }
    }

    @PutMapping("/atualizar/{id}")
    @Operation(summary = "Atualizar uma telefone", description = "Atualiza uma telefone ja existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Telefone atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class))),
            @ApiResponse(responseCode = "404", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class)))
    })
    public ResponseEntity<String> atualizarTelefones(@Parameter(description = "Placa da telefone")    @Valid @PathVariable int id, @RequestBody Telefones telefoneAtualizada) {
        Telefones telefoneExistente = telefonesService.buscarTelefonePorId(id);
        if (telefoneExistente != null) {
            Telefones telefone = telefoneExistente;
            telefone.setTipo(telefoneAtualizada.getTipo());
            telefone.setNumero(telefoneAtualizada.getNumero());


            telefonesService.salvarTelefone(telefone);
            return ResponseEntity.ok("Telefones atualizada com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/atualizarParcial/{id}")
    @Operation(summary = "Atualizar parcialmente as informações de uma telefone", description = "Atualiza uma parte das informações do telefone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class))),
            @ApiResponse(responseCode = "404", description = "Telefone não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Telefones.class)))
    })
    //arrumar aqui o swagger
    public  ResponseEntity<?> atualizarTelefonesParcial(@Parameter(description = "Placa da telefone") @PathVariable int id, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto com as novas informações",content = @Content(schema = @Schema(type = "object",example = "{\"tipo\": \"TIPO\","+"\"numero\": \"NUMERO\"}")) ) @RequestBody Map<String, Object> updates) {
        try{
            Telefones telefone = telefonesService.buscarTelefonePorId(id);
            if (updates.containsKey("tipo")){
                telefone.setTipo((String) updates.get("tipo"));
            }
            if (updates.containsKey("numero")){
                telefone.setNumero((String) updates.get("numero"));
            }


            //validar
            DataBinder binder = new DataBinder(telefone);
            binder.setValidator(validador);
            binder.validate();
            BindingResult result = binder.getBindingResult();
            if (result.hasErrors()){
                Map erros = handlerValidator(result);
                return ResponseEntity.badRequest().body(erros);
            }
            Telefones telefoneSalvo = telefonesService.salvarTelefone(telefone);
            return ResponseEntity.ok("Telefones atualizada parcialmente com sucesso");
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Telefones com Placa " + id + " não encontrada");
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
