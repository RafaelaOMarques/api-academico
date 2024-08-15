package br.edu.ifs.apiacademico.util;

import java.time.LocalDate;
import java.util.Random;

public class GeradorIdUtil {
     public static int gerarIdentificadorUnico(){
            Random RANDOM = new Random();
            int ordem = RANDOM.nextInt(10000); // Gera um número aleatório de 4 dígitos
            LocalDate dataAtual = LocalDate.now();
            int ano = dataAtual.getYear();

            String newId;
            newId = String.valueOf(ano) + String.valueOf(ordem);
            int ordemId = Integer.parseInt(newId);
            return ordemId;
    }
}