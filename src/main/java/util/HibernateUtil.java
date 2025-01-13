package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            // Initialize the EntityManagerFactory
            entityManagerFactory = Persistence.createEntityManagerFactory("joint"); // Persistence unit name
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Get the EntityManagerFactory instance
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    // Create and return an EntityManager
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public static void shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
