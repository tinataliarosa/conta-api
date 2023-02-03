package com.conta.api.utils;


import java.util.Objects;

public class ContaUtils {

    public static String extraiAgencia(String id_conta) {
        if (Objects.nonNull(id_conta)) {
            return id_conta.substring(0, 4);
        }
        return null;
    }

    public static String extraiNumero(String id_conta) {
        if (Objects.nonNull(id_conta)) {
            return id_conta.substring(4, 9);
        }
        return null;

    }

    public static String extraiDigito(String id_conta) {
        if (Objects.nonNull(id_conta)) {
            return id_conta.substring(9, 10);
        }
        return null;
    }

}
