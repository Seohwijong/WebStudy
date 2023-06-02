package com.sist.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * request : 사용자 요청값을 받는 경우 => getParameter()
 * response: 전송 (브라우저) =? html,cookie
 * 			 => 한파일에서 1번만 수행이 가능
 *           => 서버에서 화면을 변경 (sendRedirect())
 * cookie
 * session
 * ----------- JSP / Spring / Spring-boot
 * 
 * 
 * WEB
 *     java => jsp/servlet (Spring)
 *     c#	=> aspx / asp
 *     python => django
 *     php
 *     nodejs
 *     -------------------------------------------- request,response,cookie,session
 */



@WebServlet("/SeoulDetailServlet")
public class SeoulDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
