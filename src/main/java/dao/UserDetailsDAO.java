package dao;

import java.util.List;

import entities.UserDetails;

public interface UserDetailsDAO {
	
	void saveUserDetails(UserDetails userDetails);
	void updateUserDetails(UserDetails userDetails);
	boolean deleteUser(String email); 
	List<UserDetails> getAllUsers();
	UserDetails getUserByEmail(String email);

}

//public class GetAllUsersServlet extends HttpServlet {
//    private static final Logger logger = LoggerFactory.getLogger(GetAllUsersServlet.class);
//    private UserDetailsDAO userDetailsDAO;
//
//    @Override
//    public void init() throws ServletException {
//        userDetailsDAO = new UserDetailsDAOImpl();
//    }
//
//    @Override
//    protected void doGet(HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            List<UserDetails> userList = userDetailsDAO.getAllUsers();
//
//            resp.setContentType("application/json");
//            resp.setCharacterEncoding("UTF-8");
//
//            Gson gson = new Gson(); // Convert list to JSON
//            String jsonResponse = gson.toJson(userList);
//
//            resp.getWriter().write(jsonResponse);
//        } catch (Exception e) {
//            logger.error("Error in GetAllUsersServlet", e);
//            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
//        }
//    }