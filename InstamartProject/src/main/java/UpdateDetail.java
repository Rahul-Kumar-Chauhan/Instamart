import java.io.IOException;



import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import BO.AddressBO;
import BO.UserBO;
import Domain.Address;
import Domain.User;
 
public class UpdateDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public UpdateDetail() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBO userbo=new UserBO();
		User u=new User();
		Address add=new Address();
		u.setAge(Integer.parseInt(request.getParameter("age")));
		Date dob;
		try {
			dob = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dob"));
			u.setDob(dob);
			u.setContactNo(request.getParameter("contactNo"));
			userbo.updateContactDetail(u);
			add.setCity(request.getParameter("city"));
			add.setCountry(request.getParameter("country"));
			add.setState(request.getParameter("state"));
			add.setPincode(Integer.parseInt(request.getParameter("pinCode")));
			add.setStreet(request.getParameter("street"));
			request.setAttribute("success", "Successfully Registered Login to Continue");

			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
