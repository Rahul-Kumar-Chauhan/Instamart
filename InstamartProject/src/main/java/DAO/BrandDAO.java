package DAO;

import Domain.Brand;
import Utility.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO {
    public Brand obtainBrandById(Integer id) throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getConnection();
        
        PreparedStatement ps=connection.prepareStatement("select * from brand where id=?");
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();
       
        Brand brand = null;
        if(rs.next()) {
        	brand=new Brand(rs.getInt(1), rs.getString(2));
        }
        rs.close();
        connection.close();
        ps.close();
		return brand;
    }
    
    public Brand obtainBrandByName(String name) throws ClassNotFoundException, SQLException{
        Brand brand = null;
        Connection connection = DBConnection.getConnection();
        
        PreparedStatement ps=connection.prepareStatement("select * from brand where name=?");
        ps.setString(1, name);
        ResultSet rs=ps.executeQuery();
   
        if(rs.next()) {
        	brand=new Brand(rs.getInt(1), rs.getString(2));
        }
        
        rs.close();
        connection.close();
        ps.close();
        return brand;
    }
    
    public List<Brand> listBrands() throws ClassNotFoundException, SQLException{
		List<Brand> brandList = new ArrayList<Brand>();
		Connection con = DBConnection.getConnection();
		
		Statement stmt=con.createStatement();
		String sql="select * from brand";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next()) {
			 int id = rs.getInt(1);
			 String name=rs.getString(2);
			 Brand brand=new Brand(id,name);
			 brandList.add(brand);
		}
		
		rs.close();
		stmt.close();
		con.close();
		return brandList;
	}
}
