package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		String jsp=request.getParameter("jsp");
	 	
		if(jsp==null)
	 		jsp="home.jsp";
//		String page="";
//	 	if(jsp.equals("mypage.do"))
//	 	{
//	 		jsp="mypage.do";
//	 	}
//	 	else if(jsp.equals("amdinpage.do"))
//	 	{
//	 		jsp="adminpage.do";
//	 	}
	 	request.setAttribute("jsp", jsp);
		return "main.jsp";
	}

}
