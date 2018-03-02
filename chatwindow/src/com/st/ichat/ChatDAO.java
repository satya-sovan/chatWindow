package com.st.ichat;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class ChatDAO {
	Connection con;
	
	ResultSet rs=null;
	PreparedStatement ps=null;
	String query="insert into ichatmsgs (name,msgs)values(?,?)";
	
	int cnt=0;

	ChatDAO() {
		InitialContext ic = null;
		DataSource ds = null;
		try {
			// local jndl regidter
			ic = new InitialContext();
			// Get datasource object from jndl regitry
			ds = (DataSource) ic.lookup("java:/comp/env/mypool");
			// get jdbc con object from jdbc con pool
			con = ds.getConnection();
			if (con == null) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean registerCheck(ChatBO bo) {
		try {
			PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM ICHAT WHERE EMAIL=?");
			ps.setString(1, bo.getEmail());
			ResultSet rs = rs = ps.executeQuery();
			rs.next();
			if (rs.getInt(1) != 0)
				return false;
			else
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean registerInsert(ChatBO bo) {
		try {
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO ICHAT(FNAME,LNAME,EMAIL,PASSWORD) VALUES(?,?,?,?)");
			ps.setString(1, bo.getFirst_name());
			ps.setString(2, bo.getLast_name());
			System.out.println(bo.getLast_name());
			ps.setString(3, bo.getEmail());
			ps.setString(4, bo.getPassword());

			int res = ps.executeUpdate();
			if (res != 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean loginCheck(ChatBO bo) {
		PreparedStatement ps = null;
		ResultSet rs = null;

		// connect to database
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			ps = con.prepareStatement("select fname from ichat where email=? and password=?");
			// set parameters to query
			ps.setString(1, bo.getEmail());
			ps.setString(2, bo.getPassword());
			System.out.println(bo.getEmail() + "  " + bo.getPassword());
			if (ps != null)
				rs = ps.executeQuery();
		
			if (rs.next()) {
				System.out.println("uname from db in loginCheck() at ChatDAO :: " + rs.getString(1));
				bo.setFirst_name(rs.getString(1));
				return true;
			} else {
				return false;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException cfs) {
			cfs.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		return false;

	}

	public boolean chatInsert(String name, String msg) {

		String uname = name;
		String umsg = msg;

		try {
			
			if (con != null)
				ps = con.prepareStatement(query);
			// add data to query param
			ps.setString(1, uname);
			ps.setString(2, umsg);

			if (ps != null) {
				cnt = ps.executeUpdate();
			}
			if (cnt != 0)
				return true;
			else {
				return false;
			}

		}catch (SQLException se) {
			se.printStackTrace();
		}
		return false;
	}

	
}
