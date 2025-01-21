package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDetailsDAO;
import dao.UserDetailsDAOImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/deleteUserServlet")
public class DeleteUserDetailsServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DeleteUserDetailsServlet.class);
    private UserDetailsDAO userDetailsDAO;

    @Override
    public void init() throws ServletException {
        userDetailsDAO = new UserDetailsDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            if (email == null || email.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email is required.");
                return;
            }

            // Call the DAO method to delete the user
            boolean isDeleted = userDetailsDAO.deleteUser(email);
            if (isDeleted) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("User deleted successfully.");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("User not found with the provided email.");
            }
        } catch (Exception e) {
            logger.error("Error in DeleteUserDetailsServlet", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }
}
