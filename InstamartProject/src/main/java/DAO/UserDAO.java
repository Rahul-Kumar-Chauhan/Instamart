package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import BO.AddressBO;
import BO.RoleBO;
import Domain.Role;
import Domain.User;
import Utility.DBConnection;

public class UserDAO {
	public User validateLogin(String username, String password) throws ClassNotFoundException, SQLException {
		User user = null;
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(
				"select * from users where username = '" + username + "' and password = '" + password + "'");
		ResultSet rs = preparedStatement.executeQuery();
		RoleBO roleBO = new RoleBO();
		AddressBO addressBO = new AddressBO();
		if (rs.next()) {
			Role role = roleBO.findRoleById(rs.getInt("role_id"));
			user = new User(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getDate("dob"),
					rs.getInt("age"), rs.getString("contact_no"), rs.getString("email"), rs.getString("username"),
					rs.getString("password"), role);
			user.setAddressList(addressBO.findByUserId(user.getId()));
		}
		connection.close();
		return user;
	}

	public User findById(Integer id) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from users where id=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Role role = new RoleBO().findRoleById(rs.getInt(10));
			User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getInt(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), role);
			con.close();
			return user;
		}

		return null;
	}

	public boolean findUserByUsername(String username) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from users where username=?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			con.close();
			return true;
		}

		return false;
	}

	public Integer createUser(User user) throws SQLException, ClassNotFoundException {
		Connection con = DBConnection.getConnection();
		PreparedStatement ps = null;
		Integer userId = null;
		ps = con.prepareStatement("select max(id) from users");
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Integer a = rs.getInt(1);
			ps = con.prepareStatement(
					"Insert into users(id,first_name,last_name,email,username,password,role_id) values(?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			// insert into users(id,first_name,last_name,email,username,password,role_id)
			// values(13,'David','Ross','david12@gmail.com','david12@gmail.com','david12@gmail.com',1)
			ps.setInt(1, a + 1);
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getUsername());
			ps.setString(6, user.getPassword());
			ps.setLong(7, 2);
			int rowAffected = ps.executeUpdate();
			if (rowAffected == 1) {
				ResultSet rset = ps.getGeneratedKeys();
				if (rset.next())
					userId = 1;
			}
			con.close();
		}
		return userId;
	}

	public void updateContactDetail(User user) throws SQLException, ClassNotFoundException {
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		PreparedStatement ps = null;
		Integer userId = null;
		ps = con.prepareStatement("select max(id) from users");
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			userId = rs.getInt(1);
		}
		Date dob = user.getDob();
		System.out.println(dob);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dobString = sdf.format(new java.sql.Date(user.getDob().getTime()));
		System.out.println(dobString);
		String query = "update users set dob=to_date('" + dobString + "','dd/MM/yyyy'), age=" + user.getAge()
				+ ", contact_no='" + user.getContactNo() + "'where id=" + userId + "";
		stmt.executeUpdate(query);
		con.close();
	}

	public void updateProfile(User user) throws SQLException, ClassNotFoundException {
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dobString = sdf.format(user.getDob());
		stmt.executeUpdate("update users set first_name='" + user.getFirstName() + "', last_name='" + user.getLastName()
				+ "', email='" + user.getEmail() + "', password='" + user.getPassword() + "', dob=to_date('" + dobString
				+ "','dd/MM/yyyy'), age=" + user.getAge() + ", contact_no='" + user.getContactNo() + "' where id="
				+ user.getId() + "");
		con.close();
	}

}