package br.edu.up;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorDeArquivos {
    private String arquivoAlunos = "C:\\Users\\autologon\\Desktop\\A1-33941491\\Alunos\\src\\br\\edu\\up\\alunos.csv";
    private String arquivoResumo = "C:\\Users\\autologon\\Desktop\\A1-33941491\\Alunos\\src\\br\\edu\\up\\resumo.csv";

    public List<Aluno> getAlunos() {
        List<Aluno> listaDeAlunos = new ArrayList<>();

        try (Scanner leitor = new Scanner(new File(arquivoAlunos))) {

            if (leitor.hasNextLine()) {
                leitor.nextLine();
            }
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String dados[] = linha.split(";");

                String matricula = dados[0];
                String nome = dados[1];
                String nota = dados[2];

                Aluno aluno = new Aluno(matricula, nome, nota);
                listaDeAlunos.add(aluno);

            }
        } catch (Exception e) {
            System.out.println("Arquivo de alunos não encontrado! " + e.getMessage());
        }

        return listaDeAlunos;
    }

    private int calcularQuantidadeAlunos(List<Aluno> alunos) {
        return alunos.size();
    }

    private int calcularQuantidadeAprovados(List<Aluno> alunos) {
        int count = 0;
        for (Aluno aluno : alunos) {
            if (Double.parseDouble(aluno.getNota().replace(",", ".")) >= 6.0) {
                count++;
            }
        }
        return count;
    }

    private int calcularQuantidadeReprovados(List<Aluno> alunos) {
        int count = 0;
        for (Aluno aluno : alunos) {
            if (Double.parseDouble(aluno.getNota().replace(",", ".")) < 6.0) {
                count++;
            }
        }
        return count;
    }

    private double calcularMenorNota(List<Aluno> alunos) {
        double menorNota = Double.MAX_VALUE;
        for (Aluno aluno : alunos) {
            double nota = Double.parseDouble(aluno.getNota().replace(",", "."));
            if (nota < menorNota) {
                menorNota = nota;
            }
        }
        return menorNota;
    }
    private double calcularMaiorNota(List<Aluno> alunos) {
        double maiorNota = Double.MIN_VALUE;
        for (Aluno aluno : alunos) {
            double nota = Double.parseDouble(aluno.getNota().replace(",", "."));
            if (nota > maiorNota) {
                maiorNota = nota;
            }
        }
        return maiorNota;
    }

    private double calcularMediaGeral(List<Aluno> alunos) {
        double soma = 0.0;
        for (Aluno aluno : alunos) {
            soma += Double.parseDouble(aluno.getNota().replace(",", "."));
        }
        return soma / alunos.size();
    }

    public boolean gravarAlunoLista(List<Aluno> alunos) {
        try {
            FileWriter arquivoGravar = new FileWriter(arquivoResumo);
            PrintWriter gravador = new PrintWriter(arquivoGravar);

            int quantidadeAlunos = calcularQuantidadeAlunos(alunos);
            int quantidadeAprovados = calcularQuantidadeAprovados(alunos);
            int quantidadeReprovados = calcularQuantidadeReprovados(alunos);
            double menorNota = calcularMenorNota(alunos);
            double maiorNota = calcularMaiorNota(alunos);
            double mediaGeral = calcularMediaGeral(alunos);
            gravador.println(
                    "quantidadeAlunos;quantidadeAprovados;quantidadeReprovados;menorNota;maiorNota;mediaGeral");
            gravador.println(
                    quantidadeAlunos + ";" +
                            quantidadeAprovados + ";" +
                            quantidadeReprovados + ";" +
                            menorNota + ";" +
                            maiorNota + ";" +
                            mediaGeral);

            gravador.close();
            return true;
        } catch (Exception e) {
            System.out.println("Não foi possível gravar o arquivo!");
        }
        return false;
    }

}