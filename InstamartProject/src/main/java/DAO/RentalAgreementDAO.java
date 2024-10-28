package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import Domain.RentalAgreement;
import Utility.DBConnection;

public class RentalAgreementDAO {
	public RentalAgreement save(RentalAgreement rentalAgreement) throws SQLException, ClassNotFoundException {
		Connection con = DBConnection.getConnection();


		return rentalAgreement;

	}

}
