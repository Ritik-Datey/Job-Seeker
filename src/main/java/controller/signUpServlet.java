package controller;

import java.io.IOException;

import dao.VerificationDAO;
import dao.VerificationDaoImpl;
import entities.Verification;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/signUpServlet")
public class signUpServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//super.doPost(req, resp);	
		
		 try {
			 
			 VerificationDAO verificationDAO = new VerificationDaoImpl();
			 
	            String email = req.getParameter("email");
	            String password = req.getParameter("password");
	            
	            HttpSession session = req.getSession();
	            session.setAttribute("userEmail", email);

//	            if (email == null || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
//	                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//	                resp.getWriter().write("Invalid email format.");
//	                return;
//	            }
//
//	            if (password == null || password.length() < 6) {
//	                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//	                resp.getWriter().write("Password must be at least 6 characters.");
//	                return;
//	            }

	            // verification checks.........
	            Verification existingVerification = verificationDAO.findByEmail(email);

	            if (existingVerification != null) {
                    req.setAttribute("errorMessage", "Email already registered.");
                    req.getRequestDispatcher("/signUp.jsp").forward(req, resp);
	                return;
	            }

	            //adding emails.............
	            Verification verification = new Verification();
	            verification.setEmail(email);
	            verification.setPassword(password);

	            verificationDAO.saveVerification(verification);

	            req.setAttribute("successMessage", "Registration successful! Please log in.");
	            req.getRequestDispatcher("/logIn.jsp").forward(req, resp);
//	            logger.info("Verification details saved successfully for email: " + email);
	            
	        } catch (Exception e) {
//	            logger.severe("Error processing registration: " + e.getMessage());
	            throw new ServletException("Error processing registration", e);
	        }
		
		
	}

}
