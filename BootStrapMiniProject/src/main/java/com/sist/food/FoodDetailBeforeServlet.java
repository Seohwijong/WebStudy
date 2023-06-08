package com.sist.food;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FoodDetailBeforeServlet")
public class FoodDetailBeforeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode=request.getParameter("mode");
		String fno=request.getParameter("fno");
		// 저장
		Cookie cookie=new Cookie("food_"+fno,fno);
		cookie.setMaxAge(60*60*24);
		cookie.setPath("/");
		response.addCookie(cookie);
		// 본인의 브라우저에 저장
		// 단점 : 보안 취약
		response.sendRedirect("MainServlet?mode="+mode+"&fno="+fno);
	}
}
