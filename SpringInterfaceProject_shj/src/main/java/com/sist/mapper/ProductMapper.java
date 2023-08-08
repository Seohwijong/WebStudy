package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
/*
 * private int pdno,goods_count;
	private String title,poster,subject,sale,priced_sale,original_pri,first_pri,delivery_pri;
	private double score;
 */


import com.sist.vo.ProductVO;
public interface ProductMapper {
@Select("SELECT pdno,title,poster,score,num "
		+ "FROM(SELECT pdno,title,poster,score,rownum as num "
		+ "FROM(SELECT pdno,title,poster,score "
		+ "FROM product_detail ORDER BY pdno ASC)) "
		+ "WHERE num BETWEEN #{start} AND #{end}")
public List<ProductVO> productListData(Map map);
@Select("SELECT CEIL(COUNT(*)/12.0) FROM product_detail")
public int productTotalPage();
//상세
	@Select("SELECT pdno,title,poster,score,sale,priced_sale,original_pri,first_pri,delivery_pri,"
			+ "subject "
			+ "FROM product_detail "
			+ "WHERE fno=#{pdno}")
	public ProductVO productDetailData(int pdno);
	//검색 ==> Vue/React => 실시간 
	@Select("SELECT pno,title,poster,score,num "
			+ "FROM (SELECT pdno,title,poster,score,rownum as num "
			+ "FROM (SELECT pdno,title,poster,score "
			+ "FROM product_detail ORDER BY fno ASC WHERE title LIKE '%'||#{title}||'%')) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<ProductVO> productFindData(Map map);
}
