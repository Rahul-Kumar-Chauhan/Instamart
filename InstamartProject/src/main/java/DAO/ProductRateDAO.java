package DAO;

import Domain.Brand;
import Domain.CatalogServiceArea;
import Domain.CatalogServiceSector;
import Domain.Material;
import Domain.Product;
import Domain.ProductCategory;
import Domain.ProductRate;
import Domain.ProductType;
import Utility.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import BO.BrandBO;
import BO.CatalogServiceAreaBO;
import BO.CatalogServiceSectorBO;
import BO.MaterialBO;
import BO.ProductCategoryBO;
import BO.ProductTypeBO;

public class ProductRateDAO {
	public ProductRate obtainProductRateById(Product product) throws ClassNotFoundException, SQLException {
		ProductRate productRate = null;
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from product_rate where id = ?");
		int productId = product.getId();
		ps.setInt(1, productId);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			productRate = new ProductRate(rs.getInt("id"), product, rs.getInt("rate"), rs.getTimestamp("start_date"),
					rs.getTimestamp("end_date"));
		}

		connection.close();
		return productRate;
	}

	public ProductRate createProductRate(ProductRate rate) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con
				.prepareStatement("insert into product_rate(product_id,rate,start_date) values(?,?,?)");
		ps.setInt(1, rate.getProduct().getId());
		ps.setInt(2, rate.getRate());
		ps.setTimestamp(3, (Timestamp) rate.getStartDate());

		int rowInserted = ps.executeUpdate();
		return rate;
	}
}
