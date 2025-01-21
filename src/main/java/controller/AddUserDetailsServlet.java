package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import dao.UserDetailsDAO;
import dao.UserDetailsDAOImpl;
import entities.UserDetails;
import entities.Verification;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/addUserServlet")
public class AddUserDetailsServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads"; // Folder to store uploaded files

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        UserDetailsDAO userDetailsDAO = new UserDetailsDAOImpl();

        try {
            // Retrieve form parameters
            String email = req.getParameter("email"); 
            String prefix = req.getParameter("prefix"); 
            String firstname = req.getParameter("firstname"); 
            String middlename = req.getParameter("middlename");
            String lastname = req.getParameter("lastname");
            String gender = req.getParameter("gender");
            String phone = req.getParameter("phone");
            String qualification = req.getParameter("qualification");
            String fieldOfStudy = req.getParameter("field_of_study");
            String position = req.getParameter("position");
            String experience = req.getParameter("experience");
            String skills = req.getParameter("skills");
            String coverLetter = req.getParameter("coverLetter");

            // Handle resume file upload
            Part resumePart = req.getPart("resume");
            String resumePath = null;
            if (resumePart != null && resumePart.getSize() > 0) {
                // Generate a unique filename
                String fileName = UUID.randomUUID().toString() + Paths.get(resumePart.getSubmittedFileName()).getFileName().toString();
                Path uploadPath = Paths.get(getServletContext().getRealPath("") + File.separator + UPLOAD_DIR);
                if (!uploadPath.toFile().exists()) {
                    uploadPath.toFile().mkdirs();
                }

                // Save file to the server
                resumePath = uploadPath.resolve(fileName).toString();
                resumePart.write(resumePath);
            }

            // Retrieve password from form (assuming it's passed)
            String password = req.getParameter("password");
            
            // Create Verification object
            Verification verification = new Verification(email, password, null);  // The userDetails will be set later
            
            // Create UserDetails object and set the verification
            UserDetails userDetails = new UserDetails(
                    email, prefix, firstname, middlename, lastname, gender, phone, 
                    qualification, fieldOfStudy, position, experience, skills, 
                    coverLetter, resumePath, verification);
            verification.setUserDetails(userDetails); // Set the back-reference to userDetails
            
            // Save using DAO
            userDetailsDAO.saveUserDetails(userDetails);

            // Send success response
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("User details added successfully.");
        
        } catch (Exception e) {
            // Log the error and send error response
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }
}
