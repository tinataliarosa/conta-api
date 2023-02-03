package com.conta.api.controller;

import com.conta.api.builder.ResponseBuilder;
import com.conta.api.entity.Conta;
import com.conta.api.service.ContaService;
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
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ContaControllerTest {

    protected static final String GET_LIMITE_JSON = "src/test/resources/response-get-limite.json";

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected ContaService contaService;

    protected String getJsonContent(String path, Class<?> clazz) throws IOException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(om.readValue(new File(path), clazz));
    }

    @Test
    public void getLimiteTest() throws Exception {

        Conta conta = Conta.builder()
                .limite(new BigDecimal(200)).build();

        when( contaService.getIdLimiteConta("1234","12345","1")).thenReturn( Optional.of(conta) );
        mockMvc.perform(get("/api/v1/contas/1234123451/limite"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(getJsonContent(GET_LIMITE_JSON, ResponseBuilder.class)));
    }
}