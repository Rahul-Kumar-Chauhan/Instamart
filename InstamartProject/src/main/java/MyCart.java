import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import BO.AddressBO;
import BO.DiscountBO;
import BO.ProductBO;
import Domain.Address;
import Domain.Discount;
import Domain.Product;
import Domain.User;

@WebServlet("/MyCart")
public class MyCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		HttpSession session=request.getSession();
		
		AddressBO addressBO=new AddressBO();
		Address address=null;
		String addressId=request.getParameter("addressId");
		int addId=0;
		if(addressId != null) {
			try {
				address=addressBO.findById(Integer.parseInt(addressId));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("address", address);
			request.setAttribute("addressId", Integer.parseInt(addressId));
			request.getRequestDispatcher("myCart.jsp").forward(request, response);
		}
		else if(addressId==null){
			request.setAttribute("addressId", 0);
		}
		System.out.println("Test");
		String monthCost=request.getParameter("monthCost");
		String totalCost=request.getParameter("totCost");
		System.out.println(monthCost);
		System.out.println(totalCost);

		
		session.setAttribute("monthCost", monthCost);
		session.setAttribute("totCost", totalCost);
		
		Object a = session.getAttribute("addressId");
		if(a==null) {
			response.sendRedirect("myCart.jsp");

		}if(!(a==null)) {
			request.getRequestDispatcher("myCart.jsp").include(request, response);	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
