package com.sist.dao;

import java.sql.*;
import java.util.*;

import oracle.jdbc.OracleTypes;

public class StudentDAO {
	// 연결
	private Connection conn;
	// 함수 (프로시저) 호출
	private CallableStatement cs;
	// URL
	private final String URL="jdbc:oracle:thin:@localhost:1522:xe";
	// Singleton
	private static StudentDAO dao;
	// Driver 등록
	public StudentDAO()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(Exception ex) {}
	}
	// 싱글턴 사용
	public static StudentDAO newInstance()
	{
		if(dao==null)
			dao=new StudentDAO();
		return dao;
	}
	// 데이터 추가
	public void studentInsert(StudentVO vo)
	{
		
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
			// 함수 호출만 하면 된다
			String sql="{CALL studentInsert(?,?,?,?)}";
			cs=conn.prepareCall(sql);
			// ?에 값을 채운다음 실행
			cs.setString(1, vo.getName());
			cs.setInt(2, vo.getKor());
			cs.setInt(3, vo.getEng());
			cs.setInt(4, vo.getMath());
			// 실행 요청
			cs.executeQuery(); // cs.executeQuery()로 실행
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(cs!=null) cs.close();
				if(conn!=null) conn.close();
			}
			catch(Exception ex) {}
		}
		
	}
	// 데이터 수정
	public void StudentUpdate(StudentVO vo)
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
			String sql="{CALL studentUpdate(?,?,?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, vo.getHakbun());
			cs.setString(2, vo.getName());
			cs.setInt(3, vo.getKor());
			cs.setInt(4, vo.getEng());
			cs.setInt(5, vo.getMath());
			
			cs.executeQuery(); 
			// 모든 테이블의 데이터를 페이징
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(cs!=null) cs.close();
				if(conn!=null) conn.close();
			}
			catch(Exception ex) {}
		}
		
	}
	// 데이터 삭제
	public void StudentDelete(int hakbun)
	{
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
			String sql="{CALL studentDelete(?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, hakbun);
			cs.executeQuery();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(cs!=null) cs.close();
				if(conn!=null) conn.close();
			}
			catch(Exception ex) {}
		}
		
	}
	// 데이터 상세
	public StudentVO studentDetaildata(int hakbun)
	{
		StudentVO vo=new StudentVO();
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
			String sql="{CALL studentDetaildate(?,?,?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, hakbun);
			// OUT 변수일 경우에 => 메모리에 저장한다
			cs.registerOutParameter(2, OracleTypes.VARCHAR);
			cs.registerOutParameter(3, OracleTypes.INTEGER);
			cs.registerOutParameter(4, OracleTypes.INTEGER);
			cs.registerOutParameter(5, OracleTypes.INTEGER);
			cs.executeQuery();
			vo.setName(cs.getString(2));
			vo.setKor(cs.getInt(3));
			vo.setEng(cs.getInt(4));
			vo.setMath(cs.getInt(5));
			vo.setHakbun(hakbun);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(cs!=null) cs.close();
				if(conn!=null) conn.close();
			}
			catch(Exception ex) {}
		}
		return vo;
	}
	// 데이터 전체
	public List<StudentVO> studentListData()
	{
		List<StudentVO> list=new ArrayList<StudentVO>();
		try
		{
			conn=DriverManager.getConnection(URL,"hr","happy");
			String sql="{CALL studentListData(?)}";
			cs=conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			/*
			 * NUMBER => INTEGER/DOUBLE
			 * VARCHAR2,CHAR => VARCHAR
			 * CURSOR => CURSOR
			 */
			cs.executeQuery();
			ResultSet rs=(ResultSet)cs.getObject(1);
			// CURSOR => ResultSet으로 변경됨
			while(rs.next())
			{
				StudentVO vo=new StudentVO();
				vo.setHakbun(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setKor(rs.getInt(3));
				vo.setEng(rs.getInt(4));
				vo.setMath(rs.getInt(5));
				vo.setTotal(rs.getInt(6));
				vo.setAvg(rs.getDouble(7));
				list.add(vo);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try
			{
				if(cs!=null) cs.close();
				if(conn!=null) conn.close();
			}
			catch(Exception ex) {}
		}
		return list;
	}
}
