package com.conta.api.controller;

import com.conta.api.dto.NotificacaoDto;
import com.conta.api.entity.Notificacao;
import com.conta.api.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping
    public ResponseEntity<List<NotificacaoDto>> getNotificacoes(){
        List<Notificacao> notificacoes = notificacaoService.listaTodas();
        return ResponseEntity.ok(notificacoes.stream().map(NotificacaoDto::new).collect(Collectors.toList()));
    }
}
