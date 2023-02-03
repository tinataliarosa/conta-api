package com.conta.api.controller;

import com.conta.api.builder.ResponseBuilder;
import com.conta.api.dto.LimiteDto;
import com.conta.api.entity.Conta;
import com.conta.api.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.conta.api.utils.ContaUtils.*;

@RestController
@RequestMapping("/api/v1/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/{id_conta}/limite")
    public ResponseEntity<ResponseBuilder> getLimite(@PathVariable String id_conta){
        Optional<Conta> contaOptional = contaService.getIdLimiteConta(extraiAgencia(id_conta), extraiNumero(id_conta), extraiDigito(id_conta));
        if(contaOptional.isPresent()){
            var limiteDto = new LimiteDto(contaOptional.get());
            return ResponseEntity.ok().body(ResponseBuilder.builder().data(limiteDto).build());
        }
        return ResponseEntity.notFound().build();
    }
}
