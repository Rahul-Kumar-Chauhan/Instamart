import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import BO.CompanyBO;
import BO.OrderDetailBO;
import BO.RentalAgreementBO;
import Domain.Address;
import Domain.Company;
import Domain.Discount;
import Domain.OrderDetail;
import Domain.Product;
import Domain.RentalPackage;
import Domain.User;

@WebServlet("/Rentals")
public class Rentals extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Rentals() {
        super();
        // TODO Auto-generated constructor stub    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,NumberFormatException {
		// TODO Auto-generated method stub
		try{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String delDate =(String)request.getParameter("deliveryDate");
		String delTime = (String)request.getParameter("deliveryTime");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date deliveryDate=null;
		try {
			deliveryDate=sdf.parse(delDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Address choosenAddress =(Address) session.getAttribute("address");
		Product p =(Product) session.getAttribute("product");
		System.out.println(p);
		Discount discount =  (Discount)session.getAttribute("discount");
		System.out.println(discount+"discount");
		ArrayList<Product> ChoosedProductList = new ArrayList<Product>();
		ChoosedProductList.add((Product) session.getAttribute("product"));
		session.setAttribute("product",p);
    	request.setAttribute("product",p);
		System.out.println(ChoosedProductList);
		Integer monCost = (Integer)session.getAttribute("costPerMonth");
		System.out.println(monCost+"monCost");
		//Double mCos=(Double.parseDouble(monCost));
	   // int cost = monCost.intValue();
	   // int cost=(Integer.parseInt(monCost));
	    //Integer cost = cost1.;
		OrderDetail orderObj = new OrderDetail(user, user.getUsername(), user.getContactNo(),new Date(), deliveryDate, delTime,
        choosenAddress,ChoosedProductList,new ArrayList<RentalPackage>(), 20);
		String agreementNumber = "INS"+orderObj.getId() +"U" + orderObj.getUser().getId()+ "V"+0;
        if(orderObj!=null){
        	session.setAttribute("orderObj",orderObj);
        	request.setAttribute("orderObj",orderObj);
        }
		//Double totCost = (Double)session.getAttribute("totCost");
		//System.out.println(totCost);
	   // int totalCost = totCost.intValue();
        //Integer totalCost=(Integer)session.getAttribute("amount");
        Calendar cal = Calendar.getInstance();
        cal.setTime(orderObj.getDeliveryDate());
        cal.add(Calendar.MONTH, discount.getTenure());
        Date expiryDate = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date renewalDate = cal.getTime();
        Company company = new CompanyBO().getCompany();
        Domain.RentalAgreement rentalAgreement= new Domain.RentalAgreement(agreementNumber,company, orderObj, discount.getTenure(),
        		orderObj.getOrderedDate(), orderObj.getDeliveryDate(), renewalDate,
    			expiryDate, discount, 20);
        rentalAgreement = new RentalAgreementBO().save(rentalAgreement);
        if(rentalAgreement!=null){
        	session.setAttribute("rentals",rentalAgreement);
        	request.setAttribute("rentals",rentalAgreement);
        }
        
        RequestDispatcher rd=request.getRequestDispatcher("rentals.jsp");
		rd.include(request, response);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
