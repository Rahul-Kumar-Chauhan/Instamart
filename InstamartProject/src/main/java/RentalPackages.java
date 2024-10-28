import java.io.IOException;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import BO.ProductBO;
import BO.RentalPackageBO;
import Domain.Product;
import Domain.RentalPackage;

@WebServlet("/RentalPackages")
public class RentalPackages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RentalPackages() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String role = (String) request.getSession().getAttribute("role");
		RequestDispatcher rd=request.getRequestDispatcher("listPackages.jsp");
		rd.include(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
