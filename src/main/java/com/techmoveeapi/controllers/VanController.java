package com.techmoveeapi.controllers;

import com.techmoveeapi.model.Van;

import com.techmoveeapi.services.VanService;
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
@RequestMapping("api/van")
@Validated
@Controller
public class VanController {
    private final VanService vanService;
    private final Validator validador;

    public VanController(Validator validador, VanService vanService){

        this.vanService = vanService;
        this.validador = validador;
    }

    @GetMapping("/selecionar")
    @Operation(summary = "Selecionar todas as vans",
            description = "Selecionar todas as vans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas as vans selecionadas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class)))
    })
    public ResponseEntity<List<Van>> listarVan(){
        List<Van> listaVan = vanService.buscarTodasVans();
        return ResponseEntity.ok(listaVan);
    }

    @GetMapping("/selecionarCapacidade")
    @Operation(summary = "Selecionar todas as vans",
            description = "Selecionar todas as vans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas as vans selecionadas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class)))
    })
    public ResponseEntity<List<Van>> listarVanCapacidade(){
        List<Van> listaVan = vanService.buscarVanPorOrdemCapacidade();
        return ResponseEntity.ok(listaVan);
    }

    @GetMapping("/selecionarMensalidade")
    @Operation(summary = "Selecionar todas as vans",
            description = "Selecionar todas as vans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas as vans selecionadas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class)))
    })
    public ResponseEntity<List<Van>> listarVanMensalidade(){
        List<Van> listaVan = vanService.buscarVanPorMenorMensalidade();
        return ResponseEntity.ok(listaVan);
    }

    @GetMapping("/selecionarAcessivel")
    @Operation(summary = "Selecionar todas as vans",
            description = "Selecionar todas as vans")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todas as vans selecionadas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class)))
    })
    public ResponseEntity<List<Van>> listarVanAcessivel(){
        List<Van> listaVan = vanService.buscarVanAcessivel();
        return ResponseEntity.ok(listaVan);
    }

    @GetMapping("/buscarPorPlaca/{placa}")
    @Operation(summary = "Buscar uma nova van pela placa", description = "Busca a van pela placa e mostra seus dados ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Van selecionada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class)))
    })
    public ResponseEntity<?> buscarPorPlaca(@Parameter(description = "Placa da van") @RequestParam String placa){
        Van van = vanService.buscarVanPorPlaca(placa);
        if (van != null) {
            return ResponseEntity.ok(van);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Van não encontrado");
        }
    }

    @PostMapping("/inserirVan")
    @Operation(summary = "Insere uma nova van",
            description = "Insere uma nova van")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Van inserida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "404", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class)))
    })
    public ResponseEntity<?> inserirVan(@Valid @RequestBody Van van) {
        vanService.salvarVan(van);
        return ResponseEntity.ok().body("Van inserida");
    }


    @DeleteMapping("/excluir/{placa}")
    @Operation(summary = "Excluir uma van", description = "remover uma van do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Van excluído com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "404", description = "Van não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class)))
    })
    public ResponseEntity<String> excluirVan(@Parameter(description = "Placa da van") @Valid @PathVariable String placa) {
        Van van = vanService.buscarVanPorPlaca(placa);
        if (van != null){
            vanService.excluirVan(placa);
            return ResponseEntity.ok("deu certo");
        }else {
            return  ResponseEntity.ok("deu ruim");
        }
    }

    @PutMapping("/atualizar/{placa}")
    @Operation(summary = "Atualizar uma van", description = "Atualiza uma van ja existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Van atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "404", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class)))
    })
    public ResponseEntity<String> atualizarVan(@Parameter(description = "Placa da van")    @Valid @PathVariable String placa, @RequestBody Van vanAtualizada) {
        Van vanExistente = vanService.buscarVanPorPlaca(placa);
        if (vanExistente != null) {
            Van van = vanExistente;
            van.setModelo(vanAtualizada.getModelo());
            van.setFoto_id(vanAtualizada.getFoto_id());
            van.setPlaca(vanAtualizada.getPlaca());
            van.setTransportador_cnh(vanAtualizada.getTransportador_cnh());

            vanService.salvarVan(van);
            return ResponseEntity.ok("Van atualizada com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PatchMapping("/atualizarParcial/{placa}")
    @Operation(summary = "Atualizar parcialmente as informações de uma van", description = "Atualiza uma parte das informações da van")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Van atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "404", description = "Van não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Van.class)))
    })
    //arrumar aqui o swagger
    public  ResponseEntity<?> atualizarVanParcial(@Parameter(description = "Placa da van") @PathVariable String placa, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto com as novas informações",content = @Content(schema = @Schema(type = "object",example = "{\"modelo\": \"MODELO\","+"\"ano\": \"ANO\","+"\"foto_id\": \"FOTO_ID\","+"\"placa\": \"PLACA\","+"\"transportador_cnh\": \"TRANSPORTADOR_CNH\"}")) ) @RequestBody Map<String, Object> updates) {
        try{
            Van van = vanService.buscarVanPorPlaca(placa);
            if (updates.containsKey("modelo")){
                van.setModelo((String) updates.get("modelo"));
            }
            if (updates.containsKey("foto_id")){
                van.setFoto_id((int) updates.get("foto_id"));
            }
            if (updates.containsKey("placa")){
                van.setPlaca((String) updates.get("placa"));
            }
            if (updates.containsKey("transportador_cnh")){
                van.setTransportador_cnh((String) updates.get("transportador_cnh"));
            }

            //validar
            DataBinder binder = new DataBinder(van);
            binder.setValidator(validador);
            binder.validate();
            BindingResult result = binder.getBindingResult();
            if (result.hasErrors()){
                Map erros = handlerValidator(result);
                return ResponseEntity.badRequest().body(erros);
            }
            Van vanSalva = vanService.salvarVan(van);
            return ResponseEntity.ok("Van atualizada parcialmente com sucesso");
        }catch (RuntimeException re){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Van com Placa " + placa + " não encontrada");
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
