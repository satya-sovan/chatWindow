package com.st.ichat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class ChatInsert
 */
@WebServlet("/ChatInsert")
public class ChatInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	ResultSet rs = null,rs1;
	PreparedStatement ps = null;
	String query = "insert into ichatmsgs (name,msgs)values(?,?)";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connect to database
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

			ps = con.prepareStatement(query);
			// add data to query param

			String uname = null;
			String umsg = null;

			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connect to database
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
			
			ps=con.prepareStatement("SELECT FNAME FROM ICHAT WHERE EMAIL=?");
			ps.setString(1, request.getParameter("email"));
			System.out.println("chat insert"+request.getParameter("email"));
			rs1=ps.executeQuery();
			rs1.next();
			uname=rs1.getString(1);
			umsg=request.getParameter("msgs");
			ps=null;
			
				ps = con.prepareStatement(query);
			
			// add data to query param
			ps.setString(1, uname);
			ps.setString(2, umsg);
			int cnt=0;
			if (ps != null) {
				cnt = ps.executeUpdate();
			}
			if (cnt != 0)
				pw.println();
			else {
				pw.print("alert('Message sending failed..!!')");
			}

		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}finally {
			try {
			if(ps!=null)
				ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if (pw!=null)
					pw.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
				
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
