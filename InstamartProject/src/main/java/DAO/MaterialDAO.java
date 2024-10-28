package DAO;

import Domain.Brand;
import Domain.CatalogServiceArea;
import Domain.CatalogServiceSector;
import Domain.Material;
import Utility.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import BO.CatalogServiceSectorBO;

public class MaterialDAO {
	public Material obtainMaterialById(Integer id) throws ClassNotFoundException, SQLException {
		Material material = null;
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from material where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			material = new Material(rs.getInt(1), rs.getString(2));
		}
		rs.close();
		connection.close();
		ps.close();

		connection.close();
		return material;
	}

	public Material obtainMaterialByName(String name) throws ClassNotFoundException, SQLException {
		Material material = null;
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from material where name=?");
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			material = new Material(rs.getInt(1), rs.getString(2));
		}
		rs.close();
		connection.close();
		ps.close();

		connection.close();
		return material;
	}

	public List<Material> listMaterials() throws ClassNotFoundException, SQLException {
		List<Material> materials = new ArrayList<Material>();
		Connection con = DBConnection.getConnection();

		Statement stmt = con.createStatement();
		String sql = "select * from material";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			Material material = new Material(id, name);
			materials.add(material);
		}

		rs.close();
		stmt.close();
		con.close();

		return materials;
	}
}
