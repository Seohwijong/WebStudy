package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NavModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		String page=request.getParameter("page");
		String jsp="";
		if(page==null)
				page="home.jsp";
		if(page.equals("mypage.do"))
		{
			jsp="mypage.do";
		}
		else if(page.equals("adminpage.do"))
		{
			jsp="adminpage.do";
		}
		request.setAttribute("jsp", jsp);
		request.setAttribute("page", page);
		return "nav.jsp";
	}

}
