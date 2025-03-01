package br.sistema.alunos2025.db.dao;

import br.sistema.alunos2025.utils.JPAUtil;
import jakarta.persistence.EntityManager;

public class BaseDAO<T> {

    protected final EntityManager em = JPAUtil.getEntityManager();

    public void atualizar(T object) {
        em.detach(object);
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
    }

    public void cadastrar(T object) {
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    public void remover(T object) {
        em.getTransaction().begin();
        em.remove(object);
        em.getTransaction().commit();
    }
}
