package br.sistema.alunos2025.db;

import br.sistema.alunos2025.db.dao.AlunoDAO;
import br.sistema.alunos2025.db.model.Aluno;

import java.math.BigDecimal;

public class DataBaseService {

    private static final DataBaseService INSTANCE = new DataBaseService();

    public static DataBaseService getInstance() {
        return INSTANCE;
    }

    public DataBaseService() {
    }


    public Aluno atualizarAluno(Long id, String nome, String ra, String email, double nota1, double nota2, double nota3) {
        Aluno aluno = AlunoDAO.getInstance().buscarPorId(id);
        if (aluno == null) {
            System.out.println("Aluno não encontrado...");
            return null;
        }

        aluno.setNome(nome);
        aluno.setRa(ra);
        aluno.setEmail(email);
        aluno.setNota1(new BigDecimal(nota1));
        aluno.setNota2(new BigDecimal(nota2));
        aluno.setNota3(new BigDecimal(nota3));

        AlunoDAO.getInstance().atualizar(aluno);
        return aluno;
    }

    public Aluno buscarAlunoPorNome(String nome) {
        Aluno aluno = AlunoDAO.getInstance().buscarPorNome(nome);
        if (aluno == null) {
            System.out.println("Aluno não encontrado...");
        }
        return aluno;
    }

    public Aluno cadastrarAluno(String nome, String ra, String email, double nota1, double nota2, double nota3) {
        Aluno aluno = new Aluno();

        aluno.setNome(nome);
        aluno.setRa(ra);
        aluno.setEmail(email);
        aluno.setNota1(new BigDecimal(nota1));
        aluno.setNota2(new BigDecimal(nota2));
        aluno.setNota3(new BigDecimal(nota3));

        AlunoDAO.getInstance().cadastrar(aluno);
        return aluno;
    }

    public void removerAluno(Long id) {
        Aluno aluno = AlunoDAO.getInstance().buscarPorId(id);
        if (aluno == null) {
            System.out.println("Aluno não encontrado...");
            return;
        }

        AlunoDAO.getInstance().remover(aluno);
    }
}
