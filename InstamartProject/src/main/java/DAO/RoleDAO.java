package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BO.AddressBO;
import BO.RoleBO;
import Domain.Role;
import Domain.User;
import Utility.DBConnection;

public class RoleDAO {

	public Role findRoleById(Integer roleId) throws ClassNotFoundException, SQLException {
		Integer id = 0;
		String name = null;
		String active = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from role where id = " + roleId + "");
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			active = rs.getString(3);
			id = rs.getInt(1);
			name = rs.getString(2);
			Role role = new Role(id, name, active);
			connection.close();
			return role;
		}
		return null;
	}

	public Role getCustomerRole() throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();

		// fill your code here

		return null;

	}
}
