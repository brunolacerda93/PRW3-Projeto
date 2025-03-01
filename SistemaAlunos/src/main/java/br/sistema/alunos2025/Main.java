package br.sistema.alunos2025;

import br.sistema.alunos2025.db.dao.AlunoDAO;
import br.sistema.alunos2025.db.model.Aluno;
import br.sistema.alunos2025.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        Aluno aluno = new Aluno();
        aluno.setNome("Aluno1");
        Aluno aluno2 = new Aluno();
        aluno2.setNome("Aluno2");

        EntityManager em = JPAUtil.getEntityManager();

        AlunoDAO alunoDAO = new AlunoDAO(em);
        alunoDAO.cadastrar(aluno);
        alunoDAO.cadastrar(aluno2);

        for (Aluno a : alunoDAO.buscarTodos()) {
            System.out.println(a.getNome());
        }

        alunoDAO.remover(alunoDAO.buscarTodos().getFirst().getId());

        for (Aluno a : alunoDAO.buscarTodos()) {
            System.out.println(a.getNome());
        }

        em.close();
    }
}
