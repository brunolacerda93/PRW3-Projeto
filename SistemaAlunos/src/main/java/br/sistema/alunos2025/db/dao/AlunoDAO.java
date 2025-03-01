package br.sistema.alunos2025.db.dao;

import br.sistema.alunos2025.db.model.Aluno;
import br.sistema.alunos2025.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class AlunoDAO extends BaseDAO<Aluno> {

    private final static AlunoDAO INSTANCE = new AlunoDAO();

    public static AlunoDAO getInstance() {
        return INSTANCE;
    }


    public AlunoDAO() {
    }

    public Aluno buscarPorId(Long id) {
        return em.find(Aluno.class, id);
    }

    public Aluno buscarPorNome(String nome) {
        return em.createQuery("from Aluno where nome = :nome", Aluno.class)
                .setParameter("nome", nome)
                .getSingleResult();
    }

    public List<Aluno> buscarTodos() {
        return em.createQuery("from Aluno", Aluno.class)
                .getResultList();
    }
}
