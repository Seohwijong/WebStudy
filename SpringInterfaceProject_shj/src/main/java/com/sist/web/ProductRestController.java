package com.sist.web;


import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.dao.ProductDAO;
import com.sist.vo.*;
/*
 *   
 */
@RestController
public class ProductRestController {
@Autowired
private ProductDAO dao;
@GetMapping(value="product/list_vue.do",produces="text/plain;charset=UTF-8")
public String product_list_vue(String page)
{
	String result="";
	try
	{
		 if(page==null)
			 page="1";
		 int curpage=Integer.parseInt(page);
		 Map map=new HashMap();
		 map.put("start", (curpage*12)-11);
		 map.put("end", curpage*12);
		 List<ProductVO> list=dao.productListData(map);
		 int totalpage=dao.productTotalPage();
		 // List => [] => JSONArray list를 배열로
		 // FoodLocationVO => {} => JSONObject
		 //[{},{},{}....]
		 JSONArray arr=new JSONArray(); //리스트를 배열로 자바스크립트와 호환하려면 JSON형태로 변환해야 함.
		 int i=0;
		 /*
		  * {fno:1,name:"",poster:"",score:5.0}
		  */
		 for(ProductVO vo:list)
		 {
			 JSONObject obj=new JSONObject();
			 obj.put("pdno", vo.getPdno());
			 obj.put("title", vo.getTitle());
			 obj.put("score", vo.getScore());
			 
			 
			 obj.put("poster", vo.getPoster());
			 if(i==0)
			 {
				 obj.put("curpage", curpage);
				 obj.put("totalpage", totalpage);
			 }
			 arr.add(obj);
			 i++;
		 }
		 result=arr.toJSONString();
	}catch(Exception ex) {}
	return result;
}
}
