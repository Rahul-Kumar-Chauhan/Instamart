import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import BO.ProductRateBO;
import BO.RentalAgreementBO;
import Domain.OrderDetail;
import Domain.Product;

@WebServlet("/Renewal")
public class Renewal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Renewal() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//fill your code here
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
