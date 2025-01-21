package dao;

import entities.Verification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import util.HibernateUtil;

public class VerificationDaoImpl implements VerificationDAO {
	
	static EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();
    
    @Override
    public void saveVerification(Verification verification) {

        try {
        
            et.begin();
            em.persist(verification);  // Save the verification entity to the database
            et.commit();
            
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();  // Rollback the transaction in case of error
            }
            throw new RuntimeException("Error saving verification", e);
        } finally {
            if (em != null) {
                em.close();  // Close EntityManager
            }
        }
    }

    @Override
    public Verification findByEmail(String email) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getEntityManagerFactory().createEntityManager();
            return em.find(Verification.class, email);  // Retrieve verification by email (assuming email is the primary key)
        } catch (Exception e) {
            throw new RuntimeException("Error finding verification by email", e);
        } finally {
            if (em != null) {
                em.close();  // Close EntityManager
            }
        }
    }
    
    @Override
    public void updatePassword(String email, String newPassword) {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = HibernateUtil.getEntityManagerFactory().createEntityManager();
            et = em.getTransaction();
            et.begin();

            Verification verification = em.find(Verification.class, email);  // Find the Verification by email

            if (verification != null) {
                verification.setPassword(newPassword);  // Update the password
                em.merge(verification);  // Merge the updated entity
                et.commit();  // Commit the transaction
            } else {
                throw new RuntimeException("No verification found with the provided email.");
            }
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();  // Rollback the transaction in case of error
            }
            throw new RuntimeException("Error updating password", e);
        } finally {
            if (em != null) {
                em.close();  // Close EntityManager
            }
        }
    }
}
