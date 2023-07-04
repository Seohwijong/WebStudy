package com.sist.food;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.dao.*;
@WebServlet("/FoodListServlet")
public class FoodListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// 클라이언트로 부터 값 받아오기
		String cno=request.getParameter("cno");
		// DAO연동
		FoodDAO dao=FoodDAO.newInstance();
		List<FoodVO> list=dao.food_category_data(Integer.parseInt(cno));
		CategoryVO cvo=dao.food_category_info(Integer.parseInt(cno));
		
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<style>");
		out.println(".container{margin-top:50px}");
		out.println(".row{");
		out.println("margin:0px auto");
		out.println("width:1024px}</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		out.println("<div class=jumbotron>");
		out.println("<center>");
		out.println("<h3>"+cvo.getTitle()+"</h3>");
		out.println("<h4>"+cvo.getSubject()+"</h4>");
		out.println("</center>");
		out.println("</div>");
		
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td>");
		for(FoodVO vo:list)
		{
			out.println("<table class=table>");
			out.println("<tr>");
			out.println("<td width=30% align=center rowspan=4>");
			out.println("<a href=FoodDetailServlet?fno="+vo.getFno()+"><img src="+vo.getPoster()+" style=\"width:240;height:150px\"></a>");
			out.println("</td>");
			out.println("<td width=70%>");
			out.println("<a href=FoodDetailServlet?fno="+vo.getFno()+">"+vo.getName()+"</a>&nbsp;<span style=\"color:orange\">"+vo.getScore()+"</span>");
			out.println("</td>");
			
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width=70%>");
			out.println(vo.getAddress());
			out.println("</td>");
			out.println("</tr>");
			
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width=70%>");
			out.println(vo.getPhone());
			out.println("</td>");
			out.println("</tr>");
			
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width=70%>");
			out.println(vo.getType());
			out.println("</td>");
			out.println("</tr>");
		}
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

}