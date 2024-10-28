package DAO;

import BO.CatalogServiceSectorBO;
import Domain.Brand;
import Domain.CatalogServiceArea;
import Domain.CatalogServiceSector;
import Utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CatalogServiceAreaDAO {
	public CatalogServiceArea obtainCatalogServiceAreaById(Integer id) throws ClassNotFoundException, SQLException {
		CatalogServiceArea csa = new CatalogServiceArea();
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from catalog_service_area where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		CatalogServiceSectorBO cssBO = new CatalogServiceSectorBO();
		if (rs.next()) {
			int cssId = rs.getInt(3);
			CatalogServiceSector css = cssBO.obtainCatalogServiceSectorById(cssId);
			csa = new CatalogServiceArea(rs.getInt(1), rs.getString(2), css);
		}
		rs.close();
		connection.close();
		ps.close();

		return csa;

	}

	public CatalogServiceArea obtainCatalogServiceAreaByCategory(String category)
			throws ClassNotFoundException, SQLException {
		CatalogServiceArea csa = new CatalogServiceArea();
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from catalog_service_area where category=?");
		ps.setString(1, category);
		ResultSet rs = ps.executeQuery();

		CatalogServiceSectorBO cssBO = new CatalogServiceSectorBO();
		if (rs.next()) {
			int cssId = rs.getInt(3);
			CatalogServiceSector css = cssBO.obtainCatalogServiceSectorById(cssId);
			csa = new CatalogServiceArea(rs.getInt(1), rs.getString(2), css);
		}
		rs.close();
		connection.close();
		ps.close();

		return csa;
	}

	public List<CatalogServiceArea> listServiceArea(CatalogServiceSector serviceSector)
			throws ClassNotFoundException, SQLException {
		List<CatalogServiceArea> serviceAreas = new ArrayList<CatalogServiceArea>();
		Connection con = DBConnection.getConnection();

		return serviceAreas;
	}

	public List<CatalogServiceArea> obtainCatalogServiceList() throws ClassNotFoundException, SQLException {
		List<CatalogServiceArea> serviceAreas = new ArrayList<CatalogServiceArea>();
		Connection con = DBConnection.getConnection();

		Statement stmt = con.createStatement();
		String sql = "select * from Catalog_Service_area";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt(1);
			String category = rs.getString(2);
			int cssId = rs.getInt(3);
			CatalogServiceSectorBO cssBO = new CatalogServiceSectorBO();
			CatalogServiceSector css = cssBO.obtainCatalogServiceSectorById(cssId);

			CatalogServiceArea catalogServiceArea = new CatalogServiceArea(id, category, css);
			serviceAreas.add(catalogServiceArea);
		}

		rs.close();
		stmt.close();
		con.close();
		return serviceAreas;
	}

}
