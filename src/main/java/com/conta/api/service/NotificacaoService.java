package com.conta.api.service;

import com.conta.api.entity.Notificacao;
import com.conta.api.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public Notificacao salvaNotificacao(Notificacao notificacao){
        return notificacaoRepository.save(notificacao);
    }

    public List<Notificacao> listaTodas() {
        return  notificacaoRepository.findAll();
    }
}
