package com.westchase.scripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author marc
 *
 */
public class MigratePhoneBookPropertiesMult {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/westchase", "root", "");
			PreparedStatement ps = conn.prepareStatement("select id, CompanyId from phone_book order by id");
			PreparedStatement ps2 = conn.prepareStatement("select CompanyId, MapNo from company_mapno where companyid = ?");

			StringBuffer res = new StringBuffer();
			
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					long id = rs.getLong("id");
					long compid = rs.getLong("CompanyId");
					if (compid > 0) {
						ps2.setLong(1, compid);
						ResultSet rs2 = ps2.executeQuery();
						List<Integer> mapnos = new ArrayList<Integer>();
						
						while (rs2.next()) {
							int mapno = rs2.getInt("MapNo");
							mapnos.add(new Integer(mapno));
						}
						
						if (mapnos.size() > 1) {
							for (Integer mapno : mapnos) {
								res.append("insert into phone_book_property(phone_book, property) values (" + id + ", " + mapno.intValue() + ");\n");
							}
						} else if (mapnos.size() == 0) {
//							res.append("insert into phone_book_property(phone_book, property) values (" + id + ", -999);\n");
						}
					}
				}
			}
			System.out.println(res.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
