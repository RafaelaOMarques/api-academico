package br.edu.ifs.apiacademico.util;

import java.time.LocalDate;
import java.util.Random;

public class GerardorMatriculaUtil {
    public static int gerarMatricula(){
        Random random = new Random();
        int numero = random.nextInt(1000);
        LocalDate dataAtual = LocalDate.now();
        int ano = dataAtual.getYear();
        int mes = dataAtual.getMonthValue();
        int dia = dataAtual.getDayOfMonth();
        int semestre = mes > 6 ? 2 : 1;

        String newMatricula = Integer.toString(ano)+Integer.toString(semestre)+Integer.toString(mes)+Integer.toString(dia)+Integer.toString(numero);
        int matricula = Integer.parseInt(newMatricula);
        return  matricula;

    }
}
