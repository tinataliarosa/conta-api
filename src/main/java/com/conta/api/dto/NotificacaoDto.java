package com.conta.api.dto;

import com.conta.api.entity.Conta;
import com.conta.api.entity.Notificacao;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class NotificacaoDto {
    private String messageId;
    private Conta conta;
    private BigDecimal valor;

    public NotificacaoDto(Notificacao notificacao){
        this.messageId = notificacao.getMessageId();
        this.conta = notificacao.getConta();
        this.valor = notificacao.getValor();
    }
}
