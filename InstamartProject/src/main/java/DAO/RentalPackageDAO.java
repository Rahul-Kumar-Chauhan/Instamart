
package DAO;

import BO.ProductBO;
import BO.ProductCategoryBO;
import BO.RentalPackageBO;
import Domain.Product;
import Domain.ProductCategory;
import Domain.ProductType;
import Domain.RentalPackage;
import Domain.User;
import Utility.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalPackageDAO {

	public RentalPackage findById(Integer id) throws SQLException, ClassNotFoundException {
		RentalPackage rentalPackage = null;
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from rental_package where id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			rentalPackage = new RentalPackage(rs.getInt("id"), rs.getString("name"), rs.getString("is_visible"),
					rs.getString("is_available"), rs.getString("city"), rs.getString("image_url"),
					rs.getDate("created_date"));
		}

		connection.close();
		return rentalPackage;
	}

	public List<RentalPackage> getAvailableRentalPackages(String city) throws SQLException, ClassNotFoundException {
		List<RentalPackage> packageList = new ArrayList<RentalPackage>();
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from rental_package where city=?");
		ps.setString(1, city);
		ResultSet rs = ps.executeQuery();

		RentalPackageBO rpBO = new RentalPackageBO();
		while (rs.next()) {
			int id = rs.getInt(1);
			RentalPackage rentalPackage = rpBO.findById(id);
			packageList.add(rentalPackage);
		}

		connection.close();
		return packageList;
	}

	public List<RentalPackage> obtainAllPackages(boolean visibility, boolean availability)
			throws SQLException, ClassNotFoundException {
		List<RentalPackage> packageList = new ArrayList<RentalPackage>();
		Connection connection = DBConnection.getConnection();

		char is_visible = '0';
		char is_available = '0';
		if (visibility = true) {
			is_visible = '1';
		} else {
			is_visible = '0';
		}
		if (visibility = true) {
			is_available = '1';
		} else {
			is_available = '0';
		}

		PreparedStatement ps = connection.prepareStatement("select * from rental_package where is_visible=" + is_visible
				+ " and is_available=" + is_available + "");

		ResultSet rs = ps.executeQuery();

		RentalPackageBO rpBO = new RentalPackageBO();
		while (rs.next()) {
			int id = rs.getInt(1);
			RentalPackage rentalPackage = rpBO.findById(id);
			packageList.add(rentalPackage);
		}

		connection.close();
		return packageList;
	}

	public List<RentalPackage> obtainAllPackagesForManager() throws SQLException, ClassNotFoundException {
		List<RentalPackage> packageList = new ArrayList<RentalPackage>();
		Connection connection = DBConnection.getConnection();

		PreparedStatement ps = connection.prepareStatement("select * from product");
		ResultSet rs = ps.executeQuery();

		RentalPackageBO rpBO = new RentalPackageBO();
		while (rs.next()) {
			int id = rs.getInt(1);
			RentalPackage rentalPackage = rpBO.findById(id);
			packageList.add(rentalPackage);
		}

		connection.close();
		return packageList;
	}

	public List<RentalPackage> getRentalPackagesByOrder(Integer orderDetailId)
			throws SQLException, ClassNotFoundException {
		List<RentalPackage> packageList = new ArrayList<RentalPackage>();
		Connection connection = DBConnection.getConnection();

		connection.close();
		return packageList;
	}

	public void updateVisibility(RentalPackage pack) throws SQLException, ClassNotFoundException {

		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		stmt.executeUpdate(
				"update rental_package set is_visible=" + pack.getIsVisible() + " where id = " + pack.getId() + "");
		con.close();
	}

}
