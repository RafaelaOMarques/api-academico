package br.edu.ifs.apiacademico.util;

import java.time.LocalDate;
import java.util.Random;

public class ValidadorCpfUtil {
    public static boolean validarCpf(String cpf) {
        String regexCpf = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        if (!cpf.matches(regexCpf)) {
            throw new IllegalArgumentException("Invalid CPF");
        }

        String cpfSemPontos = cpf.replaceAll("[^\\d]", "");

        if (cpfSemPontos.length() != 11) {
            throw new IllegalArgumentException("Invalid CPF");
        }

        if (cpfSemPontos.length() != 11) {
            throw new Error("Invalid CPF");
        }

        // Elimina CPFs conhecidos que são inválidos
        if (
                cpfSemPontos.equals("00000000000") || cpfSemPontos.equals("11111111111") ||
                cpfSemPontos.equals("22222222222") || cpfSemPontos.equals("33333333333") ||
                cpfSemPontos.equals("44444444444") || cpfSemPontos.equals("55555555555") ||
                cpfSemPontos.equals("66666666666") || cpfSemPontos.equals("77777777777") ||
                cpfSemPontos.equals("88888888888") || cpfSemPontos.equals("99999999999")
        ) {
            throw new IllegalArgumentException("Invalid CPF");
        }

        Integer sum = 0;
        Integer saldo;

        // Calcula o primeiro dígito verificador
        for (int i = 1; i <= 9; i++) {
            sum += Character.getNumericValue(cpfSemPontos.charAt(i - 1)) * (11 - i);
        }

        saldo = (sum * 10) % 11;

        if (saldo == 10 || saldo == 11) {
            saldo = 0;
        }

        if (saldo != Character.getNumericValue(cpfSemPontos.charAt(9))) {
            throw new IllegalArgumentException("Invalid CPF");
        }

        sum = 0;

        // Calcula o segundo dígito verificador
        for (int i = 1; i <= 10; i++) {
            sum += Character.getNumericValue(cpfSemPontos.charAt(i - 1)) * (12 - i);
        }

        saldo = (sum * 10) % 11;

        if (saldo == 10 || saldo == 11) {
            saldo = 0;
        }


        if (saldo != Character.getNumericValue(cpfSemPontos.charAt(10))) {
            throw new IllegalArgumentException("Invalid CPF");
        }

        return true;
    }
}
