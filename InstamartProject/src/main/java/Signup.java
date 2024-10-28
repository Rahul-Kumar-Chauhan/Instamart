import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import BO.RoleBO;
import BO.UserBO;
import Domain.Role;
import Domain.User;

public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBO userBO=new UserBO();
		User user=new User();
		user.setFirstName(request.getParameter("firstname"));
		user.setLastName(request.getParameter("lastname"));
		user.setUsername(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		System.out.println();
		try {
			if(userBO.findUserByUsername(user.getUsername())) {
				request.setAttribute("error","Username is already taken" );
				request.getRequestDispatcher("signup.jsp").forward(request, response);;
			}else {
				userBO.createUser(user);
				request.getRequestDispatcher("profileDetails.jsp").forward(request, response);;
			} 
		}
			catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}
		//doGet(request, response);
	}
}