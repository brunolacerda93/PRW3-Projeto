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

        do {
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

    // TODO
    private void alterarAluno() {}

    private void buscarAlunoPorNome() {
        Tools.clearScreen();
        System.out.println("CONSULTAR ALUNO:");
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();
        System.out.println(AlunoDAO.getInstance().buscarPorNome(nome));
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

    }

    private void removerAluno() {
        Tools.clearScreen();
        System.out.println("EXCLUIR ALUNO:");
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();

        Aluno aluno = AlunoDAO.getInstance().buscarPorNome(nome);
        System.out.println(aluno);
        System.out.println("Confirma Remoção? (S/N)");
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
