package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sist.dao.*;

public class MemberDAO {
	private Connection conn; // 오라클 연결 객체 (데이터베이스 연결)
	private PreparedStatement ps; // SQL문장 전송 / 결과값 읽기
	private final String URL="jdbc:oracle:thin:@localhost:1522:XE";
	// mySQL => jdbc:mysql://localhost/mydb
	private static MemberDAO dao; // 싱글턴 패턴
	// DAO객체를 한개만 사용이 가능하게 만든다
	// 드라이버 설치 => 소프트웨어 (메모리 할당 요청) Class.forName()
	// 클래스의 정보를 전송
	// 드라이버 설치는 1번만 수행
	public MemberDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception ex) {}
	}
	// 오라클 연결
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
			// => 오라클 전송 : conn
		}
		catch(Exception ex) {}
	}
	// 오라클 연결 종료
	public void disConnection()
	{
		try
		{
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
			// => 오라클 전송 : exit
		}
		catch(Exception ex) {}
	}
	// DAO객체를 1개만 생성해서 사용 => 메모리 누수현상 방지 (싱글턴 패턴)
	// 싱글턴 / 팩토리 => 면접 (스프링 : 패턴 8개)
	public static MemberDAO newInstance()
	{
		// newInstance() , getInstance() => 싱글턴
		if(dao==null)
			dao=new MemberDAO();
		return dao;
	}
//	public String isLogin(String id, String pwd)
//	{
//		String result="";
//		try
//		{
//			getConnection();
//			String sql="SELECT COUNT(*) FROM jspMember "
//					+ "WHERE id=?";
//			ps=conn.prepareStatement(sql);
//			ps.setString(1, id);
//			ResultSet rs=ps.executeQuery();
//			rs.next();
//			int count=rs.getInt(1);
//			rs.close();
//			if(count==0)
//			{
//				vo.setMsg(sal);
//			}
//			else
//			{
//				
//			}
//		}
//		catch (Exception ex) 
//		{
//			ex.printStackTrace();
//		}
//		finally
//		{
//			disConnection();
//		}
//		return result;
//	}
	public MemberVO isLogin(String id, String pwd)
	{
		MemberVO vo=new MemberVO();
		try
		{
			getConnection();
			String sql="SELECT COUNT(*) FROM jspMember "
					+ "WHERE id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			int count=rs.getInt(1);
			rs.close();
			if(count==0)
			{
				vo.setMsg("NOID");
			}
			else
			{
				sql="SELECT id,name,sex,pwd "
						+ "FROM jspMember "
						+ "WHERE id=?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, id);
				rs=ps.executeQuery();
				rs.next();
				String db_id=rs.getString(1);
				String name=rs.getString(2);
				String sex=rs.getString(3);
				String db_pwd=rs.getString(4);
				rs.close();
				
				if(db_pwd.equals(pwd)) // 로그인
				{
					vo.setId(db_id);
					vo.setName(name);;
					vo.setSex(sex);
					vo.setMsg("OK");
				}
				else // 비번틀림
				{
					vo.setMsg("NOPWD");
				}
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return vo;
	}
}
