package com.sist.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.dao.*;
import com.sist.vo.*;
@Controller
public class ProductController {
 @Autowired
  private ProductDAO dao;
 
 @GetMapping("product/list.do")
 public String product_list()
 {
	  return "product/list";
 }
}
