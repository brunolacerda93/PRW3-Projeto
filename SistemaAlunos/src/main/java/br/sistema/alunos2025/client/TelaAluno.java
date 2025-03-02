package br.sistema.alunos2025.client;

import br.sistema.alunos2025.db.DataBaseService;
import br.sistema.alunos2025.db.dao.AlunoDAO;
import br.sistema.alunos2025.db.model.Aluno;
import br.sistema.alunos2025.utils.Tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

public class TelaAluno {

    private final Scanner sc = new Scanner(System.in);

    public TelaAluno() {
    }

    public void menu() {
        String opc;

        do { // hast
            Tools.clearScreen();
            System.out.println("** CADASTRO DE ALUNOS **\n");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Alterar aluno");
            System.out.println("3 - Excluir aluno");
            System.out.println("4 - Buscar aluno pelo nome");
            System.out.println("5 - Listar alunos (com status aprovação)");
            System.out.println("0 - FIM\n");
            System.out.print("Digite a opção desejada: ");
            opc = sc.nextLine();

            switch (opc) {
                case "1":
                    cadastrarAluno();
                    Tools.pause();
                    sc.nextLine();
                    break;

                case "2":
                    alterarAluno();
                    Tools.pause();
                    break;

                case "3":
                    removerAluno();
                    Tools.pause();
                    break;

                case "4":
                    buscarAlunoPorNome();
                    Tools.pause();
                    break;

                case "5":
                    listarTodos();
                    Tools.pause();
                    break;

                case "0":
                    break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA!");
                    Tools.pause();
                    break;
            }

        } while (!opc.equals("0"));
    }

    private void alterarAluno() {
        Tools.clearScreen();
        System.out.println("ALTERAR ALUNO:");
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();

        Aluno aluno = DataBaseService.getInstance().buscarAlunoPorNome(nome);
        if (aluno == null) {
            return;
        }

        Aluno novoAluno = new Aluno();
        novoAluno.setNome(String.copyValueOf(aluno.getNome().toCharArray()));
        novoAluno.setRa(String.copyValueOf(aluno.getRa().toCharArray()));
        novoAluno.setEmail(String.copyValueOf(aluno.getEmail().toCharArray()));
        novoAluno.setNota1(BigDecimal.valueOf(aluno.getNota1().doubleValue()));
        novoAluno.setNota2(BigDecimal.valueOf(aluno.getNota2().doubleValue()));
        novoAluno.setNota3(BigDecimal.valueOf(aluno.getNota3().doubleValue()));

        String opc;
        do { // hast
            Tools.clearScreen();
            System.out.println("Dados do Aluno:");
            System.out.println(novoAluno + "\n");
            System.out.println("ALTERAR ALUNO:");
            System.out.println("1 - Nome");
            System.out.println("2 - RA");
            System.out.println("3 - Email");
            System.out.println("4 - Nota 01");
            System.out.println("5 - Nota 02");
            System.out.println("6 - Nota 03");
            System.out.println("0 - RETORNAR\n");
            System.out.print("Digite a opção desejada: ");
            opc = sc.nextLine();

            switch (opc) {
                case "1":
                    System.out.print("Novo NOME: ");
                    novoAluno.setNome(sc.nextLine());
                    break;

                case "2":
                    System.out.print("Novo RA: ");
                    novoAluno.setRa(sc.nextLine());
                    break;

                case "3":
                    System.out.print("Novo EMAIL: ");
                    novoAluno.setEmail(sc.nextLine());
                    break;

                case "4":
                    System.out.print("Nova Nota 01: ");
                    novoAluno.setNota1(BigDecimal.valueOf(sc.nextDouble()));
                    sc.nextLine();
                    break;

                case "5":
                    System.out.print("Nova Nota 02: ");
                    novoAluno.setNota2(BigDecimal.valueOf(sc.nextDouble()));
                    sc.nextLine();
                    break;

                case "6":
                    System.out.print("Nova Nota 03: ");
                    novoAluno.setNota3(BigDecimal.valueOf(sc.nextDouble()));
                    sc.nextLine();
                    break;

                case "0":
                    System.out.print("SALVAR ALTERAÇÕES? (S/N) ");
                    String ans = sc.nextLine();
                    if (ans.equals("S") || ans.equals("s")) {
                        DataBaseService.getInstance().atualizarAluno(aluno.getId(), novoAluno.getNome(), novoAluno.getRa(), novoAluno.getEmail(),
                                novoAluno.getNota1().doubleValue(), novoAluno.getNota2().doubleValue(), novoAluno.getNota3().doubleValue());
                        System.out.println("Aluno alterado com sucesso!");
                    } else {
                        System.out.println("Alterações descartadas...");
                    }
                    break;

                default:
                    System.out.println("OPÇÃO INVALIDA!");
                    Tools.pause();
                    break;
            }

        } while (!opc.equals("0"));
    }

    private void buscarAlunoPorNome() {
        Tools.clearScreen();
        System.out.println("CONSULTAR ALUNO:");
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();

        Aluno aluno = DataBaseService.getInstance().buscarAlunoPorNome(nome);
        if (aluno != null) {
            System.out.println("Dados do Aluno:");
            System.out.println(aluno);
        }
    }

    private void cadastrarAluno() {
        Tools.clearScreen();
        System.out.println("CADASTRO DE ALUNO:");
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();
        System.out.print("Digite o ra: ");
        String ra = sc.nextLine();
        System.out.print("Digite o email: ");
        String email = sc.nextLine();
        System.out.print("Digite a nota 01: ");
        double nota1 = sc.nextDouble();
        System.out.print("Digite a nota 02: ");
        double nota2 = sc.nextDouble();
        System.out.print("Digite a nota 03: ");
        double nota3 = sc.nextDouble();

        DataBaseService.getInstance().cadastrarAluno(nome, ra, email, nota1, nota2, nota3);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private void listarTodos() {
        Tools.clearScreen();
        System.out.println("Exibindo todos os alunos:\n");

        List<Aluno> lsAlunos = AlunoDAO.getInstance().buscarTodos();
        for (Aluno aluno : lsAlunos) {
            System.out.println("\n" + aluno);
            BigDecimal avg = BigDecimal.valueOf(Tools.averageOf(List.of(aluno.getNota1(), aluno.getNota2(), aluno.getNota3())));
            System.out.println("Média: " + avg.setScale(1, RoundingMode.HALF_EVEN));

            System.out.print("Situação: ");
            if (avg.doubleValue() < 4.0) {
                System.out.println("Reprovado!");
            } else if (avg.doubleValue() >= 4.0 && avg.doubleValue() < 6.0) {
                System.out.println("Recuperação!");
            } else {
                System.out.println("Aprovado!");
            }
        }
        System.out.println();
    }

    private void removerAluno() {
        Tools.clearScreen();
        System.out.println("EXCLUIR ALUNO:");
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();

        Aluno aluno = AlunoDAO.getInstance().buscarPorNome(nome);
        System.out.println(aluno);
        System.out.print("Confirma Remoção? (S/N) ");
        String opc = sc.nextLine();

        if (opc.equals("S") || opc.equals("s")) {
            DataBaseService.getInstance().removerAluno(aluno.getId());
            System.out.println("Aluno removido com sucesso!");
        } else if (opc.equals("N") || opc.equals("n")) {
            System.out.println("Operação cancelada!");
        } else {
            System.out.println("Opção Inválida, Operação Cancelada!");
        }
    }
}
