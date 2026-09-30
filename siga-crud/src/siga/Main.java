package siga;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SIGA - Atividade CRUD ===\n");

        AlunoDAO dao = new AlunoDAOMemoria();
        ServicoAluno servico = new ServicoAluno(dao);

        // --- CREATE ---
        cadastrar(servico, new Aluno("Maria Silva", "2026001", 8.5));
        cadastrar(servico, new Aluno("João Souza",  "2026002", 6.0));
        System.out.println();

        System.out.println("Teste de cadastro de aluno com matrícula duplicada:");
        cadastrar(servico, new Aluno("Duplicado Silva", "2026001", 7.0));

        System.out.println("\nTeste de inserção de aluno com média absurda:");
        Aluno suspeito = new Aluno("Média Absurda", "2026003", 50);
        cadastrar(servico, suspeito);
        System.out.println();

        // --- READ ---
        System.out.println("Alunos cadastrados:");
        for (Aluno aluno : servico.listar()) {
            System.out.println("  " + aluno);
        }

        System.out.println("\nAlunos após a tentativa de inserção direta na lista devolvida:");
        List<Aluno> lista = servico.listar();
        lista.add(new Aluno("Intruso Silva", "9999999", 10));
        for (Aluno aluno : servico.listar()) {
            System.out.println("  " + aluno);
        }

        // --- DELETE ---
        System.out.println("\nTeste de exclusão de aluno existente:");
        try {
            servico.excluir("2026002");
            System.out.println("Removido: matrícula 2026002");
            System.out.println();
            System.out.println("Alunos após remoção:");
            for (Aluno aluno : servico.listar()) {
                System.out.println("  " + aluno);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Remover: " + e.getMessage());
        }

        System.out.println("\nTeste de exclusão de uma matrícula inexistente:");
        try {
            servico.excluir("0000000");
        } catch (IllegalArgumentException e) {
            System.out.println("Remover: " + e.getMessage());
        }

        // --- UPDATE ---
        System.out.println("\nTeste de atualização de aluno existente:");
        try {
            servico.alterar(new Aluno("Maria Silva", "2026001", 9.0));
            System.out.println("Atualizado: " + servico.consultar("2026001"));
        } catch (IllegalArgumentException e) {
            System.out.println("Atualizar: " + e.getMessage());
        }
        System.out.println("Revisão dos alunos cadastrados:");
        for (Aluno aluno : servico.listar()) {
            System.out.println("  " + aluno);
        }
    }

    /** Apresentação: traduz as exceções do serviço em mensagens ao usuário. */
    private static void cadastrar(ServicoAluno servico, Aluno aluno) {
        try {
            servico.cadastrar(aluno);
            System.out.println("Cadastrado: " + aluno);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível cadastrar: " + e.getMessage());
        }
    }
}
