import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import BO.BrandBO;
import BO.CatalogServiceAreaBO;
import BO.CatalogServiceSectorBO;
import BO.MaterialBO;
import BO.ProductBO;
import BO.ProductCategoryBO;
import BO.ProductRateBO;
import BO.ProductTypeBO;
import Domain.Brand;
import Domain.CatalogServiceArea;
import Domain.CatalogServiceSector;
import Domain.Material;
import Domain.Product;
import Domain.ProductCategory;
import Domain.ProductRate;
import Domain.ProductType;


@WebServlet("/CreateProduct")
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
           
    public CreateProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String name=(String)request.getParameter("name");
		String color=(String)request.getParameter("colors");
		String dimensions=(String)request.getParameter("dimensions");
		String city=(String)request.getParameter("city");
		String b=(String)request.getParameter("brand");
		String m=(String)request.getParameter("material");
		String css=(String)request.getParameter("catalogServiceSector");
		String csa=(String)request.getParameter("catalogServiceArea");
		String pc=(String)request.getParameter("productCategory");
		String pt=(String)request.getParameter("productType");
		String imageUrl=(String)request.getParameter("imageUrl");
		String rate=(String)request.getParameter("productRate");

		Brand brand=new Brand();
		CatalogServiceArea sa=new CatalogServiceArea();
		CatalogServiceSector ss=new CatalogServiceSector();
		Product p=new Product();

		p.setName(name);
		p.setColor(color);
		p.setDimensions(dimensions);
		p.setCity(city);
		
		
		ProductBO pbo=new ProductBO();
		
		response.sendRedirect("listProducts.jsp");
	}



	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
