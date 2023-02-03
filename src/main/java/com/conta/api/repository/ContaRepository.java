package com.conta.api.repository;

import com.conta.api.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository  extends JpaRepository<Conta, Long> {

    @Query(value = "SELECT" +
            " c.id, " +
            " c.agencia, " +
            " c.numero, " +
            " c.digito, " +
            " c.saldo, " +
            " c.limite" +
            " FROM conta c" +
            " WHERE c.agencia = :agencia" +
            " AND c.numero = :numero" +
            " AND c.digito = :digito" +
            " ", nativeQuery = true)
    Optional<Conta> getIdLimiteConta(@Param("agencia") String agencia,
                                     @Param("numero") String numero,
                                     @Param("digito") String digito);
}
