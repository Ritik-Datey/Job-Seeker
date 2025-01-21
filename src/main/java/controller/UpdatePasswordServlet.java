package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VerificationDAO;
import dao.VerificationDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/updatePasswordServlet")
public class UpdatePasswordServlet extends HttpServlet {
    
    private static final Logger logger = LoggerFactory.getLogger(UpdatePasswordServlet.class);
    private VerificationDAO verificationDAO;

    @Override
    public void init() throws ServletException {
        verificationDAO = new VerificationDaoImpl();  // Initialize the DAO
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Retrieve parameters (email and new password)
            String email = req.getParameter("email");
            String newPassword = req.getParameter("newPassword");

            // Validate inputs
            if (email == null || email.isEmpty() || newPassword == null || newPassword.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email and new password are required.");
                return;
            }

            // Call the DAO method to update the password
            verificationDAO.updatePassword(email, newPassword);

            // Send success response
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Password updated successfully.");
        } catch (Exception e) {
            logger.error("Error in UpdatePasswordServlet", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the password.");
        }
    }
}
