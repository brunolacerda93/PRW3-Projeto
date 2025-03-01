package br.sistema.alunos2025.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("sistema");
    private static final EntityManager EM = FACTORY.createEntityManager();

    public static EntityManager getEntityManager() {
        return EM;
    }
}
