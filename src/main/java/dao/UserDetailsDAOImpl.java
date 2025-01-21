package dao;

import java.util.List;

import entities.UserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import util.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserDetailsDAOImpl implements  UserDetailsDAO {
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsDAOImpl.class);

	static EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();
	static EntityManager em = emf.createEntityManager();
	static EntityTransaction et = em.getTransaction();
	
	@Override
    public void saveUserDetails(UserDetails userDetails) {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = HibernateUtil.getEntityManagerFactory().createEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(userDetails);
            et.commit();
            logger.info("User details saved successfully: {}", userDetails);
        } catch (Exception e) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
            logger.error("Error saving user details", e);
            throw new RuntimeException("Error saving user details", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
	}

	 @Override
	public void updateUserDetails(UserDetails userDetails) {
	        EntityManager em = null;
	        EntityTransaction et = null;

	        try {
	            em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	            et = em.getTransaction();
	            et.begin();
	            em.merge(userDetails); // Merge for updating existing entity
	            et.commit();
	            logger.info("User details updated successfully: {}", userDetails);
	        } catch (Exception e) {
	            if (et != null && et.isActive()) {
	                et.rollback();
	            }
	            logger.error("Error updating user details", e);
	            throw new RuntimeException("Error updating user details", e);
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	    }

	

	@Override
	public boolean deleteUser(String email) {
	    
	    boolean isDeleted = false;

	    try {
	       
	        et.begin();

	        UserDetails user = em.find(UserDetails.class, email); // Assume email is the primary key
	        if (user != null) {
	            em.remove(user);
	            isDeleted = true; // Set to true if deletion was successful
	            logger.info("User deleted successfully: {}", email);
	        } else {
	            logger.warn("No user found with email: {}", email);
	        }

	        et.commit();
	    } catch (Exception e) {
	        if (et != null && et.isActive()) {
	            et.rollback();
	        }
	        logger.error("Error deleting user", e);
	        throw new RuntimeException("Error deleting user", e);
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }

	    return isDeleted; // Return boolean indicating success/failure
	}
	public List<UserDetails> getAllUsers() {
	        EntityManager em = null;

	        try {
	            em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	            TypedQuery<UserDetails> query = em.createQuery("SELECT u FROM UserDetails u", UserDetails.class);
	            List<UserDetails> userList = query.getResultList();
	            logger.info("Retrieved all users successfully.");
	            return userList;
	        } catch (Exception e) {
	            logger.error("Error retrieving users", e);
	            throw new RuntimeException("Error retrieving users", e);
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	    }

	@Override
    public UserDetails getUserByEmail(String email) {
        EntityManager em = null;
        try {
            em = HibernateUtil.getEntityManagerFactory().createEntityManager();
            UserDetails user = em.find(UserDetails.class, email); // Assuming email is the primary key
            if (user != null) {
                logger.info("User found: {}", user);
            } else {
                logger.warn("No user found with email: {}", email);
            }
            return user;
        } catch (Exception e) {
            logger.error("Error retrieving user by email", e);
            throw new RuntimeException("Error retrieving user by email", e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
	}
	
	
}	
	
