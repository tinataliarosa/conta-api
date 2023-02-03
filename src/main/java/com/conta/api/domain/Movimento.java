package com.conta.api.domain;

import com.conta.api.Validator;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class Movimento implements Serializable, Validator {

    private String id;
    private String agencia;
    private String numeroConta;
    private String digitoConta;
    private BigDecimal valor;

    public List<String> validationMessages = new ArrayList<>();

    public String getValidationMessagesAsJson(){
        StringBuilder json = new StringBuilder("messages:[");
        validationMessages.forEach(message -> {
            json.append("'" + message + "'" + ",");
        });
        json.append("]");
        return json.toString();
    }

    @Override
    public void validate() {
        if(this.getAgencia().isEmpty()){
            validationMessages.add("Agência não pode ser vazio");
        }
        if(this.getNumeroConta().isEmpty()){
            validationMessages.add("Conta não pode ser vazio");
        }
        if(this.getDigitoConta().isEmpty()) {
            validationMessages.add("Dígito não pode ser vazio");
        }
        if(this.getValor().compareTo(BigDecimal.ZERO)<0){
            validationMessages.add("O valor precisa ser maior que zero");
        }
    }
}
