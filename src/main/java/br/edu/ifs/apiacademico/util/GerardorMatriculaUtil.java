package br.edu.ifs.apiacademico.util;

import java.time.LocalDate;
import java.util.Random;

public class GerardorMatriculaUtil {
    public static int gerarMatricula(){
        Random random = new Random();
        int ordemMatricula = random.nextInt(1000);

        LocalDate dataAtual = LocalDate.now();
        int ano = dataAtual.getYear();
        int mes = dataAtual.getMonthValue();
        int dia = dataAtual.getDayOfMonth();
        int semestre = mes > 6 ? 2 : 1;

        String matricula;
        matricula = String.valueOf(ano) + String.valueOf(semestre) + String.valueOf(ordemMatricula) ;
        int novaMatricula = Integer.parseInt(matricula);
        return  novaMatricula;

    }
}