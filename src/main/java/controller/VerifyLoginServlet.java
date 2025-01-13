package controller;

import java.io.IOException;

import dao.VerificationDAO;
import dao.VerificationDaoImpl;
import entities.Verification;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/verifyLogin")
public class VerifyLoginServlet extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//super.doPost(req, resp);
		
		VerificationDAO verificationDAO = new VerificationDaoImpl();

        String email = req.getParameter("email");
        String password = req.getParameter("password"); 
		
		 try {
	            Verification v = verificationDAO.findByEmail(email);

	            if (v != null) {
	                if (v.getPassword().equals(password)) { 

	                    HttpSession session = req.getSession();
	                    session.setAttribute("userEmail", email);

	                    RequestDispatcher rd = req.getRequestDispatcher("/userDetails.html");
	                    rd.forward(req, resp);
	                    
	                } else {
	                	req.setAttribute("errorMessage", "Invalid Password. Please try again.");
	                    req.getRequestDispatcher("/logIn.jsp").forward(req, resp);
	                }
	            } else {
	            	req.setAttribute("errorMessage", "Email is not registered please Sign Up!");
                    req.getRequestDispatcher("/logIn.jsp").forward(req, resp);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            resp.getWriter().write("An error occurred. Please try again later.");
	        }
		
	}

}
