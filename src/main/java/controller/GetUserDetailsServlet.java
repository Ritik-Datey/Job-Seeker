package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDetailsDAO;
import entities.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/getUserDetailsServlet")
public class GetUserDetailsServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(GetUserDetailsServlet.class);

    private UserDetailsDAO userDetailsDAO;

    @Override
    public void init() throws ServletException {
        userDetailsDAO = new dao.UserDetailsDAOImpl();
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String email = req.getParameter("email").trim();
            if (email == null || email.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email is required.");
                return;
            }

            // Fetch the user details by email
            UserDetails user = userDetailsDAO.getUserByEmail(email);
            if (user == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("No user found with the provided email.");
                return;
            }

            // Set response type and character encoding
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            // Convert the user object to JSON
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(user);

            // Send the JSON response
            resp.getWriter().write(jsonResponse);
        } catch (Exception e) {
            // Log error
            logger.error("Error in GetUserDetailsServlet", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }
}
