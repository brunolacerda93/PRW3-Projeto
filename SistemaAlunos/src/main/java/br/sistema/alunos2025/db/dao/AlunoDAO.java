package br.sistema.alunos2025.db.dao;

import br.sistema.alunos2025.db.model.Aluno;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class AlunoDAO {

    private final EntityManager em;

    public AlunoDAO(EntityManager em) {
        this.em = em;
    }

    public void atualizar(Long id, String nome, String ra, String email,
                          BigDecimal nota1, BigDecimal nota2, BigDecimal nota3) {

        Aluno aluno = buscarPorId(id);
        if (aluno == null) {
            System.out.println("Aluno não encontrado...");
            return;
        }

        em.detach(aluno);
        aluno.setNome(nome);
        aluno.setRa(ra);
        aluno.setEmail(email);
        aluno.setNota1(nota1);
        aluno.setNota2(nota2);
        aluno.setNota3(nota3);
        em.getTransaction().begin();
        em.merge(aluno);
        em.getTransaction().commit();
    }

    public Aluno buscarPorId(Long id) {
        return em.find(Aluno.class, id);
    }

    public List<Aluno> buscarTodos() {
        return em.createQuery("from Aluno", Aluno.class).getResultList();
    }

    public void cadastrar(Aluno aluno) {
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
    }

    public void remover(Long id) {
        Aluno aluno = buscarPorId(id);
        em.getTransaction().begin();
        em.remove(aluno);
        em.getTransaction().commit();
    }
}
