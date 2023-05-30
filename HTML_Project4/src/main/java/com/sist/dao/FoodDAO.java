package com.sist.dao;
// => 카테고리 => 카테고리별 맛집 => 맛집에 대한 상세보기 => 지도출력 , 검색
import java.util.*;
import java.sql.*;
public class FoodDAO {
	// 연결 객체
	private Connection conn;
	// 송수신
	private PreparedStatement ps;
	// 오라클 URL주소 설정
	private final String URL="jdbc:oracle:thin:@localhost:1522:xe";
	// 싱글턴
	private static FoodDAO dao;
	// 1. 드라이버 등록
	public FoodDAO()
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
	// 4. 싱글턴 설정 => static은 메모리 공간을 1개만 가지고 있다
	// 메모리 누수 현상을 방지 ...
	// DAO => new를 이용해서 생성 => 사용하지 않는 DAO가 오라클을 연결하고 있다
	// 싱글턴은 데이터베이스에서는 필수 조건
	// 프로그래머 , 디벨로퍼 (coder)
	// java    web  html/css spring , 머신러닝 , msa
	// 15000  20000 8000     35000    70000   90000
	public static FoodDAO newInstance() 
	{
		if(dao==null)
			dao=new FoodDAO();
		return dao;
	}
	// 5. 기능
	// 5-1. 카테고리 출력
	public List<CategoryVO> food_category_list()
	{
		// 카테고리 1개의 정보 (번호, 이미지, 제목, 부제목) => CategoryVO
		List<CategoryVO> list=new ArrayList<CategoryVO>();
		try
		{
			getConnection();
			String sql="SELECT cno,title,subject,poster FROM food_category ORDER BY cno ASC";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				CategoryVO vo=new CategoryVO();
				vo.setCno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSubject(rs.getString(3));
				String poster=rs.getString(4);
//				poster=poster.substring(0,poster.indexOf("^"));
//				poster=poster.replace("#", "&");
//				vo.setPoster(poster);
				poster=poster.replace("#", "&");
				vo.setPoster(poster);
				list.add(vo);
			}
			rs.close();
			// list=> 받아서 브라우저로 전송 실행
			//			   ------------ Servlet , JSP
			//				Spring => Servlet
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
	// 5-1. 카테고리 정보
	public CategoryVO food_category_info(int cno)
	{
		CategoryVO vo=new CategoryVO();
		try
		{
			getConnection();
			String sql="SELECT title,subject FROM food_category WHERE cno="+cno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next();
			vo.setTitle(rs.getString(1));
			vo.setSubject(rs.getString(2));
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
		return vo;
	}
	// 5-2. 카테고리별 맛집 출력
	// 		List<FoodVO>
	public List<FoodVO> food_category_data(int cno)
	{
		List<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			getConnection();
//			String sql="SELECT fno,name,score,address,poster FROM food_house WHERE food_house.cno="+cno;
			String sql="SELECT fno,name,score,poster,address,phone,type FROM food_house WHERE cno="+cno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				FoodVO vo=new FoodVO();
//				vo.setFno(rs.getInt(1));
//				vo.setName(rs.getString(2));
//				vo.setScore(rs.getDouble(3));
//				vo.setAddress(rs.getString(4));
//				vo.setPoster(rs.getString(5));
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setScore(rs.getDouble(3));
				String poster=rs.getString(4);
				poster=poster.substring(0,poster.indexOf("^"));
				poster=poster.replace("#", "&");
				vo.setPoster(poster);
				String address=rs.getString(5);
				address=address.substring(0,address.lastIndexOf("지"));
				vo.setAddress(address.trim());
				vo.setPhone(rs.getString(6));
				vo.setType(rs.getString(7));
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
	// 5-3. 맛집 상세보기
	//		FoodVO
	public FoodVO food_detail_data(int fno)
	{
		FoodVO vo=new FoodVO();
		try
		{
			getConnection();
			String sql="SELECT fno,name,address,phone,type,price,parking,time,menu,poster FROM food_house WHERE fno="+fno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				vo.setFno(rs.getInt(1));
				vo.setName(rs.getString(2));
				String address=rs.getString(3);
				address=address.substring(0,address.lastIndexOf("지"));
				vo.setAddress(address.trim());
				vo.setPhone(rs.getString(4));
				vo.setType(rs.getString(5));
				vo.setPrice(rs.getString(6));
				vo.setParking(rs.getString(7));
				vo.setTime(rs.getString(8));
				vo.setMenu(rs.getString(9));
				String poster=rs.getString(10);
				poster=poster.substring(0,poster.indexOf("^"));
				poster=poster.replace("#", "&");
				vo.setPoster(poster);
				
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
		return vo;
	}
	// 5-4. 맛집 검색 ==>> detail로 해서 가져오면 안되나?
	// 		List<FoodVO>
	public List<FoodVO> food_house_find_data(String name)
	{
		List<FoodVO> list=new ArrayList<FoodVO>();
		try
		{
			getConnection();
			String sql="SELECT fno,name,address,phone,type,price,parking,time,menu,poster FROM food_house WHERE name LIKE '%"+name+"%'";
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
