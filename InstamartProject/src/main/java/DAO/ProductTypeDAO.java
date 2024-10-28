package DAO;

import BO.ProductBO;

import BO.ProductCategoryBO;
import Domain.Product;
import Domain.ProductCategory;
import Domain.ProductRate;
import Domain.ProductType;
import Utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDAO {
	public ProductType obtainProductTypeById(Integer id) throws ClassNotFoundException, SQLException {
		ProductType productType = null;
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from product_type where id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		ProductCategoryBO pcBO = new ProductCategoryBO();
		if (rs.next()) {
			int pcId = rs.getInt("product_category_id");
			ProductCategory pc = pcBO.obtainProductCategoryById(pcId);
			productType = new ProductType(rs.getInt("id"), rs.getString("type"), pc);
		}

		connection.close();
		return productType;
	}

	public ProductType obtainProductTypeByType(String type) throws ClassNotFoundException, SQLException {
		ProductType productType = null;
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from product_type where type = ?");
		ps.setString(1, type);
		ResultSet rs = ps.executeQuery();

		ProductCategoryBO pcBO = new ProductCategoryBO();
		if (rs.next()) {
			int pcId = rs.getInt("product_category_id");
			ProductCategory pc = pcBO.obtainProductCategoryById(pcId);
			productType = new ProductType(rs.getInt("id"), rs.getString("type"), pc);

		}

		connection.close();
		return productType;
	}

	public List<ProductType> listProductTypes(ProductCategory productCategory)
			throws ClassNotFoundException, SQLException {
		List<ProductType> productTypes = new ArrayList<ProductType>();
		Connection con = DBConnection.getConnection();

		int pcId = productCategory.getId();
		PreparedStatement ps = con
				.prepareStatement("select * from product_type where product_category_id =" + pcId + "");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			int id = rs.getInt(1);
			String type = rs.getString(2);
			ProductType pt = new ProductType(id, type, productCategory);
			productTypes.add(pt);
		}

		con.close();
		return productTypes;
	}

	public List<ProductType> obtainAllProductType() throws ClassNotFoundException, SQLException {
		List<ProductType> productTypes = new ArrayList<ProductType>();
		Connection con = DBConnection.getConnection();

		PreparedStatement ps = con.prepareStatement("select * from product_type");
		ResultSet rs = ps.executeQuery();

		ProductCategoryBO pcBO = new ProductCategoryBO();
		while (rs.next()) {
			int id = rs.getInt(1);
			String type = rs.getString(1);
			int pcId = rs.getInt(3);
			ProductCategory pc = pcBO.obtainProductCategoryById(pcId);
			ProductType pt = new ProductType(id, type, pc);
			productTypes.add(pt);
		}

		con.close();
		return productTypes;
	}
}
