import jakarta.servlet.Filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BO.RoleBO;
import BO.UserBO;
import Utility.DBConnection;
import Domain.User;
import Domain.Role;

@WebFilter("/Validation")
public class AuthenticationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Initialization code if needed
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = null;
		RoleBO roleBO = new RoleBO();
		UserBO userBO = new UserBO();
		try {
			user = userBO.validateLogin(username, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (user != null) {
				Role roleObj = roleBO.findRoleById(user.getRole().getId());
				String role = roleObj.getName();
				System.out.println(role);
				HttpSession session = httpRequest.getSession();
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				session.setAttribute("id", user.getId());
				session.setAttribute("role", role);
				session.setAttribute("user", user);
				chain.doFilter(request, response);
			} else {
				request.setAttribute("error", "Invalid Username or Password");
				request.getRequestDispatcher("index.jsp").forward(httpRequest, httpResponse);
			}
		} catch (ClassNotFoundException | SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {

	}
}
