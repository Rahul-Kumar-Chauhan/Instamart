import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import BO.RoleBO;
import BO.UserBO;
import Utility.DBConnection;
import DAO.UserDAO;
import Domain.Address;
import Domain.Role;
import Domain.User;

@WebServlet("/Validation")
public class Validation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Validation() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String role = (String) request.getSession().getAttribute("role");
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		System.out.println(role);
		
		if(role.equals("Customer")) {
			RequestDispatcher rd=request.getRequestDispatcher("customerHome.jsp");
			rd.forward(request, response);
		}
		else if(role.equals("Manager")) {
			RequestDispatcher rd=request.getRequestDispatcher("managerHome.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
