<%@page import="kr.co.redbull.code.service.Code"%>
<%@page import="kr.co.redbull.cmn.StringUtil"%>
<%@page import="kr.co.redbull.cmn.Search"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%--
  /**
  * @Class Name : board_list.jsp
  * @Description : 게시판 list
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018.04.26            최초 생성
  *
  * author SIST 개발팀
  * since 2019.09.24
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>
<%
	/**페이지 사이즈*/
	String pageSize = "10";	
	/**페이지 번호*/	
	String pageNum = "1";
	/**검색조건*/
	String searchDiv = "20";
	/**검색어*/
	String searchWord = "";	

	Search vo = (Search)request.getAttribute("vo");
	
	if(null!=vo){
		pageSize   = StringUtil.nvl(vo.getPageSize()+"", "10");
		pageNum    = StringUtil.nvl(vo.getPageNum()+"", "1");
		searchDiv  = StringUtil.nvl(vo.getSearchDiv(), "");
		searchWord = StringUtil.nvl(vo.getSearchWord(), "");
	}else{
		pageSize   = "10";
		pageNum    = "1";
		searchDiv  = "20";
		searchWord = "";
	}
	
	//paging
	//maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName
	
	int maxNum      = 0;  //totalCnt
	int bottomCount = 10;
	int currPageNo  = 1;  //pageNum
	int rowPerPage  = 10; //pageSize
	
	String url 	  	  = request.getContextPath()+"/board/get_retrieve.do";
	String scriptName = "search_page"; 
	
	//totalCnt
	String totalCnt = (request.getAttribute("totalCnt")==null)? "0":request.getAttribute("totalCnt").toString();
	
	maxNum     = Integer.parseInt(totalCnt);
	currPageNo = Integer.parseInt(pageNum);
	rowPerPage = Integer.parseInt(pageSize);
	
%> 
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
	<title>스토어</title>
	
	<!-- 부트스트랩 -->
	<link rel="stylesheet" href="${context}/resources/css/bootstrap.css" />
	
	<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
	<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
	<!--[if lt IE 9]>
	  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" href="${context}/resources/vendors/linericon/style.css" />
	<link rel="stylesheet" href="${context}/resources/css/font-awesome.min.css" />
	<link rel="stylesheet" href="${context}/resources/css/themify-icons.css" />
	<link rel="stylesheet" href="${context}/resources/vendors/lightbox/simpleLightbox.css" />
	<link rel="stylesheet" href="${context}/resources/vendors/nice-select/css/nice-select.css" />
	<link rel="stylesheet" href="${context}/resources/vendors/animate-css/animate.css" />
	<!-- main css -->
	<link rel="stylesheet" href="${context}/resources/css/style.css" />
	<link rel="stylesheet" href="${context}/resources/css/responsive.css" />
</head>
<body>
	<!--================Home Banner Area =================-->
	<section class="banner_area">
		<div class="banner_inner d-flex align-items-center">
			<div class="container">
				<div
					class="banner_content d-md-flex justify-content-between align-items-center">
					<div class="mb-3 mb-md-0">
						<h2>스토어</h2>
					</div>
					<div class="page_link">
						<a href="index.html">메인 화면</a>
						<a href="single-product.html">스토어</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================End Home Banner Area =================-->
	
	<!--================Category Product Area =================-->
	<section class="cat_product_area section_gap">
	  <div class="container">
	    <div class="row flex-row-reverse">
	      <div class="col-lg-9">
	        <div class="product_top_bar">
	          <div class="left_dorp">
	            <select class="sorting">
	              <option value="1">Default sorting</option>
	              <option value="2">Default sorting 01</option>
	              <option value="4">Default sorting 02</option>
	            </select>
	            <select class="show">
	              <option value="1">Show 12</option>
	              <option value="2">Show 14</option>
	              <option value="4">Show 16</option>
	            </select>
	          </div>
	        </div>
	        
	        <div class="latest_product_inner">
	          <div class="row">
	            <div class="col-lg-4 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img
	                    class="card-img"
	                    src="${context}/resources/img/product/inspired-product/i1.jpg"
	                    alt=""
	                  />
	                  <div class="p_icon">
	                    <a href="#">
	                      <i class="ti-eye"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-heart"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-shopping-cart"></i>
	                    </a>
	                  </div>
	                </div>
	                <div class="product-btm">
	                  <a href="#" class="d-block">
	                    <h4>Latest men’s sneaker</h4>
	                  </a>
	                  <div class="mt-3">
	                    <span class="mr-4">$25.00</span>
	                    <del>$35.00</del>
	                  </div>
	                </div>
	              </div>
	            </div>
	
	            <div class="col-lg-4 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img
	                    class="card-img"
	                    src="${context}/resources/img/product/inspired-product/i2.jpg"
	                    alt=""
	                  />
	                  <div class="p_icon">
	                    <a href="#">
	                      <i class="ti-eye"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-heart"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-shopping-cart"></i>
	                    </a>
	                  </div>
	                </div>
	                <div class="product-btm">
	                  <a href="#" class="d-block">
	                    <h4>Latest men’s sneaker</h4>
	                  </a>
	                  <div class="mt-3">
	                    <span class="mr-4">$25.00</span>
	                    <del>$35.00</del>
	                  </div>
	                </div>
	              </div>
	            </div>
	
	            <div class="col-lg-4 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img
	                    class="card-img"
	                    src="${context}/resources/img/product/inspired-product/i3.jpg"
	                    alt=""
	                  />
	                  <div class="p_icon">
	                    <a href="#">
	                      <i class="ti-eye"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-heart"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-shopping-cart"></i>
	                    </a>
	                  </div>
	                </div>
	                <div class="product-btm">
	                  <a href="#" class="d-block">
	                    <h4>Latest men’s sneaker</h4>
	                  </a>
	                  <div class="mt-3">
	                    <span class="mr-4">$25.00</span>
	                    <del>$35.00</del>
	                  </div>
	                </div>
	              </div>
	            </div>
	
	            <div class="col-lg-4 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img
	                    class="card-img"
	                    src="${context}/resources/img/product/inspired-product/i4.jpg"
	                    alt=""
	                  />
	                  <div class="p_icon">
	                    <a href="#">
	                      <i class="ti-eye"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-heart"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-shopping-cart"></i>
	                    </a>
	                  </div>
	                </div>
	                <div class="product-btm">
	                  <a href="#" class="d-block">
	                    <h4>Latest men’s sneaker</h4>
	                  </a>
	                  <div class="mt-3">
	                    <span class="mr-4">$25.00</span>
	                    <del>$35.00</del>
	                  </div>
	                </div>
	              </div>
	            </div>
	
	            <div class="col-lg-4 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img
	                    class="card-img"
	                    src="${context}/resources/img/product/inspired-product/i5.jpg"
	                    alt=""
	                  />
	                  <div class="p_icon">
	                    <a href="#">
	                      <i class="ti-eye"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-heart"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-shopping-cart"></i>
	                    </a>
	                  </div>
	                </div>
	                <div class="product-btm">
	                  <a href="#" class="d-block">
	                    <h4>Latest men’s sneaker</h4>
	                  </a>
	                  <div class="mt-3">
	                    <span class="mr-4">$25.00</span>
	                    <del>$35.00</del>
	                  </div>
	                </div>
	              </div>
	            </div>
	
	            <div class="col-lg-4 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img
	                    class="card-img"
	                    src="${context}/resources/img/product/inspired-product/i6.jpg"
	                    alt=""
	                  />
	                  <div class="p_icon">
	                    <a href="#">
	                      <i class="ti-eye"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-heart"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-shopping-cart"></i>
	                    </a>
	                  </div>
	                </div>
	                <div class="product-btm">
	                  <a href="#" class="d-block">
	                    <h4>Latest men’s sneaker</h4>
	                  </a>
	                  <div class="mt-3">
	                    <span class="mr-4">$25.00</span>
	                    <del>$35.00</del>
	                  </div>
	                </div>
	              </div>
	            </div>
	
	            <div class="col-lg-4 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img
	                    class="card-img"
	                    src="${context}/resources/img/product/inspired-product/i7.jpg"
	                    alt=""
	                  />
	                  <div class="p_icon">
	                    <a href="#">
	                      <i class="ti-eye"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-heart"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-shopping-cart"></i>
	                    </a>
	                  </div>
	                </div>
	                <div class="product-btm">
	                  <a href="#" class="d-block">
	                    <h4>Latest men’s sneaker</h4>
	                  </a>
	                  <div class="mt-3">
	                    <span class="mr-4">$25.00</span>
	                    <del>$35.00</del>
	                  </div>
	                </div>
	              </div>
	            </div>
	
	            <div class="col-lg-4 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img
	                    class="card-img"
	                    src="${context}/resources/img/product/inspired-product/i8.jpg"
	                    alt=""
	                  />
	                  <div class="p_icon">
	                    <a href="#">
	                      <i class="ti-eye"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-heart"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-shopping-cart"></i>
	                    </a>
	                  </div>
	                </div>
	                <div class="product-btm">
	                  <a href="#" class="d-block">
	                    <h4>Latest men’s sneaker</h4>
	                  </a>
	                  <div class="mt-3">
	                    <span class="mr-4">$25.00</span>
	                    <del>$35.00</del>
	                  </div>
	                </div>
	              </div>
	            </div>
	
	            <div class="col-lg-4 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img
	                    class="card-img"
	                    src="${context}/resources/img/product/inspired-product/i2.jpg"
	                    alt=""
	                  />
	                  <div class="p_icon">
	                    <a href="#">
	                      <i class="ti-eye"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-heart"></i>
	                    </a>
	                    <a href="#">
	                      <i class="ti-shopping-cart"></i>
	                    </a>
	                  </div>
	                </div>
	                <div class="product-btm">
	                  <a href="#" class="d-block">
	                    <h4>Latest men’s sneaker</h4>
	                  </a>
	                  <div class="mt-3">
	                    <span class="mr-4">$25.00</span>
	                    <del>$35.00</del>
	                  </div>
	                </div>
	              </div>
	            </div>
	          </div>
	        </div>
	      </div>
	
	      <div class="col-lg-3">
	        <div class="left_sidebar_area">
	          <aside class="left_widgets p_filter_widgets">
	            <div class="l_w_title">
	              <h3>Browse Categories</h3>
	            </div>
	            <div class="widgets_inner">
	              <ul class="list">
	                <li>
	                  <a href="#">Frozen Fish</a>
	                </li>
	                <li>
	                  <a href="#">Dried Fish</a>
	                </li>
	                <li>
	                  <a href="#">Fresh Fish</a>
	                </li>
	                <li>
	                  <a href="#">Meat Alternatives</a>
	                </li>
	                <li>
	                  <a href="#">Fresh Fish</a>
	                </li>
	                <li>
	                  <a href="#">Meat Alternatives</a>
	                </li>
	                <li>
	                  <a href="#">Meat</a>
	                </li>
	              </ul>
	            </div>
	          </aside>
	
	          <aside class="left_widgets p_filter_widgets">
	            <div class="l_w_title">
	              <h3>Product Brand</h3>
	            </div>
	            <div class="widgets_inner">
	              <ul class="list">
	                <li>
	                  <a href="#">Apple</a>
	                </li>
	                <li>
	                  <a href="#">Asus</a>
	                </li>
	                <li class="active">
	                  <a href="#">Gionee</a>
	                </li>
	                <li>
	                  <a href="#">Micromax</a>
	                </li>
	                <li>
	                  <a href="#">Samsung</a>
	                </li>
	              </ul>
	            </div>
	          </aside>
	
	          <aside class="left_widgets p_filter_widgets">
	            <div class="l_w_title">
	              <h3>Color Filter</h3>
	            </div>
	            <div class="widgets_inner">
	              <ul class="list">
	                <li>
	                  <a href="#">Black</a>
	                </li>
	                <li>
	                  <a href="#">Black Leather</a>
	                </li>
	                <li class="active">
	                  <a href="#">Black with red</a>
	                </li>
	                <li>
	                  <a href="#">Gold</a>
	                </li>
	                <li>
	                  <a href="#">Spacegrey</a>
	                </li>
	              </ul>
	            </div>
	          </aside>
	
	          <aside class="left_widgets p_filter_widgets">
	            <div class="l_w_title">
	              <h3>Price Filter</h3>
	            </div>
	            <div class="widgets_inner">
	              <div class="range_item">
	                <div id="slider-range"></div>
	                <div class="">
	                  <label for="amount">Price : </label>
	                  <input type="text" id="amount" readonly />
	                </div>
	              </div>
	            </div>
	          </aside>
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
	<!--================End Category Product Area =================-->
	
	<!-- Optional JavaScript -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script src="${context}/resources/js/stellar.js"></script>
	<script src="${context}/resources/vendors/lightbox/simpleLightbox.min.js"></script>
	<script src="${context}/resources/vendors/isotope/imagesloaded.pkgd.min.js"></script>
	<script src="${context}/resources/vendors/isotope/isotope-min.js"></script>
	<script type="text/javascript">
		//단건조회
		//listTable
	    $("#listTable>img").on("click","a",function(){
	    	var tr  = $(this);
	    	var td  =  tr.children();
	    	var pNum = td.eq(0).text();
	    	console.log("pNum:"+pNum);
	    	
    		if(null==pNum || pNum.length==1) return;
    		
    		var frm = document.productFrm;
    		frm.pNum.value = pNum;
    		frm.action = "${context}/product/get_selectOne.do";
    		frm.submit(); 
	    });
		
		//목록조회
		function do_retrieve(){
			
			var searchWord = $("#searchWord").text();
			
			var frm = document.productFrm;
			frm.pageNum.value= 1;
			frm.action = "${context}/product/get_retrieve.do";
			frm.submit();
		}
	
		$("#do_retrieve").on("click", function(){
			//alert('doRetrieve');
			do_retrieve();
		});
	</script>
</body>
</html>