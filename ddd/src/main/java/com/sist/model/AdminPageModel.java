package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPageModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		String mode=request.getParameter("mode");
	 	if(mode==null)
	 		mode="1";
	 	String jsp="";
	 	int index=Integer.parseInt(mode);
	 	switch(index)
	 	{
	 	case 1:
	 		jsp="myinfo.do";
	 		break;
	 	case 2:
	 		jsp="myinfo2.do";
	 		break;
	 	case 3:
	 		jsp="application.jsp";
	 		break;
	 	case 4:
	 		jsp="pageContext.jsp";
	 		break;
	 	case 5:
	 		jsp="out.jsp";
	 		break;
	 	case 6:
	 		jsp="etc.jsp";
	 		break;
	 	}
	 	request.setAttribute("jsp", jsp);
		return "amdinpage.jsp";
	}

}
