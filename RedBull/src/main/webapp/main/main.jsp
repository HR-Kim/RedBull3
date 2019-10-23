<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	          <div class="col-lg-12">
	            <p class="sub text-uppercase">핫세일 상품</p>
	            <h3><span>매일</span> 자정 <br />새로운 <span>특가상품</span></h3>
	            <h4>망설이면 품절! 집순이쇼핑몰 특별가</h4>
	            <a class="main_btn mt-40" href="${context}/product/get_hotSaleList.do">핫세일 상품 보기</a>
	          </div>
	        </div>
	      </div>
	    </div>
	  </section>
	 
	  <!--================ 신상 =================-->
	  <section class="feature_product_area section_gap_bottom_custom">
	    <div class="container">
	      <div class="row justify-content-center">
	        <div class="col-lg-12">
	          <div class="main_title">
	            <h2><span>new</span></h2>
	            <p>이번주 신상품</p>
	          </div>
	        </div>
	      </div>
	      
			    <!--================ 여기서부터 하기 : 컨트롤러에서 상품 데이터 전달해줘야 함 =================--> 
			      
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
			
				<!--================ //여기서부터 하기 : 컨트롤러에서 상품 데이터 전달해줘야 함 =================--> 
	
	      <div class="row">
	        <div class="col-lg-4 col-md-6">
	          <div class="single-product">
	            <div class="product-img">
	              <img class="img-fluid w-100" src="${context}/resources/img/product/feature-product/f-p-1.jpg" alt="" />
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
	              <img class="img-fluid w-100" src="${context}/resources/img/product/feature-product/f-p-2.jpg" alt="" />
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
	                <h4>Red women purses</h4>
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
	              <img class="img-fluid w-100" src="${context}/resources/img/product/feature-product/f-p-3.jpg" alt="" />
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
	                <h4>Men stylist Smart Watch</h4>
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
	  </section>
	  <!--================ End 신상 =================-->
		
	  <!--================ 랭킹 =================-->
	  <section class="new_product_area section_gap_top section_gap_bottom_custom">
	    <div class="container">
	      <div class="row justify-content-center">
	        <div class="col-lg-12">
	          <div class="main_title">
	            <h2><span>best</span></h2>
	            <p>이번주 인기상품</p>
	          </div>
	        </div>
	      </div>
	
	      <div class="row">
	        <div class="col-lg-6">
	          <div class="new_product">
	            <h5 class="text-uppercase">collection of 2019</h5>
	            <h3 class="text-uppercase">Men’s summer t-shirt</h3>
	            <div class="product-img">
	              <img class="img-fluid" src="${context}/resources/img/product/new-product/new-product1.png" alt="" />
	            </div>
	            <h4>$120.70</h4>
	            <a href="#" class="main_btn">Add to cart</a>
	          </div>
	        </div>
	
	        <div class="col-lg-6 mt-5 mt-lg-0">
	          <div class="row">
	            <div class="col-lg-6 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img class="img-fluid w-100" src="${context}/resources/img/product/new-product/n1.jpg" alt="" />
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
	                    <h4>Nike latest sneaker</h4>
	                  </a>
	                  <div class="mt-3">
	                    <span class="mr-4">$25.00</span>
	                    <del>$35.00</del>
	                  </div>
	                </div>
	              </div>
	            </div>
	
	            <div class="col-lg-6 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img class="img-fluid w-100" src="${context}/resources/img/product/new-product/n2.jpg" alt="" />
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
	                    <h4>Men’s denim jeans</h4>
	                  </a>
	                  <div class="mt-3">
	                    <span class="mr-4">$25.00</span>
	                    <del>$35.00</del>
	                  </div>
	                </div>
	              </div>
	            </div>
	
	            <div class="col-lg-6 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img class="img-fluid w-100" src="${context}/resources/img/product/new-product/n3.jpg" alt="" />
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
	                    <h4>quartz hand watch</h4>
	                  </a>
	                  <div class="mt-3">
	                    <span class="mr-4">$25.00</span>
	                    <del>$35.00</del>
	                  </div>
	                </div>
	              </div>
	            </div>
	
	            <div class="col-lg-6 col-md-6">
	              <div class="single-product">
	                <div class="product-img">
	                  <img class="img-fluid w-100" src="${context}/resources/img/product/new-product/n4.jpg" alt="" />
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
	                    <h4>adidas sport shoe</h4>
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
	    </div>
	  </section>
	  <!--================ End 랭킹 =================-->
	
	  <!--================ 핫세일 =================-->
	  <section class="inspired_product_area section_gap_bottom_custom">
	    <div class="container">
	      <div class="row justify-content-center">
	        <div class="col-lg-12">
	          <div class="main_title">
	            <h2><span>sale</span></h2>
	            <p>이번주 세일 상품</p>
	          </div>
	        </div>
	      </div>
	
	      <div class="row">
	        <div class="col-lg-3 col-md-6">
	          <div class="single-product">
	            <div class="product-img">
	              <img class="img-fluid w-100" src="${context}/resources/img/product/inspired-product/i1.jpg" alt="" />
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
	
	        <div class="col-lg-3 col-md-6">
	          <div class="single-product">
	            <div class="product-img">
	              <img class="img-fluid w-100" src="${context}/resources/img/product/inspired-product/i2.jpg" alt="" />
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
	
	        <div class="col-lg-3 col-md-6">
	          <div class="single-product">
	            <div class="product-img">
	              <img class="img-fluid w-100" src="${context}/resources/img/product/inspired-product/i3.jpg" alt="" />
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
	
	        <div class="col-lg-3 col-md-6">
	          <div class="single-product">
	            <div class="product-img">
	              <img class="img-fluid w-100" src="${context}/resources/img/product/inspired-product/i4.jpg" alt="" />
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
	  </section>
	  <!--================ End 핫세일 =================-->
	
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
       
</body>
</html>
