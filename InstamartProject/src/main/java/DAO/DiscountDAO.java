package DAO;

import java.beans.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BO.AddressBO;
import BO.DiscountBO;
import BO.ProductBO;
import Domain.Discount;
import Domain.Product;
import Utility.DBConnection;


public class DiscountDAO {
	 public ArrayList<Discount> listAllDiscount() throws ClassNotFoundException, SQLException{
		 	ArrayList<Discount> discountList = new ArrayList<Discount>();
	        Connection connection = DBConnection.getConnection();
	        PreparedStatement ps=connection.prepareStatement("select * from discount");
	        ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				 Discount discount=new Discount(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				 discountList.add(discount);
			}
	        connection.close();
	        return discountList;
	    }
	 
	 public Discount findById(Integer id) throws ClassNotFoundException, SQLException{
		 	Discount discount = null;
		 	Connection connection = DBConnection.getConnection();
	        PreparedStatement ps=connection.prepareStatement("select * from discount where id=?;");
	        ps.setInt(1, id);
	        ResultSet rs=ps.executeQuery();
	        if(rs.next()) {
	        	discount=new Discount(rs.getInt(2), rs.getInt(3));
	        }
	        connection.close();
	        return discount;
	 }
}
