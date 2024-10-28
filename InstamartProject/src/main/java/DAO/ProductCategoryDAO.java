package DAO;

import BO.CatalogServiceAreaBO;
import BO.ProductCategoryBO;
import Domain.Brand;
import Domain.CatalogServiceArea;
import Domain.ProductCategory;
import Domain.ProductType;
import Utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDAO {
	public ProductCategory obtainProductCategoryById(Integer id) throws ClassNotFoundException, SQLException {
		ProductCategory productCategory = null;
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from product_category where id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		CatalogServiceAreaBO csaBO = new CatalogServiceAreaBO();
		if (rs.next()) {
			CatalogServiceArea csa = csaBO.obtainCatalogServiceAreaById(rs.getInt("catalog_service_area_id"));
			productCategory = new ProductCategory(rs.getInt("id"), rs.getString("category"), csa);
		}

		connection.close();
		return productCategory;
	}

	public ProductCategory obtainProductCategoryByCategory(String category)
			throws ClassNotFoundException, SQLException {
		ProductCategory productCategory = null;
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from product_category where category = ?");
		ps.setString(1, category);
		ResultSet rs = ps.executeQuery();

		CatalogServiceAreaBO csaBO = new CatalogServiceAreaBO();
		if (rs.next()) {
			CatalogServiceArea csa = csaBO.obtainCatalogServiceAreaById(rs.getInt("catalog_service_area_id"));
			productCategory = new ProductCategory(rs.getInt("id"), rs.getString("category"), csa);
		}

		connection.close();
		return productCategory;
	}

	public List<ProductCategory> listAllProductCategories() throws ClassNotFoundException, SQLException {
		List<ProductCategory> productCategory = new ArrayList();
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from product_category");
		ResultSet rs = ps.executeQuery();

		CatalogServiceAreaBO csaBO = new CatalogServiceAreaBO();
		while (rs.next()) {
			int id = rs.getInt(1);
			String category = rs.getString(2);
			int csaId = rs.getInt(3);
			CatalogServiceArea csa = csaBO.obtainCatalogServiceAreaById(csaId);
			ProductCategory pc = new ProductCategory(id, category, csa);
			productCategory.add(pc);
		}

		connection.close();
		return productCategory;
	}

	public List<ProductCategory> listProductCategory(CatalogServiceArea serviceArea)
			throws ClassNotFoundException, SQLException {
		List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
		Connection con = DBConnection.getConnection();

		int csaId = serviceArea.getId();
		PreparedStatement ps = con
				.prepareStatement("select * from product_category where catalog_service_id=" + csaId + "");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			ProductCategory pc = new ProductCategory(rs.getInt(1), rs.getString(2), serviceArea);
			productCategories.add(pc);
		}

		con.close();
		return productCategories;
	}
}
