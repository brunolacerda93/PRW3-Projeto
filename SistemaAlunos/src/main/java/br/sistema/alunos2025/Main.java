package br.sistema.alunos2025;

import br.sistema.alunos2025.db.DataBaseService;
import br.sistema.alunos2025.db.dao.AlunoDAO;
import br.sistema.alunos2025.db.model.Aluno;
import br.sistema.alunos2025.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.logging.Level;

public class Main {

    public static void main(String[] args) {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        DataBaseService.getInstance().cadastrarAluno("Aluno 01", "", "", 0, 0, 0);
        DataBaseService.getInstance().cadastrarAluno("Aluno 02", "", "", 0, 0, 0);

        for (Aluno a : AlunoDAO.getInstance().buscarTodos()) {
            System.out.println(a.getNome());
        }

        DataBaseService.getInstance().atualizarAluno(AlunoDAO.getInstance().buscarTodos().getFirst().getId(), "Editado", "", "", 0, 0, 0);

        for (Aluno a : AlunoDAO.getInstance().buscarTodos()) {
            System.out.println(a.getNome());
        }

        DataBaseService.getInstance().removerAluno(AlunoDAO.getInstance().buscarTodos().getFirst().getId());

        for (Aluno a : AlunoDAO.getInstance().buscarTodos()) {
            System.out.println(a.getNome());
        }
    }
}
