package com.sist.model;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.MemberVO;
public class MyPageModel {
	@RequestMapping("my/mypage.do")
	public String my_page(HttpServletRequest request, HttpServletResponse response) {
		String mode=request.getParameter("mode");
		String update=request.getParameter("update");
		if(update==null)
			update="0";
	 	if(mode==null)
	 		mode="1";
	 	String jsp="";
	 	int index=Integer.parseInt(mode);
	 	switch(index)
	 	{
	 	case 1:
	 		jsp="myinfo.jsp";
	 		break;
	 	case 2:
	 		jsp="myinfo2.jsp";
	 		break;
	 	}
	 	if(Integer.parseInt(update)==1)
 			jsp="myinfo2.jsp";
	 	try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		MyDAO dao=MyDAO.newInstance();
		
		MemberVO vo=dao.myInfo(id);
	 	System.out.println(jsp);
	 	
	 	request.setAttribute("jsp", jsp);
	 	request.setAttribute("vo", vo);
		request.setAttribute("main_jsp", "../my/mypage.jsp");
		 return "../jsp/main.jsp";
	}
	@RequestMapping("my/myinfo.do")
	public String my_page_info(HttpServletRequest request, HttpServletResponse response) {
		try
		{
			request.setCharacterEncoding("UTF-8");
		}catch(Exception ex) {}
		String mode=request.getParameter("mode");
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		String jsp="myinfo2.jsp";
		
		try
		{
			PrintWriter out=response.getWriter();
			out.println(jsp); //=> Ajax에서 읽어서 처리
		}catch(Exception ex) {}

		MyDAO dao=MyDAO.newInstance();
		MemberVO vo=dao.myInfo(id);
		request.setAttribute("vo", vo);
		request.setAttribute("jsp", jsp);
		request.setAttribute("main_jsp", "../my/mypage.jsp");
		 return "../my/mypage.jsp";
	}
}
