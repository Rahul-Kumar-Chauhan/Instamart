import java.io.IOException;
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

import BO.UserBO;
import Domain.Role;
import Domain.User;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBO userBO=new UserBO();
		HttpSession session=request.getSession();
		int id =(int) session.getAttribute("id");
		User userObj = null;
		try {
			userObj = userBO.findById(id);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String firstname=request.getParameter("firstname");
		String lastname=request.getParameter("lastname");
		System.out.println(lastname);
		String stringDob=request.getParameter("dob");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date dob=null;
		try {
			dob=sdf.parse(stringDob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println(dob);
		int age=Integer.parseInt(request.getParameter("age"));
		String contactNo=request.getParameter("contactNo");
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Role role=userObj.getRole();
		User user=new User(userObj.getId(), firstname, lastname, dob, age, contactNo, email,
				 username, password, role);
		try {
			userBO.updateProfile(user);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("settings.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
}
