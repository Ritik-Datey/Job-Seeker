package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDetailsDAO;
import dao.UserDetailsDAOImpl;
import entities.UserDetails;
import entities.Verification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/updateUserServlet")
public class UpdateUserDetailsServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UpdateUserDetailsServlet.class);
    private UserDetailsDAO userDetailsDAO;

    @Override
    public void init() throws ServletException {
        userDetailsDAO = new UserDetailsDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Retrieve parameters with basic validation
            String email = req.getParameter("email");
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            if (email == null || firstname == null || lastname == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Required fields are missing.");
                return;
            }

            String prefix = req.getParameter("prefix");
            String middlename = req.getParameter("middlename");
            String gender = req.getParameter("gender");
            String phone = req.getParameter("phone");
            String qualification = req.getParameter("qualification");
            String fieldOfStudy = req.getParameter("field_of_study");
            String position = req.getParameter("position");
            String experience = req.getParameter("experience");
            String skills = req.getParameter("skills");
            String coverLetter = req.getParameter("coverLetter");
            String resume = req.getParameter("resume");
            String password = req.getParameter("password"); // Assuming password is passed for verification update

            // Retrieve existing user details
            UserDetails existingUserDetails = userDetailsDAO.getUserByEmail(email);
            if (existingUserDetails == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found.");
                return;
            }

            // Update existing UserDetails fields
            existingUserDetails.setPrefix(prefix);
            existingUserDetails.setFirstName(firstname);
            existingUserDetails.setMiddleName(middlename);
            existingUserDetails.setLastName(lastname);
            existingUserDetails.setGender(gender);
            existingUserDetails.setPhone(phone);
            existingUserDetails.setQualification(qualification);
            existingUserDetails.setFieldOfStudy(fieldOfStudy);
            existingUserDetails.setPosition(position);
            existingUserDetails.setExperience(experience);
            existingUserDetails.setSkills(skills);
            existingUserDetails.setCoverLetter(coverLetter);
            existingUserDetails.setResumePath(resume);

            // If password is updated, update the Verification entity
            if (password != null && !password.isEmpty()) {
                Verification verification = existingUserDetails.getVerification();
                if (verification != null) {
                    verification.setPassword(password); // Update password
                } else {
                    // Handle case where verification is not found (this should not typically happen)
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Verification details are missing.");
                    return;
                }
            }

            // Update using DAO
            userDetailsDAO.updateUserDetails(existingUserDetails);

            // Send success response
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("User details updated successfully.");
        } catch (Exception e) {
            logger.error("Error in UpdateUserDetailsServlet", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }
}
