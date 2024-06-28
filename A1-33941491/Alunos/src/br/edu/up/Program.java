package br.edu.up;

import java.util.List;

public class Program {
    public static void main(String[] args) throws Exception {

        System.out.println("\nProvaA1");

        GerenciadorDeArquivos gArquivos = new GerenciadorDeArquivos();

        List<Aluno> listaAlunos;
        listaAlunos = gArquivos.getAlunos();

        System.out.println("\nLista de alunos: ");

        if (listaAlunos.size() == 0) {
            System.out.println("Não há alunos no cadastro.");
        } else {
            System.out.println(listaAlunos.toString());
        }
        if (gArquivos.gravarAlunoLista(listaAlunos) == true) {
            System.out.println("Arquivo gravado!");
        } else {
            System.out.println("Falha para gravar arquivos.");
        }

    }
}