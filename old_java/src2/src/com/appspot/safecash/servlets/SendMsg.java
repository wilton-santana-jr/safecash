package com.appspot.safecash.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendMsg {
	public static void send(HttpServletRequest req, HttpServletResponse res, String msg, String url) throws ServletException, IOException {
		req.setAttribute("msg", msg);
		System.out.println(url);
		RequestDispatcher disp = req.getRequestDispatcher(url);
		disp.forward(req, res);
	}
}