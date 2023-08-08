package com.sist.manager;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sist.dao.*;
import com.sist.vo.*;
public class DataCollection {
 // 데이터수집
	public void foodCategoryData()
	{
		// 오라클에 추가
		FoodDAO dao=FoodDAO.newInstance(); // 객체 생성 (싱글톤)
		try
		{
			// 사이트 연결
			for(int j=1;j<=3;j++)
			{
			Document doc=Jsoup.connect("https://www.zibsago.com/list2.php?id=&pg="+j+""
					+ "&&si=&gu=&search_dong=&sort_how=new&search_limit=&gubun=%EC%9E%90%EB%8F%99&file=&detail_search=&area_read=&gubun=11").get();
			Elements title=doc.select("td.font_malgun.font_23"); //30ro
			Elements addr=doc.select("dd.addr"); //30개
			Elements poster=doc.select("a ul li img"); //30개
//			Elements link=doc.select("ul.list-toplist-slider a"); //30개 태그 1개면 Element 많으면  Elements
			/*System.out.println(title.toString());
			System.out.println("====================================================================");
			System.out.println(subject.toString());
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println(poster.toString());
			System.out.println("======================================================================");
			System.out.println(link.toString());*/
			for(int i=0;i<title.size();i++)
			{
				System.out.println(title.get(i).text());
				System.out.println(addr.get(i).text());
				System.out.println("https://www.zibsago.com"+(poster.get(i).attr("src")).substring(1)); //속성값이 갖고있는 거 갖고오기
//				System.out.println(link.get(i).attr("href")); //href 속성이 갖고있는 값
				System.out.println("==================================================================");
//				CategoryVO vo=new CategoryVO();
				FoodVO vo=new FoodVO();
				vo.setTitle(title.get(i).text());
				vo.setAddr(addr.get(i).text());
//				vo.setLink(link.get(i).attr("href"));
				String p="https://www.zibsago.com"+(poster.get(i).attr("src")).substring(1);
				vo.setPoster(p);
				dao.foodDataInsert(vo);
				/*
				 * https://mp-seoul-image-production-s3.mangoplate.com/
				 * keyword_search/meta/pictures/gdu43nitdmvcjhs2.png?
				 * fit=around|600:400&crop=600:400;
				 * *,*&output-format=jpg&output-quality=80    & 있으면 데이터 사라짐 &=>#으로 바꿔서 출력
				 */
			}
			}
			System.out.println("저장 완료!!");
		}catch(Exception ex) {}
	}
//	public void foodDetailData()
//	{
//		FoodDAO dao=FoodDAO.newInstance();
//		try
//		{
//			List<CategoryVO> list=dao.foodCategoryData();
//			for(CategoryVO vo:list)
//			{
//				Document doc=Jsoup.connect(vo.getLink()).get();
//				Elements link=doc.select("div.info span.title a");
//				// 카테고리별
//				for(int i=0;i<link.size();i++)
//				{
//					// 실제 상세보기 데이터 읽기
//					System.out.println(link.get(i).attr("href"));
//					Document doc2=Jsoup.connect("https://www.mangoplate.com"+link.get(i).attr("href")).get();
//					FoodVO fvo=new FoodVO();
//					fvo.setNo(vo.getCno());
//					/*
//					 * <span class="title">
//                  		<h1 class="restaurant_name">몽중헌</h1>
//                    		<strong class="rate-point ">
//                      	<span>4.4</span>
//                    	</strong>
//
//                  		<p class="branch">방이점</p>
//                			</span>
//					 */
//					Element name=doc2.selectFirst("span.title h1.restaurant_name");
//					Element addr=doc2.selectFirst("strong.rate-point span");
//						/*
//					 * 
//              <figure class="restaurant-photos-item" onclick="trackEvent('CLICK_PICTURE', {&quot;position&quot;:1,&quot;restaurant_key&quot;:&quot;n4N6o-A4zE&quot;});" ng-click="mp20_gallery_open(1)" role="img" aria-label="몽중헌 - 방이동 먹자골목 정통 중식 / 일반 중식 | 맛집검색 망고플레이트" title="몽중헌 - 방이동 먹자골목 정통 중식 / 일반 중식 | 맛집검색 망고플레이트">
//                <img class="center-croping" src="https://mp-seoul-image-production-s3.mangoplate.com/12724/1604891_1684308007564_1000000915?fit=around|512:512&amp;crop=512:512;*,*&amp;output-format=jpg&amp;output-quality=80" alt="몽중헌 사진 - 서울시 송파구 방이동 44-5" onerror="this.src='https://mp-seoul-image-production-s3.mangoplate.com/web/resources/kssf5eveeva_xlmy.jpg?fit=around|*:*&amp;crop=*:*;*,*&amp;output-format=jpg&amp;output-quality=80'">
//
//                <div class="last_image" onclick="trackEvent('CLICK_PICTURE_MORE', {&quot;position&quot;:1,&quot;restaurant_key&quot;:&quot;n4N6o-A4zE&quot;});" ng-click="mp20_gallery_open(5, true)">
//                  <p class="txt">
//                    사진 더보기
//                    <span class="arrow-white"></span>
//                  </p>
//                </div>
//              </figure>
//					 */
//					Elements poster=doc2.select("figure.restaurant-photos-item img.center-croping");
//					String image="";
//					for(int j=0;j<poster.size();j++)
//					{
//						image+=poster.get(j).attr("src")+"^";
//					}
//					image=image.substring(0,image.lastIndexOf("^"));
//					image=image.replace("&", "#");
//					
//					System.out.println("번호:"+vo.getNo());
//					System.out.println("이름:"+name.text());
//					System.out.println("주소:"+score.text());
//					System.out.println("이미지:"+image);
//					
//					fvo.setTitle(name.text());
//					fvo.setAddr(addr.text());
//					fvo.setPoster(image);
//					/*
//					 * 주소 	서울특별시 송파구 위례성대로 10 S타워 20-21F
//						지번 서울시 송파구 방이동 44-5 S타워 20-21F
//						전화번호 	02-2202-8004
//						음식 종류 	정통 중식 / 일반 중식
//						가격대 	3만원-4만원
//						주차 	발렛
//						영업시간 	11:30 - 22:00
//
//						메뉴
//					 */
//					String addr="no",phone="no",type="no",price="no",parking="no",time="no",menu="no";
//					Elements etc=doc2.select("table.info tr th");
//					for(int a=0;a<etc.size();a++)
//					{
//						String ss=etc.get(a).text();
//						if(ss.equals("주소"))
//						{
//							Element e=doc2.select("table.info tr td").get(a);
//							addr=e.text();
//						}
//						else if(ss.equals("음식 종류"))
//						{
//							Element e=doc2.select("table.info tr td").get(a);
//							type=e.text();
//						}
//						else if(ss.equals("가격대"))
//						{
//							Element e=doc2.select("table.info tr td").get(a);
//							price=e.text();
//						}
//						else if(ss.equals("주차"))
//						{
//							Element e=doc2.select("table.info tr td").get(a);
//							parking=e.text();
//						}
//						else if(ss.equals("영업시간"))
//						{
//							Element e=doc2.select("table.info tr td").get(a);
//							time=e.text();
//						}
//						else if(ss.equals("전화번호"))
//						{
//							Element e=doc2.select("table.info tr td").get(a);
//							phone=e.text();
//						}
//						else if(ss.equals("메뉴"))
//						{
//							Element e=doc2.select("table.info tr td").get(a);
//							menu=e.text();
//						}
//					}
//					System.out.println("주소"+addr);
//					System.out.println("전화:"+phone);
//					System.out.println("음식 종류:"+type);
//					System.out.println("가격대:"+price);
//					System.out.println("주차:"+parking);
//					System.out.println("영업시간:"+time);
//					System.out.println("메뉴:"+menu);
//					System.out.println("++++++++++++++++++++++++++++++++++++++");
//					fvo.setAddress(addr);
//					fvo.setPhone(phone);
//					fvo.setType(type);
//					fvo.setPrice(price);
//					fvo.setParking(parking);
//					fvo.setTime(time);
//					fvo.setMenu(menu);
//					
//					dao.foodDataInsert(fvo);
//				}
//			}
//			System.out.println("저장완료!!");
//		}catch(Exception ex) {}
//	}
	public static void main(String[] args) {
		DataCollection dc=new DataCollection();
		dc.foodCategoryData();
//		dc.foodDetailData();
	}
}