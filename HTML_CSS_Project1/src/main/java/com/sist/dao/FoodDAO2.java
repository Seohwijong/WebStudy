package com.sist.dao;
import java.util.*;
import java.sql.*;
public class FoodDAO2 {
	private Connection conn;
	// 송수신
	private PreparedStatement ps;
	// 오라클 URL주소 설정
	private final String URL="jdbc:oracle:thin:@localhost:1522:xe";
	// 싱글턴
	private static FoodDAO2 dao;
	// 1. 드라이버 등록
	public FoodDAO2()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// classNotFoundException => 체크 예외처리 => 반드시 예외처리한다
			// java.io,java.net,java.sql => 체크 예외처리
		}
		catch(Exception ex) {}
	}
	// 2. 오라클 연결
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
			// conn hr/happy => 명령어를 오라클 전송
		}
		catch(Exception ex) {}
	}
	// 3. 오라클 해제
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close(); // 통신이 열려있다면 닫아라
			if(conn==null) conn.close();
			/// exit => 오라클 닫기
		}
		catch(Exception ex) {}
	}
	
	public static FoodDAO2 newInstance() 
	{
		if(dao==null)
			dao=new FoodDAO2();
		return dao;
	}
	public List<FoodVO> foodALLData()
	{
		List<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			getConnection();
			String sql="SELECT name,address,poster,phone,type FROM food_house ORDER BY fno ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo=new FoodVO();
				vo.setName(rs.getString(1));
				String addr=rs.getString(2);
				addr=addr.substring(0,addr.lastIndexOf("지번"));
				vo.setAddress(addr);
				String poster=rs.getString(3);
				poster=poster.substring(0,poster.indexOf("^"));
				poster=poster.replace("#", "&");
				vo.setPoster(poster);
				vo.setTel(rs.getString(4));
				vo.setType(rs.getString(5));
				list.add(vo);
					
			}
			rs.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
}
