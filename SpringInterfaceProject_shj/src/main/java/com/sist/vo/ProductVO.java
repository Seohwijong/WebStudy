package com.sist.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVO {
	private int pdno,goods_count;
	private String title,poster,subject,sale,priced_sale,original_pri,first_pri,delivery_pri;
	private double score;
}
