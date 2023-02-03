package com.conta.api.dto;

import com.conta.api.entity.Conta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimiteDto {

    private BigDecimal limite;

    public LimiteDto(Conta conta){
        this.limite = conta.getLimite();
    }
}
