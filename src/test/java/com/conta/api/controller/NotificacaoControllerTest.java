package com.conta.api.controller;


import com.conta.api.entity.Conta;
import com.conta.api.entity.Notificacao;
import com.conta.api.service.NotificacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NotificacaoControllerTest {

    protected static final String GET_LIMITE_JSON = "src/test/resources/response-get-notificacao.json";

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected NotificacaoService notificacaoService;

    protected String getJsonContent(String path, Class<?> clazz) throws IOException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(om.readValue(new File(path), clazz));
    }

    @Test
    public void getNotificacoes() throws Exception {

        Conta conta = Conta.builder()
                .id(UUID.fromString("99bab7cd-96b3-4282-b63a-1afe618064a9"))
                .agencia("1234")
                .numero("12345")
                .digito("1")
                .saldo(new BigDecimal(1))
                .limite(new BigDecimal(10)).build();

        List<Notificacao> notificacoes = new ArrayList<>();

        notificacoes.add(Notificacao.builder().conta(conta).messageId("b5459f0a-c363-4f02-8dc6-79263ddcb5bb").valor(new BigDecimal(20)).build());

        when(notificacaoService.listaTodas()).thenReturn(notificacoes);
        mockMvc.perform(get("/api/v1/notificacoes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent(GET_LIMITE_JSON, List.class)));
    }
}