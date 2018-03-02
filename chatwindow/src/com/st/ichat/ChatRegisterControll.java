package com.st.ichat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/chat-register")
public class ChatRegisterControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ChatDTO dto=new ChatDTO();
	ChatService service=new ChatService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=null;
		//general setting'
		pw=response.getWriter();
		response.setContentType("text/html");
		
		dto.setFirst_name(request.getParameter("first-name"));
		dto.setLast_name(request.getParameter("last-name"));
		dto.setEmail(request.getParameter("email"));
		dto.setPassword(request.getParameter("password"));
		
		if(service.register(dto)) {
			pw.print(true);
			//System.out.println(true);
		}
		else {
			pw.print(false);
			System.out.println("regd fail");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
