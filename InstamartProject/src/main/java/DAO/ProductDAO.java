package DAO;

import BO.*;

import Domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hslf.record.PPDrawing;

import BO.BrandBO;
import BO.CatalogServiceAreaBO;
import BO.CatalogServiceSectorBO;
import BO.MaterialBO;
import BO.ProductCategoryBO;
import BO.ProductTypeBO;
import Domain.Brand;
import Domain.CatalogServiceArea;
import Domain.CatalogServiceSector;
import Domain.Material;
import Domain.Product;
import Domain.ProductCategory;
import Domain.ProductType;
import Utility.DBConnection;

public class ProductDAO {
	public Product findById(Integer id) throws ClassNotFoundException, SQLException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement ps = connection.prepareStatement("select * from product where id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Brand brand = new BrandBO().obtainBrandById(rs.getInt("brand_id"));
			ProductType productType = new ProductTypeBO().obtainProductTypeById(rs.getInt("product_type_id"));
			ProductCategory productCategory = new ProductCategoryBO()
					.obtainProductCategoryById(rs.getInt("product_category_id"));
			CatalogServiceSector css = new CatalogServiceSectorBO()
					.obtainCatalogServiceSectorById(rs.getInt("catalog_service_sector_id"));
			CatalogServiceArea csa = new CatalogServiceAreaBO()
					.obtainCatalogServiceAreaById(rs.getInt("catalog_service_area_id"));
			Material material = new MaterialBO().obtainMaterialById(rs.getInt("material_id"));
			Product p = new Product(rs.getInt("id"), rs.getString("name"), brand, productType, productCategory, css,
					csa, rs.getString("color"), material, rs.getString("dimensions"), rs.getString("is_visible"),
					rs.getString("is_available"), rs.getString("city"), rs.getString("image_url"));
			return p;
		}

		connection.close();
		return null;
	}

	public List<Product> obtainAvailableProduct(String city) throws ClassNotFoundException, SQLException {
		List<Product> productList = new ArrayList<Product>();
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from product where city=?");
		ps.setString(1, city);
		ResultSet rs = ps.executeQuery();

		ProductBO pBO = new ProductBO();
		while (rs.next()) {
			int id = rs.getInt(1);
			Product products = pBO.findById(id);
			productList.add(products);
		}

		connection.close();
		return productList;
	}

	public List<Product> obtainAllProducts(boolean visibilty, boolean availability)
			throws ClassNotFoundException, SQLException {
		List<Product> productList = new ArrayList<Product>();
		Connection connection = DBConnection.getConnection();

		char is_visible = '0';
		char is_available = '0';
		if (visibilty = true) {
			is_visible = '1';
		} else {
			is_visible = '0';
		}
		if (visibilty = true) {
			is_available = '1';
		} else {
			is_available = '0';
		}

		PreparedStatement ps = connection.prepareStatement(
				"select * from product where is_visible=" + is_visible + " and is_available=" + is_available + "");

		ResultSet rs = ps.executeQuery();

		ProductBO pBO = new ProductBO();
		while (rs.next()) {
			int id = rs.getInt("id");
			Product products = pBO.findById(id);
			productList.add(products);
		}

		connection.close();
		return productList;
	}

	public List<Product> obtainAllProductsForManager() throws ClassNotFoundException, SQLException {
		List<Product> productList = new ArrayList<Product>();
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from product");
		ResultSet rs = ps.executeQuery();

		ProductBO pBO = new ProductBO();
		while (rs.next()) {
			int id = rs.getInt("id");
			Product products = pBO.findById(id);
			productList.add(products);
		}

		connection.close();
		return productList;
	}

	public List<Product> getProductsByOrder(Integer orderDetailId) throws ClassNotFoundException, SQLException {
		List<Product> productList = new ArrayList<Product>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"select * from product inner join order_detail_product on product.id=order_detail_product.product_id where order_detail_product.order_detail_id="
						+ orderDetailId + ";");
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Brand brand = new BrandBO().obtainBrandById(rs.getInt("brand_id"));
			ProductType productType = new ProductTypeBO().obtainProductTypeById(rs.getInt("product_type_id"));
			ProductCategory productCategory = new ProductCategoryBO()
					.obtainProductCategoryById(rs.getInt("product_category_id"));
			CatalogServiceSector css = new CatalogServiceSectorBO()
					.obtainCatalogServiceSectorById(rs.getInt("catalog_service_sector_id"));
			CatalogServiceArea csa = new CatalogServiceAreaBO()
					.obtainCatalogServiceAreaById(rs.getInt("catalog_service_area_id"));
			Material material = new MaterialBO().obtainMaterialById(rs.getInt("material_id"));
			Product p = new Product(rs.getInt("id"), rs.getString("name"), brand, productType, productCategory, css,
					csa, rs.getString("color"), material, rs.getString("dimensions"), rs.getString("is_visible"),
					rs.getString("is_available"), rs.getString("city"), rs.getString("image_url"));
			ProductRate productRate = new ProductRateBO().obtainProductRateByProduct(p);
			productList.add(p);
		}
		connection.close();
		return productList;
	}

	public List<Product> getProductsByPackage(Integer packageId) throws ClassNotFoundException, SQLException {
		List<Product> productList = new ArrayList<Product>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"select * from product inner join rental_package_product as pp on product.id=pp.product_id where pp.package_id="
						+ packageId + ";");
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Brand brand = new BrandBO().obtainBrandById(rs.getInt("brand_id"));
			ProductType productType = new ProductTypeBO().obtainProductTypeById(rs.getInt("product_type_id"));
			ProductCategory productCategory = new ProductCategoryBO()
					.obtainProductCategoryById(rs.getInt("product_category_id"));
			CatalogServiceSector css = new CatalogServiceSectorBO()
					.obtainCatalogServiceSectorById(rs.getInt("catalog_service_sector_id"));
			CatalogServiceArea csa = new CatalogServiceAreaBO()
					.obtainCatalogServiceAreaById(rs.getInt("catalog_service_area_id"));
			Material material = new MaterialBO().obtainMaterialById(rs.getInt("material_id"));
			Product p = new Product(rs.getInt("id"), rs.getString("name"), brand, productType, productCategory, css,
					csa, rs.getString("color"), material, rs.getString("dimensions"), rs.getString("is_visible"),
					rs.getString("is_available"), rs.getString("city"), rs.getString("image_url"));
			ProductRate productRate = new ProductRateBO().obtainProductRateByProduct(p);
			productList.add(p);
		}
		connection.close();
		return productList;
	}

	public Product addProduct(Product p) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select max(id) from users");
		ResultSet rs = ps.executeQuery();
		Integer productId = null;
		if (rs.next()) {
			Integer a = rs.getInt(1);
			ps = con.prepareStatement(
					"Insert into product(id,color,name,dimensions,is_available,is_visible,name,brand_id,catalog_sercice_area_id,catalog_service_sector_id,company_id,material_id,poroduct_category_id,product_type_id,city,image_url) values(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, a + 1);
			ps.setString(2, p.getColor());
			ps.setString(3, p.getDimensions());
			ps.setString(4, p.getIsAvailable());
			ps.setString(5, p.getIsVisible());
			ps.setString(6, p.getName());
			ps.setInt(7, p.getBrand().getId());
			ps.setInt(8, p.getCatalogServiceArea().getId());
			ps.setInt(9, p.getCatalogServiceSector().getId());
			ps.setInt(10, p.getMaterial().getId());
			ps.setInt(11, p.getProductCategory().getId());
			ps.setInt(12, p.getProductType().getId());
			ps.setString(13, p.getCity());
			ps.setString(14, p.getImageUrl());
			int rowAffected = ps.executeUpdate();
			if (rowAffected == 1) {
				ResultSet rset = ps.getGeneratedKeys();
				if (rset.next())
					productId = 1;
			}

			con.close();
		}
		return p;
	}
}
