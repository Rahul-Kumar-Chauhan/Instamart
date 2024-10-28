package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BO.UserBO;
import Domain.Address;
import Domain.User;
import Utility.DBConnection;

public class AddressDAO {

	public Address findById(Integer id) throws ClassNotFoundException, SQLException {
		Address address = null;
		int user_id = 0;
		UserDAO userDAO = new UserDAO();
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT * from address where id=" + id + "");
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			user_id = rs.getInt(2);
			User user = userDAO.findById(user_id);
			address = new Address(id, user, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
					rs.getInt(7));
		}
		rs.close();
		ps.close();
		con.close();
		return address;
	}

	public ArrayList<Address> findByUserId(Integer userId) throws ClassNotFoundException, SQLException {
		ArrayList<Address> addressList = new ArrayList<>();
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findById(userId);
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("Select * from address where user_id=" + userId + "");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Address address = new Address(rs.getInt(1), user, rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7));
			addressList.add(address);
		}
		return addressList;
	}

	public ArrayList<Address> findByUserId(Integer userId, String city) throws ClassNotFoundException, SQLException {
		ArrayList<Address> addressList = new ArrayList<>();
		Connection con = DBConnection.getConnection();

		// fill your coe here

		return addressList;
	}

	public Address create(Address address, Integer userId) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con
				.prepareStatement("insert into address(user_id,street,city,state,country,pincode) values(?,?,?,?,?,?)");

		return address;

	}

}
