package com.sist.dao;

import java.util.*;

import javax.xml.crypto.Data;

import java.nio.file.SecureDirectoryStream;
import java.sql.*;
import com.sist.vo.*;
public class DataBoardDAO {
	private Connection conn; // 오라클 연결 객체 (데이터베이스 연결)
	private PreparedStatement ps; // SQL문장 전송 / 결과값 읽기
	private final String URL="jdbc:oracle:thin:@localhost:1522:XE";
	// mySQL => jdbc:mysql://localhost/mydb
	private static DataBoardDAO dao; // 싱글턴 패턴
	// DAO객체를 한개만 사용이 가능하게 만든다
	// 드라이버 설치 => 소프트웨어 (메모리 할당 요청) Class.forName()
	// 클래스의 정보를 전송
	// 드라이버 설치는 1번만 수행
	public DataBoardDAO()
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
	public static DataBoardDAO newInstance()
	{
		// newInstance() , getInstance() => 싱글턴
		if(dao==null)
			dao=new DataBoardDAO();
		return dao;
	}
	// 기능
	// 1. 목록 => 페이징(인라인뷰)
	// 2. => 블록별 => <1 2 3 4 5>
	public List<DataBoardVO>  databoardListData(int page)
	{
		List<DataBoardVO> list= new ArrayList<DataBoardVO>();
		try
		{
			getConnection();
			String sql="SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD'),hit,num "
					+ "FROM (SELECT no,subject,name,regdate,hit,rownum as num "
					+ "FROM (SELECT /*+ INDEX_DESC(jspDataBoard jd_no_pk)*/ no,subject,name,regdate,hit "
					+ "FROM jspDataBoard)) "
					+ "WHERE num BETWEEN ? AND ?";
			// TOP-N (중간에 잘라올 수 없다)
			ps=conn.prepareStatement(sql); // sql문장 전속
			// ?에 값을 채운다
			int rowSize=10;
			int start=(rowSize*page)-(rowSize-1);
			int end=rowSize*page;
			/*
			 *  rownum => 1번
			 *  1page => 1~10
			 *  2page => 11~20
			 *  3page => 21~30 
			 */
			ps.setInt(1, start);
			ps.setInt(2, end);
			// 실행
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				DataBoardVO vo=new DataBoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		return list;
	}
	// 총페이지 
	public int databoardRowCount()
	{
		int count=0;
		try
		{
			getConnection();
			String sql="SELECT COUNT(*) FROM jspDataBoard";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			count=rs.getInt(1);
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
		return count;
	}
	// 데이터 추가 => Insert
	public void databoardInsert(DataBoardVO vo)
	{
		try
		{
			getConnection();
			String sql="INSERT INTO jspDataBoard VALUES("
					+ "jd_no_seq.nextval,?,?,?,?,SYSDATE,0,?,?)";
			ps=conn.prepareStatement(sql);
			// ? 에 값 채우기
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setString(5, vo.getFilename());
			ps.setInt(6, vo.getFilesize());
			ps.executeQuery();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
	}
	// 내용보기
	public DataBoardVO databoardDetailData(int no)
	{
		DataBoardVO vo=new DataBoardVO();
		try
		{
			getConnection();
			String sql="UPDATE jspDataBoard SET "
					+ "hit=hit+1 "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			sql="SELECT no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD'),hit,filename,filesize "
					+ "FROM jspDataBoard "
					+ "WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs=ps.executeQuery();
			rs.next();
			
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setDbday(rs.getString(5));
			vo.setHit(rs.getInt(6));
			vo.setFilename(rs.getString(7));
			vo.setFilesize(rs.getInt(8));
			
			
		}
		catch(Exception ex)
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
