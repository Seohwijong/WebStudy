package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
import com.sist.vo.*;
@Repository
public class ProductDAO {
@Autowired
private ProductMapper mapper;
// 목록
/*
 * @Select("SELECT pdno,title,poster,score,num "
		+ "FROM(SELECT pdno,title,poster,score,rownum as num "
		+ "FROM(SELECT pdno,title,poster,score "
		+ "FROM product_detail OREDER BY pdno ASC)) "
		+ "WHERE num BETWEEN #{start} AND #{end}")*/
public List<ProductVO> productListData(Map map)
{
	return mapper.productListData(map);
}
//@Select("SELECT CEIL(COUNT(*)/12.0) FROM product_detail")
public int productTotalPage()
{
	return mapper.productTotalPage();
}

}
