<%@page import="kr.co.redbull.cmn.StringUtil"%>
<%@page import="kr.co.redbull.cmn.Search"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:set var="context" value="${pageContext.request.contextPath }" />

<!--================Header Menu Area =================-->
<jsp:include page="header.jsp"></jsp:include>
     
<html lang="ko">
  <body>
<%-- 	${user } --%>

	  <!--================Home Banner Area =================-->
	  <section class="home_banner_area mb-40">
	    <div class="banner_inner d-flex align-items-center">
	      <div class="container">
	        <div class="banner_content row">
      	      <c:choose>
       	  		<c:when test="${locale == 'en' }"> <!-- 다국어(영어): 로고 -->
       	  		  <div class="col-lg-12">
		            <p class="sub text-uppercase">Promotional items</p>
		            <h3><span>Daily</span> Hot<span> Sale </span></h3>
		            <h4>Don't miss it!</h4>
		            <a class="main_btn mt-40" href="${context}/product/get_hotSaleList.do">Check Out</a>
		          </div>
		        </c:when>
		        <c:otherwise> <!-- 다국어(영어): 로고 -->
				  <div class="col-lg-12">
		            <p class="sub text-uppercase">핫세일 상품</p>
		            <h3><span>매일</span> 자정 <br />새로운 <span>특가상품</span></h3>
		            <h4>망설이면 품절! 집순이쇼핑몰 특별가</h4>
		            <a class="main_btn mt-40" href="${context}/product/get_hotSaleList.do">핫세일 상품 보기</a>
		          </div>
		        </c:otherwise>
		      </c:choose>
	        </div>
	      </div>
	    </div>
	  </section>
	 
	  <!--================ 상품 리스트: 신상, 랭킹, 세일 =================-->
	  <section class="feature_product_area section_gap_bottom_custom">
	    <div class="container">
	    	
	      <!--================ 신상 =================-->
	      <div class="row justify-content-center">
	        <div class="col-lg-12">
	          <div class="main_title">
	            <h2><span>new</span></h2>
	            <p>이번주 신상품</p>
	          </div>
	        </div>
	      </div>
			<c:choose>
				<c:when test="${newlist.size()>0}">
				    <div class="new_product_area" style="margin-bottom: 10px;">
		                 <a href="${context}/product/get_newList.do" class="d-block text-right">
		                   <h4>더보기</h4>
		                 </a>
		            </div>
					<div class="latest_product_inner" style="margin-bottom: 50px;">
						<div id="listTable" class="row">
							<c:forEach var="product" items="${newlist}" begin="0" end="2">
								<div class="col-lg-4 col-md-6">
									<div class="single-product">
										<input type="hidden" value="${product.pNum}" />
										<div class="product-img embed-responsive embed-responsive-4by3">
											<img class="card-img embed-responsive-item" src="${context}/${product.detail}" alt="" />
										</div>
										<div class="product-btm">
											<a href="#" class="d-block"><h4>${product.pName}</h4></a>
											<div class="mt-3">
												<span class="mr-4">${Math.round(product.bPrice*(1-product.discount))}원</span>
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
			
		  <!--================ 랭킹 =================-->
		  <div class="row justify-content-center">
	        <div class="col-lg-12">
	          <div class="main_title">
	            <h2><span>best</span></h2>
	            <p>이번주 인기상품</p>
	          </div>
	        </div>
	      </div>
			<c:choose>
				<c:when test="${ranklist.size()>0}">
				    <div class="new_product_area" style="margin-bottom: 10px;">
		                 <a href="${context}/product/get_rankList.do" class="d-block text-right">
		                   <h4>더보기</h4>
		                 </a>
		            </div>
					<div class="latest_product_inner" style="margin-bottom: 50px;">
						<div id="listTable" class="row">
							<c:forEach var="product" items="${ranklist}" begin="0" end="2">
								<div class="col-lg-4 col-md-6">
									<div class="single-product">
										<input type="hidden" value="${product.pNum}" />
										<div class="product-img embed-responsive embed-responsive-4by3">
											<img class="card-img embed-responsive-item" src="${context}/${product.detail}" alt="" />
										</div>
										<div class="product-btm">
											<a href="#" class="d-block"><h4>${product.pName}</h4></a>
											<div class="mt-3">
												<span class="mr-4">${Math.round(product.bPrice*(1-product.discount))}원</span>
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
			
		  <!--================ 핫세일 =================-->
		  <div class="row justify-content-center">
	        <div class="col-lg-12">
	          <div class="main_title">
	            <h2><span>sale</span></h2>
	            <p>이번주 세일상품</p>
	          </div>
	        </div>
	      </div>
			<c:choose>
				<c:when test="${salelist.size()>0}">
				    <div class="new_product_area" style="margin-bottom: 10px;">
		                 <a href="${context}/product/get_hotSaleList.do" class="d-block text-right">
		                   <h4>더보기</h4>
		                 </a>
		            </div>
					<div class="latest_product_inner" style="margin-bottom: 50px;" >
						<div id="listTable" class="row">
							<c:forEach var="product" items="${salelist}" begin="0" end="2">
								<div class="col-lg-4 col-md-6">
									<div class="single-product">
										<input type="hidden" value="${product.pNum}" />
										<div class="product-img embed-responsive embed-responsive-4by3">
											<img class="card-img embed-responsive-item" src="${context}/${product.detail}" alt="" />
										</div>
										<div class="product-btm">
											<a href="#" class="d-block"><h4>${product.pName}</h4></a>
											<div class="mt-3">
												<span class="mr-4">${Math.round(product.bPrice*(1-product.discount))}원</span>
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
			
		</div>
	  </section>
	  <!--================ End 상품 리스트: 신상, 랭킹, 세일 =================-->
	  
    <form name="productFrm" id="productFrm" method="get">
		<input type="hidden" name="pageNum" id="pageNum" value="${vo.pageNum}" />
		<input type="hidden" name="searchDiv" id="searchDiv" />
		<input type="hidden" name="pNum" id="pNum" />
	</form>

<%-- 	  <!--================ Start Blog Area =================-->
	  <section class="blog-area section-gap">
	    <div class="container">
	      <div class="row justify-content-center">
	        <div class="col-lg-12">
	          <div class="main_title">
	            <h2><span>latest blog</span></h2>
	            <p>Bring called seed first of third give itself now ment</p>
	          </div>
	        </div>
	      </div>
	
	      <div class="row">
	        <div class="col-lg-4 col-md-6">
	          <div class="single-blog">
	            <div class="thumb">
	              <img class="img-fluid" src="${context}/resources/img/b1.jpg" alt="">
	            </div>
	            <div class="short_details">
	              <div class="meta-top d-flex">
	                <a href="#">By Admin</a>
	                <a href="#"><i class="ti-comments-smiley"></i>2 Comments</a>
	              </div>
	              <a class="d-block" href="single-blog.html">
	                <h4>Ford clever bed stops your sleeping
	                  partner hogging the whole</h4>
	              </a>
	              <div class="text-wrap">
	                <p>
	                  Let one fifth i bring fly to divided face for bearing the divide unto seed winged divided light
	                  Forth.
	                </p>
	              </div>
	              <a href="#" class="blog_btn">Learn More <span class="ml-2 ti-arrow-right"></span></a>
	            </div>
	          </div>
	        </div>
	        
	        <div class="col-lg-4 col-md-6">
	          <div class="single-blog">
	            <div class="thumb">
	              <img class="img-fluid" src="${context}/resources/img/b2.jpg" alt="">
	            </div>
	            <div class="short_details">
	              <div class="meta-top d-flex">
	                <a href="#">By Admin</a>
	                <a href="#"><i class="ti-comments-smiley"></i>2 Comments</a>
	              </div>
	              <a class="d-block" href="single-blog.html">
	                <h4>Ford clever bed stops your sleeping
	                  partner hogging the whole</h4>
	              </a>
	              <div class="text-wrap">
	                <p>
	                  Let one fifth i bring fly to divided face for bearing the divide unto seed winged divided light
	                  Forth.
	                </p>
	              </div>
	              <a href="#" class="blog_btn">Learn More <span class="ml-2 ti-arrow-right"></span></a>
	            </div>
	          </div>
	        </div>
	
	        <div class="col-lg-4 col-md-6">
	          <div class="single-blog">
	            <div class="thumb">
	              <img class="img-fluid" src="${context}/resources/img/b3.jpg" alt="">
	            </div>
	            <div class="short_details">
	              <div class="meta-top d-flex">
	                <a href="#">By Admin</a>
	                <a href="#"><i class="ti-comments-smiley"></i>2 Comments</a>
	              </div>
	              <a class="d-block" href="single-blog.html">
	                <h4>Ford clever bed stops your sleeping
	                  partner hogging the whole</h4>
	              </a>
	              <div class="text-wrap">
	                <p>
	                  Let one fifth i bring fly to divided face for bearing the divide unto seed winged divided light
	                  Forth.
	                </p>
	              </div>
	              <a href="#" class="blog_btn">Learn More <span class="ml-2 ti-arrow-right"></span></a>
	            </div>
	          </div>
	        </div>
	      </div>
	    </div>
	  </section>
	  <!--================ End Blog Area =================--> --%>
	  
	  <!--================footer Area  =================-->
	  <jsp:include page="footer.jsp"></jsp:include>
	  
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
				
	  </script>
       
</body>
</html>
