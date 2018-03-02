package com.st.ichat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/LoginControll")
public class LoginControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ChatDTO dto=new ChatDTO();
	ChatService service=new ChatService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=null;
		//general setting'
		pw=response.getWriter();
		response.setContentType("text/html");
				
		dto.setEmail(request.getParameter("email"));
		dto.setPassword(request.getParameter("password"));
		
		
		if(service.loginCheck(dto)) {
			//System.out.println("login success");
			HttpSession session=request.getSession(true);  
	        session.setAttribute("uname",dto.getFirst_name()); 
	        session.setAttribute("email", dto.getEmail());
	        session.setAttribute("password",dto.getPassword()); 
			response.sendRedirect(request.getContextPath() + "/chatbox.jsp");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/index.html");
			System.out.println("service.logincheck() false");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
