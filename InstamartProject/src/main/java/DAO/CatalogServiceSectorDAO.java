package DAO;

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

import BO.CatalogServiceSectorBO;

public class CatalogServiceSectorDAO {
	public CatalogServiceSector obtainCatalogServiceSectorById(Integer id) throws ClassNotFoundException, SQLException {
		CatalogServiceSector catalogServiceSector = new CatalogServiceSector();
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from catalog_service_sector where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			catalogServiceSector = new CatalogServiceSector(rs.getInt(1), rs.getString(2));
		}
		rs.close();
		connection.close();
		ps.close();

		return catalogServiceSector;
	}

	public CatalogServiceSector obtainCatalogServiceSectorByName(String name)
			throws ClassNotFoundException, SQLException {
		CatalogServiceSector catalogServiceSector = new CatalogServiceSector();
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from catalog_service_sector where name=?");
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			catalogServiceSector = new CatalogServiceSector(rs.getInt(1), rs.getString(2));
		}
		rs.close();
		connection.close();
		ps.close();

		return catalogServiceSector;
	}

	public List<CatalogServiceSector> listServiceSector() throws ClassNotFoundException, SQLException {
		List<CatalogServiceSector> serviceSectors = new ArrayList<CatalogServiceSector>();
		Connection con = DBConnection.getConnection();

		Statement stmt = con.createStatement();
		String sql = "select * from Catalog_Service_Sector";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			CatalogServiceSector catalogServiceSector = new CatalogServiceSector(id, name);
			serviceSectors.add(catalogServiceSector);
		}

		rs.close();
		stmt.close();
		con.close();

		return serviceSectors;
	}
}
