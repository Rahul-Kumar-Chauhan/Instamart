import java.io.*;

import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Domain.*;
import BO.*;

@WebServlet("/ViewProduct")
public class ViewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ViewProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		String s=request.getParameter("productId");
		try{
		Product p=new ProductBO().findById(Integer.parseInt(s));
		session.setAttribute("product", p);
		Integer costPerMonth = 0;
		if( p != null){
			
			int cost =  new ProductRateBO().obtainProductRateByProduct(p).getRate();
			if(cost > 0){
				costPerMonth += cost;
			}
		}
		System.out.println(costPerMonth);
		request.setAttribute("product", p);
		System.out.println(costPerMonth);
		request.setAttribute("costPerMonth", costPerMonth);
		session.setAttribute("costPerMonth", costPerMonth);
		DiscountBO discountBO = new DiscountBO();
		ArrayList<Discount> discountList = discountBO.listAllDiscount();
		System.out.println(discountList);
		request.setAttribute("tensureList", discountList);
		session.setAttribute("tensureList", discountList);
		
		request.getRequestDispatcher("viewProduct.jsp").include(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
