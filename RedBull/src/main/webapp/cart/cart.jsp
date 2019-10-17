<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
    
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <link rel="icon" href="img/favicon.png" type="image/png" />
    <title>Eiser ecommerce</title>
    
    <!-- 부트스트랩 -->
	<link rel="stylesheet" href="${context}/resources/css/bootstrap.min.css" />
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${context}/resources/vendors/linericon/style.css" />
    <link rel="stylesheet" href="${context}/resources/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${context}/resources/css/themify-icons.css" />
    <link rel="stylesheet" href="${context}/resources/vendors/owl-carousel/owl.carousel.min.css" />
    <link rel="stylesheet" href="${context}/resources/vendors/lightbox/simpleLightbox.css" />
    <link rel="stylesheet" href="${context}/resources/vendors/nice-select/css/nice-select.css" />
    <link rel="stylesheet" href="${context}/resources/vendors/animate-css/animate.css" />
    <link rel="stylesheet" href="${context}/resources/vendors/jquery-ui/jquery-ui.css" />
    <!-- main css -->
    <link rel="stylesheet" href="${context}/resources/css/style.css" />
    <link rel="stylesheet" href="${context}/resources/css/responsive.css" />
  </head>

  <body>
    <!--================Header Menu Area =================-->
    <header class="header_area">
      <div class="top_menu">
        <div class="container">
          <div class="row">
            <div class="col-lg-7">
              <div class="float-left">
                <p>Phone: +01 256 25 235</p>
                <p>email: info@eiser.com</p>
              </div>
            </div>
            <div class="col-lg-5">
              <div class="float-right">
                <ul class="right_side">
                  <li>
                    <a href="cart.html">
                      gift card
                    </a>
                  </li>
                  <li>
                    <a href="tracking.html">
                      track order
                    </a>
                  </li>
                  <li>
                    <a href="contact.html">
                      Contact Us
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="main_menu">
        <div class="container">
          <nav class="navbar navbar-expand-lg navbar-light w-100">
            <!-- Brand and toggle get grouped for better mobile display -->
            <a class="navbar-brand logo_h" href="index.html">
              <img src="img/logo.png" alt="" />
            </a>
            <button
              class="navbar-toggler"
              type="button"
              data-toggle="collapse"
              data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div
              class="collapse navbar-collapse offset w-100"
              id="navbarSupportedContent"
            >
              <div class="row w-100 mr-0">
                <div class="col-lg-7 pr-0">
                  <ul class="nav navbar-nav center_nav pull-right">
                    <li class="nav-item">
                      <a class="nav-link" href="index.html">Home</a>
                    </li>
                    <li class="nav-item active submenu dropdown">
                      <a
                        href="#"
                        class="nav-link dropdown-toggle"
                        data-toggle="dropdown"
                        role="button"
                        aria-haspopup="true"
                        aria-expanded="false"
                        >Shop</a
                      >
                      <ul class="dropdown-menu">
                        <li class="nav-item">
                          <a class="nav-link" href="category.html"
                            >Shop Category</a
                          >
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="single-product.html"
                            >Product Details</a
                          >
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="checkout.html"
                            >Product Checkout</a
                          >
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="cart.html">Shopping Cart</a>
                        </li>
                      </ul>
                    </li>
                    <li class="nav-item submenu dropdown">
                      <a
                        href="#"
                        class="nav-link dropdown-toggle"
                        data-toggle="dropdown"
                        role="button"
                        aria-haspopup="true"
                        aria-expanded="false"
                        >Blog</a
                      >
                      <ul class="dropdown-menu">
                        <li class="nav-item">
                          <a class="nav-link" href="blog.html">Blog</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="single-blog.html"
                            >Blog Details</a
                          >
                        </li>
                      </ul>
                    </li>
                    <li class="nav-item submenu dropdown">
                      <a
                        href="#"
                        class="nav-link dropdown-toggle"
                        data-toggle="dropdown"
                        role="button"
                        aria-haspopup="true"
                        aria-expanded="false"
                        >Pages</a
                      >
                      <ul class="dropdown-menu">
                        <li class="nav-item">
                          <a class="nav-link" href="tracking.html">Tracking</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="elements.html">Elements</a>
                        </li>
                      </ul>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="contact.html">Contact</a>
                    </li>
                  </ul>
                </div>

                <div class="col-lg-5 pr-0">
                  <ul class="nav navbar-nav navbar-right right_nav pull-right">
                    <li class="nav-item">
                      <a href="#" class="icons">
                        <i class="ti-search" aria-hidden="true"></i>
                      </a>
                    </li>

                    <li class="nav-item">
                      <a href="#" class="icons">
                        <i class="ti-shopping-cart"></i>
                      </a>
                    </li>

                    <li class="nav-item">
                      <a href="#" class="icons">
                        <i class="ti-user" aria-hidden="true"></i>
                      </a>
                    </li>

                    <li class="nav-item">
                      <a href="#" class="icons">
                        <i class="ti-heart" aria-hidden="true"></i>
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </nav>
        </div>
      </div>
    </header>
    <!--================Header Menu Area =================-->

    <!--================Home Banner Area =================-->
    <section class="banner_area">
      <div class="banner_inner d-flex align-items-center">
        <div class="container">
          <div
            class="banner_content d-md-flex justify-content-between align-items-center"
          >
            <div class="mb-3 mb-md-0">
              <h2>Cart</h2>
              <p>Very us move be blessed multiply night</p>
            </div>
            <div class="page_link">
              <a href="index.html">Home</a>
              <a href="cart.html">Cart</a>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--================End Home Banner Area =================-->
    <!--================Cart Area =================-->
    <section class="cart_area">
      <div class="container">
        <div class="cart_inner">
          <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover">
              <thead>
                <tr>
			         <th class="text-center col-md-1 col-xs-1">
					 전체선택<input type="checkbox" id="checkAll" name="checkAll"></th>
			         <th class="text-center col-md-4 col-xs-4 ">상품</th>
			         <th class="text-center col-md-1 col-xs-1">상품가격</th>
			         <th class="text-center col-md-1 col-xs-1">할인 가격</th>
			         <th class="text-center col-md-1 col-xs-1">수량</th>
			         <th class="text-center col-md-1 col-xs-1">배송비</th>
			         <th class="text-center col-md-1 col-xs-1">최종 가격</th>
                </tr>
              </thead>
              
              <tbody>
               <c:choose>
	        	<c:when test="${list.size()>0 }">
	                <c:forEach var="cart" items="${list}">
	                <tr>
	                <td class="text-center">
	                <input type="checkbox" name="check" id="check">
	                </td>
	                  <td>
	                    <div class="media">
	                      <div class="media-left">
	                        <img
	                          src="${context}/${cart.saveFileNm}"
	                          class="media-object"
	                          style="width: 80px"
	                          alt=""
	                        />
	                      </div>
	                      <div class="media-body">
	                     	 <c:out value="${cart.pName}"/>
	                      </div>
	                    </div>
	                  </td>
	                  <td class="text-center">
	                  <c:out value="${cart.bPrice * (1-cart.discount) + cart.oPrice}"/>
	                  </td>
	                  <td class="text-center">
	                  <c:out value="${cart.bPrice*(1-cart.discount)}"/>
	                  </td>
	                  <td>
	                    <div class="product_count">
	                      <input
	                        type="text"
	                        name="qty"
	                        id="sst"
	                        maxlength="12"
	                        value="1"
	                        title="Quantity:"
	                        class="input-text qty"
	                      />
	                      <button
	                        onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;"
	                        class="increase items-count"
	                        type="button"
	                      >
	                        <i class="lnr lnr-chevron-up"></i>
	                      </button>
	                       <button
	                        onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;"
	                        class="reduced items-count"
	                        type="button"
	                      >
	                        <i class="lnr lnr-chevron-down"></i>
	                      </button>
	                    </div>
	                  </td>
	                  <td class="text-center">
	                    <c:out value="${cart.dPrice}"/>
	                  </td>
	                  <td class="text-center">
	                   <c:out value="${cart.bPrice * (1-cart.discount) + cart.oPrice + cart.dPrice}"/>
	                  </td>
	                </tr>
	            </c:forEach> 
			    </c:when>
		    <c:otherwise>
		         	<tr>
		         		<td class="text-center" colspan="99">장바구니가 비었습니다.</td>
		         	</tr>
		    </c:otherwise>
		</c:choose>
	              <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                </tr>
                <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td>
                    <h5>Subtotal</h5>
                  </td>
                  <td>
                    <h5>$2160.00</h5>
                  </td>
                </tr>
                <tr class="shipping_area">
                  <td></td>
                  <td></td>
                  <td>
                    <h5></h5>
                  </td>
                  <td>
                    <div class="shipping_box">
                      <ul class="list">
                        <li>
                          <a href="#">Flat Rate: $5.00</a>
                        </li>
                        <li>
                          <a href="#">Free Shipping</a>
                        </li>
                        <li>
                          <a href="#">Flat Rate: $10.00</a>
                        </li>
                        <li class="active">
                          <a href="#">Local Delivery: $2.00</a>
                        </li>
                      </ul>
                    </div>
                  </td>
                </tr>
                <tr class="out_button_area">
                  <td></td>
                  <td></td>
                  <td></td>
                </tr>
              </tbody>
            </table>
             <input type="button" id="do_delete" value="삭제하기"/>
           	<td>
                <div class="container">
                	<input type="button" class="btn btn-Success" value="쇼핑하기"/>
                	<input type="submit" class="btn btn-Success" value="결제하기"/>
<!--                    <input type="button" class="gray_btn" id="gray_btn" value="Continue Shopping" href="#" />
                  <input type="button" class="main_btn" id="main_btn" value="결제하기" href="#" /> -->
                </div>
             </td>
          </div>
        </div>
      </div>
    </section>
    <!--================End Cart Area =================-->
        <!-- Optional JavaScript -->
    	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<script src="${context}/resources/js/bootstrap.min.js"></script>
      	<script type="text/javascript">
       	$("#checkAll").click(function(){
   			if($("#checkAll").is(':checked')==true){
   				$("input[name='check']").prop("checked",true); //check
   				
   			}else{
   				$("input[name='check']").prop("checked",false); //check해제
   			}
   			
      	}); 
       	
       	$("#do_delete").on("click",function(){
       		//alert("do_delete");
       		console.log("cartNum:" + 4("#cartNum").val());
       		if(confirm("삭제하시겠습니까?") == false) return;
       	});
       	
      	</script>
      	
    <!--================ start footer Area  =================-->
    	<jsp:include page="/main/footer.jsp"></jsp:include>
    <!--================ End footer Area  =================-->
    
     <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="${context}/resources/js/popper.js"></script>

    <script src="${context}/resources/js/stellar.js"></script>
    <script src="${context}/resources/vendors/lightbox/simpleLightbox.min.js"></script>
    <script src="${context}/resources/vendors/nice-select/js/jquery.nice-select.min.js"></script>
    <script src="${context}/resources/vendors/isotope/imagesloaded.pkgd.min.js"></script>
    <script src="${context}/resources/vendors/isotope/isotope-min.js"></script>
    <script src="${context}/resources/vendors/owl-carousel/owl.carousel.min.js"></script>
    <script src="${context}/resources/js/jquery.ajaxchimp.min.js"></script>
    <script src="${context}/resources/js/mail-script.js"></script>
    <script src="${context}/resources/vendors/jquery-ui/jquery-ui.js"></script>
    <script src="${context}/resources/vendors/counter-up/jquery.waypoints.min.js"></script>
    <script src="${context}/resources/vendors/counter-up/jquery.counterup.js"></script>
    <script src="${context}/resources/js/theme.js"></script>
  </body>
</html>
    