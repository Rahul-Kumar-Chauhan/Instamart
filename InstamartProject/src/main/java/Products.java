import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import BO.ProductBO;
import Domain.Product;
import Domain.Role;

@WebServlet("/Products")
public class Products extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Products() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String role = (String) request.getSession().getAttribute("role");
		
		request.setAttribute("role", role);
		if(role.equals("Customer")) {
			RequestDispatcher rd=request.getRequestDispatcher("listProducts.jsp");
			rd.include(request, response);
		}
		else if(role.equals("Manager")) {
			RequestDispatcher rd=request.getRequestDispatcher("listProducts.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
