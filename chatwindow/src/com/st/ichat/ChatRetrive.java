package com.st.ichat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Servlet implementation class ChatRetrive
 */
@WebServlet("/ChatRetrive")
public class ChatRetrive extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	String query1 = "select * from ichatmsgs order by id asc";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String uname = null;
		HttpSession session = request.getSession(true);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// connect to database
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");

			ps = con.prepareStatement(query1);
			// add data to query param

			rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getString(1).equalsIgnoreCase((String) session.getAttribute("uname"))) {
					pw.println("<div id=\"\" class=\"chat self\">");
					pw.println("<div id=\"\" class=\"userPhoto\"><p class=\"p\">" + rs.getString(1) + "</p></div>");
					pw.println("<p class=\"userMessage\">" + rs.getString(2) + "</p>");
					pw.println("</div>");
					//pw.println("<script>document.getElementById('chat-logs')scrollTop = document.getElementById('chatlogs').scrollHeight</script>");
				} else {
					pw.println("<div id=\"\" class=\"chat friend\">");
					pw.println("<div id=\"\" class=\"userPhoto\"><p class=\"p\">" + rs.getString(1) + "</p></div>");
					pw.println("<p class=\"userMessage\">" + rs.getString(2) + "</p>");
					pw.println("</div>");
					//pw.println("<script>document.getElementById('chat-logs')scrollTop = document.getElementById('chatlogs').scrollHeight</script>");
				}
			}

		} catch (

		SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			try {
				if (pw != null)
					pw.close();
			} catch (Exception e) {
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
