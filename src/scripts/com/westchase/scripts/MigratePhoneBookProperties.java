package com.westchase.scripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author marc
 *
 */
public class MigratePhoneBookProperties {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/westchase", "root", "");
			PreparedStatement ps = conn.prepareStatement("select id, CompanyId from phone_book order by id");
			PreparedStatement ps2 = conn.prepareStatement("select CompanyId, MapNo from company_mapno where companyid = ?");
			PreparedStatement ps3 = conn.prepareStatement("insert into phone_book_property (phone_book, property) values (?, ?)");

			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					long id = rs.getLong("id");
					long compid = rs.getLong("CompanyId");
					if (compid > 0) {
						ps2.setLong(1, compid);
						ResultSet rs2 = ps2.executeQuery();
						if (rs2.next()) {
							boolean hasMoreThanOne = false;
							int prop = rs2.getInt("MapNo");
							if (rs2.next()) {
								hasMoreThanOne = true;
							}
							// only do this update if there is only one property for the company
							// that way I know for sure that this is the right property
							// the other ones will have to be done manually (for now)
							if (prop > 0 && !hasMoreThanOne) {
								ps3.setLong(1, id);
								ps3.setInt(2, prop);
								ps3.executeUpdate();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
