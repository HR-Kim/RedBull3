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
	String pageSize = "9";	
	/**페이지 번호*/	
	String pageNum = "1";
	/**검색조건*/
	String searchDiv = "20";
	/**검색어*/
	String searchWord = "";	

	Search vo = (Search)request.getAttribute("vo");
	
	if(null!=vo){
		pageSize   = StringUtil.nvl(vo.getPageSize()+"", "9");
		pageNum    = StringUtil.nvl(vo.getPageNum()+"", "1");
		searchDiv  = StringUtil.nvl(vo.getSearchDiv(), "");
		searchWord = StringUtil.nvl(vo.getSearchWord(), "");
	}else{
		pageSize   = "9";
		pageNum    = "1";
		searchDiv  = "20";
		searchWord = "";
	}
	
	//paging
	//maxNum, currPageNo, rowPerPage, bottomCount, url, scriptName
	
	int maxNum      = 0;  //totalCnt
	int bottomCount = 9;
	int currPageNo  = 1;  //pageNum
	int rowPerPage  = 9; //pageSize
	
	String url 	  	  = request.getContextPath()+"/product/get_retrieve.do";
	String scriptName = "search_page"; 
	
	//totalCnt
	String totalCnt = (request.getAttribute("totalCnt")==null)? "0":request.getAttribute("totalCnt").toString();
	
	maxNum     = Integer.parseInt(totalCnt);
	currPageNo = Integer.parseInt(pageNum);
	rowPerPage = Integer.parseInt(pageSize);
	
	//git으로 할때
	String user =  System.getProperty("user.home");
%> 
<html lang="ko">
<body>
	<!--================Header Menu Area =================-->
	<jsp:include page="/main/header.jsp"></jsp:include>
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
					<!--================Top Area =================-->
					<div class="product_top_bar">
						<div class="left_dorp">
							<select class="sorting">
								<option value="1">Default sorting</option>
								<option value="2">Default sorting 01</option>
								<option value="4">Default sorting 02</option>
							</select>
						</div>
					</div>
					<!--//================Top Area =================-->
					<!--================Product Area =================-->
					<c:choose>
						<c:when test="${list.size()>0}">
							<div class="latest_product_inner">
								<div id="listTable" class="row">
									<c:forEach var="product" items="${list}">
										<div class="col-lg-4 col-md-6">
											<div class="single-product">
												<input type="hidden" value="${product.pNum}" />
												<div class="product-img embed-responsive embed-responsive-4by3">
													<img class="card-img embed-responsive-item" src="${context}/${product.detail}" alt="" />
												</div>
												<div class="product-btm">
													<a href="#" class="d-block"><h4>${product.pName}</h4></a>
													<div class="mt-3">
														<span class="mr-4">${Math.round(product.bPrice*(product.discount))}원</span>
														<del>${product.bPrice}원</del>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<h3>등록된 게시물이 없습니다.</h3>
							</div>
						</c:otherwise>
					</c:choose>
					<!--//================Product Area =================--> 
				</div>
				<!--================Left Area =================--> 
				<div class="col-lg-3">
					<div class="left_sidebar_area">
						<aside class="left_widgets p_filter_widgets">
							<div class="l_w_title">
								<h3>Search</h3>
							</div>
							<div class="widgets_inner">
								<form name="productFrm" id="productFrm" method="get">
									<input type="hidden" name="pageNum" id="pageNum" value="${vo.pageNum}" />
									<input type="hidden" name="searchDiv" id="searchDiv" />
									<input type="hidden" name="pNum" id="pNum" />
									<input type="text" class="form-control" id="searchWord" name="searchWord" placeholder="검색어" />
									<br>
									<button class="form-control btn btn-success" onclick="javascript:do_retrieve()" >검색</button>
								</form>
							</div>
						</aside>
					</div>
					<div class="left_sidebar_area">
						<aside class="left_widgets p_filter_widgets">
							<div class="l_w_title">
								<h3>Categories</h3>
							</div>
							<div class="widgets_inner">
								<ul class="list">
									<li>
										<a href="#">가구</a>
									</li>
									<li>
										<a href="#">침구</a>
									</li>
									<li>
										<a href="#">생활용품</a>
									</li>
								</ul>
							</div>
						</aside>
					</div>
					<div class="left_sidebar_area">
						<aside class="left_widgets p_filter_widgets">
							<div class="l_w_title">
								<h3>목록별 조회</h3>
							</div>
							<div class="widgets_inner">
								<form name="productFrmq" id="productFrmq" method="get">
									<input type="hidden" name="pageNum" id="pageNum" value="${vo.pageNum}" />
									<input type="hidden" name="searchDiv" id="searchDiv" />
									<input type="hidden" name="pNum" id="pNum" />
									<br>
									<button class="form-control btn btn-success" onclick="javascript:do_newList()" >신상검색</button>
									<button class="form-control btn btn-success" onclick="javascript:do_hotSaleList()" >세일검색</button>
									<button class="form-control btn btn-success" onclick="javascript:do_rankList()" >베스트검색</button>
								</form>
							</div>
						</aside>
					</div> 
				</div>
				<!--================Left Area =================-->
			</div>
		</div>
	</section>
	<!--================End Category Product Area =================-->
	<!--================footer Area  =================-->
	<jsp:include page="/main/footer.jsp"></jsp:include>
	<script type="text/javascript">
		//단건조회
		//listTable
	    $("#listTable>div>div").on("click",function(e){
	    	e.preventDefault();
	    	var div  = $(this);
	    	var input  =  div.children("input");
	    	console.log("input:"+input.val());
	    	
    		//if(null==input || input.length==1) return;
    		
    		var frm = document.productFrm;
    		frm.pNum.value = input.val();
    		frm.action = "${context}/product/get_selectOne.do";
    		frm.submit(); 
	    });
		
		//목록조회
		function do_retrieve(){
			var searchWord = $("#searchWord").text();
			var frm = document.productFrm;
			frm.pageNum.value= 1;
			frm.searchDiv.value="10";
			frm.action = "${context}/product/get_retrieve.do";
			frm.submit();
		}
		//신상품 조회
		function do_newList(){
			var frm = document.productFrmq;
			frm.pageNum.value= 1;
			frm.searchDiv.value="10";
			frm.action = "${context}/product/get_newList.do";
			frm.submit();
			
		}
		//세일상품 조회
		function do_hotSaleList(){
			var frm = document.productFrmq;
			frm.pageNum.value= 1;
			frm.searchDiv.value="10";
			frm.action = "${context}/product/get_hotSaleList.do";
			frm.submit();
			
		}
		//베스트상품 조회
		function do_rankList(){
			var frm = document.productFrmq;
			frm.pageNum.value= 1;
			frm.searchDiv.value="10";
			frm.action = "${context}/product/get_rankList.do";
			frm.submit();
			
		}
		
	</script>
</body>
</html>