package com.conta.api.service;

import com.conta.api.domain.Movimento;
import com.conta.api.entity.Conta;
import com.conta.api.entity.Notificacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnviaNotificacaoLimiteService {

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(EnviaNotificacaoLimiteService.class);

    @Autowired
    private ContaService contaService;

    @Autowired
    private NotificacaoService notificacaoService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "movimentacoes_conta")
    public void consomeMovimentacao(String conteudo, @Header(JmsHeaders.MESSAGE_ID) String messageId) throws Exception{
        try {
            Movimento movimento = objectMapper.readValue(conteudo, Movimento.class);
            logaValidacao(movimento);

            Optional<Conta> optionalConta = contaService.getIdLimiteConta(movimento.getAgencia(),movimento.getNumeroConta(),movimento.getDigitoConta());

            if(optionalConta.isPresent()) {
                Conta conta = optionalConta.get();
                logger.info("Valor:" + movimento.getValor() + "Saldo" + conta.getSaldo() + " Limite: " + conta.getLimite() + "Saldo com Limite" + conta.getSaldo().add(conta.getLimite()));

                if (movimento.getValor().compareTo(conta.getSaldo().add(conta.getLimite())) > 0) {
                    notificacaoService.salvaNotificacao(new Notificacao(messageId.substring(3), conta, movimento.getValor()));
                    enviaNotificacao("{\"status\": \"Valor do movimento maior que o limite da conta \", id: " + movimento.getId() + "}");
                } else {
                    logger.info("Limite disponível");
                }
            }

        }catch (Exception e){
            throw e;
        }
    }

    private void logaValidacao(Movimento movimento) {
        movimento.validate();
        if (!movimento.validationMessages.isEmpty()) {
            logger.info("Movimento não é válido" + movimento.getValidationMessagesAsJson());
        } else {
            logger.info("Movimento Valido =" + movimento.getId());
        }
    }

    private void enviaNotificacao(String message){
        try{
            jmsTemplate.convertAndSend("notificacao_limite", message);
        }catch (Exception e){
            throw e;
        }
    }
}
