package dao;

import entities.Verification;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import util.HibernateUtil;
import dao.VerificationDAO;

public class VerificationDaoImpl implements VerificationDAO {

	static EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();
	
    public VerificationDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
    public Verification findByEmail(String email) {
   
        Verification verification = null;
        try {
            verification = em.find(Verification.class, email);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return verification;
    }

	@Override
	public void saveVerification(Verification verification) {
		
		   try {
	            et.begin();
	            em.persist(verification);
	            et.commit();
	        } catch (Exception e) {
	            if (et.isActive()) {
	                et.rollback();
	            }
	            throw new RuntimeException("Error saving verification", e);
	        }
		   
	}
	
}

