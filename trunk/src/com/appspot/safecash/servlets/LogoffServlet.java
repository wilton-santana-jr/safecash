package com.appspot.safecash.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoffServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if (session != null)
			session.invalidate();
		
		res.sendRedirect("/login.jsp");
	}
}