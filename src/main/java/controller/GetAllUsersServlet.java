package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDetailsDAO;
import dao.UserDetailsDAOImpl;
import entities.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/getAllUsersServlet")
public class GetAllUsersServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(GetAllUsersServlet.class);
    private UserDetailsDAO userDetailsDAO;

    @Override
    public void init() throws ServletException {
        userDetailsDAO = new UserDetailsDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    	
        try {
            List<UserDetails> userList = userDetailsDAO.getAllUsers();

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            Gson gson = new Gson(); // Convert list to JSON
            String jsonResponse = gson.toJson(userList);

            resp.getWriter().write(jsonResponse);
        } catch (Exception e) {
            logger.error("Error in GetAllUsersServlet", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }
}
