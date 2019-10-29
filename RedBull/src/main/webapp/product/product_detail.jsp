<%@page import="kr.co.redbull.code.service.Code"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.redbull.opt.service.Opt"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.redbull.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	/* OptSelect 설정
	oNum=36, oName=J01옵션명_125, oPrice=10000, pNum=71, iNum=10, toString()=DTO [totalCnt=0, num=0
	*/	
	List<Opt> optList = (request.getAttribute("optList")==null)?(List<Opt>)new ArrayList<Opt>():(List<Opt>)request.getAttribute("optList");
	List<Code> coptList = new ArrayList<Code>();
	for(int i=0; i<optList.size(); i++){
		Code code = new Code(optList.get(i).getoNum(),
							 optList.get(i).getoName()+" | "+optList.get(i).getoPrice()+"원"							 
							);
		coptList.add(code);
	}
%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<body>
<!--================Header Menu Area =================-->
<jsp:include page="/main/header.jsp"></jsp:include>
<!--================Form Group - Product==============-->
<form name="frm_pNum" method="post" >
	<input type="hidden" id="pNum" name="pNum" value="${productVO.pNum}"/>
		
</form>


<form name="payFrm" method="get" id="payFrm" >
	<input type="hidden" id="pName" name="pName" value="${productVO.pName}"/>	
	<input type="hidden" id="bPrice" name="bPrice" value="${productVO.bPrice}"/>
	<input type="hidden" id="discount" name="discount" value="${productVO.discount}"/>
	<input type="hidden" id="dPrice" name="dPrice" value="${productVO.dPrice}"/>
	<input type="hidden" id="optSelect" name="optSelect" />
	<input type="hidden" id="cartCnt" name="cartCnt"/>
	<input type="hidden" id=oNum name="oNum" />
<%-- 	<input type="hidden" id=codeId name="codeId" value="${vo.getCodeId()}" /> --%>
</form>

<!--================Home Banner Area =================-->
<section class="banner_area">
	<div class="banner_inner d-flex align-items-center">
		<div class="container">
			<div
				class="banner_content d-md-flex justify-content-between align-items-center">
				<div class="mb-3 mb-md-0">
					<h2>Product Details</h2>
				</div>
				<div class="page_link">
					<a href="index.html">Home</a> <a href="single-product.html">Product	Details</a>
				</div>
			</div>
		</div>
	</div>
</section>
<!--================End Home Banner Area =================-->

<!--================Single Product Area =================-->
<div class="product_image_area">
	<div class="container">
		<div class="row s_product_inner">
			<div class="col-lg-6">
				<div class="s_product_img">
					<div id="carouselExampleInterval" class="carousel slide" data-ride="carousel">
						<!-- Wrapper for slides -->
						<div id="carousel" class="carousel-inner">
							<c:choose>
								<c:when test="${imageList.size()>0}">
									<c:forEach var="image" items="${imageList}">
										<div class="carousel-item">
											<img class="d-block w-100" src="${context}/${image.saveFileNm}" />
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<div class="carousel-item">
										<img class="d-block w-100" src="${context}/resources/img/product/noimage.jpg" />
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<!-- Left and right controls -->
						<a class="carousel-control-prev carousel-control" href="#carouselExampleInterval" role="button" data-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a>
						<a class="carousel-control-next carousel-control" href="#carouselExampleInterval" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>
			<!-- Product 
				pNum=71, pName=J01상품명_125, 
				bPrice=10000, detail=J01상세정보_125, 
				discount=0.1, dPrice=1000, pCategory=10, 
				regId=J01등록자_125, regDt=2019/09/27, 
				modId=null, modDt=null,
				toString()=DTO [totalCnt=0, num=0]]
			-->
			<div class="col-lg-5 offset-lg-1">
				<div class="s_product_text">
					<h3>${productVO.pName}</h3>
					<h2>${Math.round(productVO.bPrice*(1-productVO.discount))} 원</h2>
					<ul class="list">
						<li>
							<a class="active" href="#"><span>할인</span> : ${productVO.discount*100 }%</a>
						</li>
						<li>
							<a class="active" href="#"><span>배송비</span> : ${productVO.dPrice} 원</a>
						</li>
					</ul>
					<hr style="border:none; border:1px dotted #d5d5d5;">
					<div class="card_area">
						<!-- 옵션 박스 -->
						<%=StringUtil.makeSelectBox(coptList, "optSelect", false) %>
					</div>
					<br>
					<div class="product_count">
						<label for="qty">Quantity:</label> <input type="text" name="qty" id="sst" maxlength="12" value="1" title="Quantity:" class="input-text qty" />
						<button onclick="javascript:countUp()" class="increase items-count" type="button">
							<i class="lnr lnr-chevron-up"></i>
						</button>
						<button onclick="javascript:countDown()" class="reduced items-count" type="button">
							<i class="lnr lnr-chevron-down"></i>
						</button>
					</div>
					<div class="card_area">
						<a class="main_btn" id="cart">장바구니</a>
						<a class="main_btn" id="pay">결제하기</a>
						<a class="main_btn" href="${context}/product/get_retrieve.do">뒤로</a>
						<a class="icon_btn" id="good">
							<i class="lnr lnr lnr-heart"></i>
						</a>
						<hr>
						<a class="main_btn" href="javascript:update_product();">수정하기</a>
						<a class="main_btn" href="javascript:delete_product();">삭제하기</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--================End Single Product Area =================-->

<!--================Product Description Area =================-->
<section class="product_description_area">
	<div class="container">
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="nav-item">
				<a class="nav-link" id="home-tab" data-toggle="tab" onclick="javascript:goHome();" href="#home" role="tab" aria-controls="home" aria-selected="true">상품정보</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" id="review-tab" data-toggle="tab" onclick="javascript:goReview();" href="#review" role="tab" aria-controls="review"aria-selected="false">리뷰</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" id="contact-tab" data-toggle="tab" onclick="javascript:goContact();" href="#contact" role="tab" aria-controls="contact" aria-selected="false">문의</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" id="profile-tab" data-toggle="tab" onclick="javascript:goProfile();" href="#profile" role="tab" aria-controls="profile" aria-selected="false">교환 및 환불 안내</a>
			</li>
		</ul>

		<div class="tab-content" id="myTabContent">
			<!--================ 상품정보 =================-->
			<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
				<p>${productVO.detail}</p>
			</div>
			<!--//================ 상품정보 =================-->
			
			<!--================ 리뷰 =================-->
			<div class="tab-pane fade" id="review" role="tabpanel" aria-labelledby="review-tab">
				<div class="row">
					<div class="col-lg-6">
						<div class="row total_rate">
							<div class="col-6">
								<div class="box_total">
									<h5>평균</h5>
									<h4>4.0</h4>
									<h6>(03 Reviews)</h6>
								</div>
							</div>
							<div class="col-6">
								<div class="rating_list">
									<h3>Based on 3 Reviews</h3>
									<ul class="list">
										<li>
											<a href="#">5 Star <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> 01 </a>
										</li>
										<li>
											<a href="#">4 Star <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> 01 </a>
										</li>
										<li>
											<a href="#">3 Star <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> 01 </a>
										</li>
										<li>
											<a href="#">2 Star <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> 01 </a>
										</li>
										<li><a href="#">1 Star <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> 01 </a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="review_list">
							<div class="review_item">
								<div class="media">
									<div class="d-flex">
										<img src="${context}/resources/img/product/single-product/review-1.png" alt="" />
									</div>
									<div class="media-body">
										<h4>Blake Ruiz</h4>
										<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i>
									</div>
								</div>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing
									elit, sed do eiusmod tempor incididunt ut labore et dolore
									magna aliqua. Ut enim ad minim veniam, quis nostrud
									exercitation ullamco laboris nisi ut aliquip ex ea commodo</p>
							</div>
							<div class="review_item">
								<div class="media">
									<div class="d-flex">
										<img src="${context}/resources/img/product/single-product/review-2.png" alt="" />
									</div>
									<div class="media-body">
										<h4>Blake Ruiz</h4>
										<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i>
									</div>
								</div>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing
									elit, sed do eiusmod tempor incididunt ut labore et dolore
									magna aliqua. Ut enim ad minim veniam, quis nostrud
									exercitation ullamco laboris nisi ut aliquip ex ea commodo</p>
							</div>
							<div class="review_item">
								<div class="media">
									<div class="d-flex">
										<img src="${context}/resources/img/product/single-product/review-3.png" alt="" />
									</div>
									<div class="media-body">
										<h4>Blake Ruiz</h4>
										<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i>
									</div>
								</div>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing
									elit, sed do eiusmod tempor incididunt ut labore et dolore
									magna aliqua. Ut enim ad minim veniam, quis nostrud
									exercitation ullamco laboris nisi ut aliquip ex ea commodo</p>
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="review_box">
							<h4>Add a Review</h4>
							<p>Your Rating:</p>
							<ul class="list">
								<li><a href="#"> <i class="fa fa-star"></i>
								</a></li>
								<li><a href="#"> <i class="fa fa-star"></i>
								</a></li>
								<li><a href="#"> <i class="fa fa-star"></i>
								</a></li>
								<li><a href="#"> <i class="fa fa-star"></i>
								</a></li>
								<li><a href="#"> <i class="fa fa-star"></i>
								</a></li>
							</ul>
							<p>Outstanding</p>
							<form class="row contact_form"  method="post" id="contactForm" novalidate="novalidate">
								<div class="col-md-12">
									<div class="form-group">
										<input type="text" class="form-control" id="name" name="name" placeholder="Your Full name" />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="email" class="form-control" id="email" name="email" placeholder="Email Address" />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="text" class="form-control" id="number" name="number" placeholder="Phone Number" />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<textarea class="form-control" name="message" id="message" rows="1" placeholder="Review"></textarea>
									</div>
								</div>
								<div class="col-md-12 text-right">
									<button type="submit" value="submit" class="btn submit_btn"> Submit Now</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!--//================ 리뷰 =================-->
			
			<!--================ 문의 =================-->
			<div class="tab-pane fade" id="contact" role="tabpanel" aria-labelledby="contact-tab">
				<div class="row">
					<div class="col-lg-6">
						<div class="comment_list">
							<div class="review_item">
								<div class="media">
									<div class="d-flex">
										<img src="${context}/resources/img/product/single-product/review-1.png" alt="" />
									</div>
									<div class="media-body">
										<h4>Blake Ruiz</h4>
										<h5>12th Feb, 2017 at 05:56 pm</h5>
										<a class="reply_btn" href="#">Reply</a>
									</div>
								</div>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing
									elit, sed do eiusmod tempor incididunt ut labore et dolore
									magna aliqua. Ut enim ad minim veniam, quis nostrud
									exercitation ullamco laboris nisi ut aliquip ex ea commodo</p>
							</div>
							<div class="review_item reply">
								<div class="media">
									<div class="d-flex">
										<img src="${context}/resources/img/product/single-product/review-2.png" alt="" />
									</div>
									<div class="media-body">
										<h4>Blake Ruiz</h4>
										<h5>12th Feb, 2017 at 05:56 pm</h5>
										<a class="reply_btn" href="#">Reply</a>
									</div>
								</div>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing
									elit, sed do eiusmod tempor incididunt ut labore et dolore
									magna aliqua. Ut enim ad minim veniam, quis nostrud
									exercitation ullamco laboris nisi ut aliquip ex ea commodo</p>
							</div>
							<div class="review_item">
								<div class="media">
									<div class="d-flex">
										<img src="${context}/resources/img/product/single-product/review-3.png" alt="" />
									</div>
									<div class="media-body">
										<h4>Blake Ruiz</h4>
										<h5>12th Feb, 2017 at 05:56 pm</h5>
										<a class="reply_btn" href="#">Reply</a>
									</div>
								</div>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing
									elit, sed do eiusmod tempor incididunt ut labore et dolore
									magna aliqua. Ut enim ad minim veniam, quis nostrud
									exercitation ullamco laboris nisi ut aliquip ex ea commodo</p>
							</div>
						</div>
					</div>
					<div class="col-lg-6">
						<div class="review_box">
							<h4>Post a comment</h4>
							<form class="row contact_form" action="contact_process.php" method="post" id="contactForm" novalidate="novalidate">
								<div class="col-md-12">
									<div class="form-group">
										<input type="text" class="form-control" id="name" name="name" placeholder="Your Full name" />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="email" class="form-control" id="email" name="email" placeholder="Email Address" />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="text" class="form-control" id="number" name="number" placeholder="Phone Number" />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<textarea class="form-control" name="message" id="message" rows="1" placeholder="Message"></textarea>
									</div>
								</div>
								<div class="col-md-12 text-right">
									<button type="submit" value="submit" class="btn submit_btn"> Submit Now</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!--//================ 문의 =================-->
			
			<!--================ 교환환불안내 =================-->
			<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
				<div class="table-responsive">
					<h4>교환 및 환불 배송 안내</h4>
					<table class="table">
						<tbody>
							<tr>
								<td>
									<h5>판매자 지정 택배사</h5>
								</td>
								<td>
									<h5>우체국 택배</h5>
								</td>
							</tr>
							<tr>
								<td>
									<h5>반품 배송비</h5>
								</td>
								<td>
									<h5>편도 4,000원 (최초 배송비 무료인 경우 8,000원 부과)</h5>
								</td>
							</tr>
							<tr>
								<td>
									<h5>교환 배송비</h5>
								</td>
								<td>
									<h5>8,000원</h5>
								</td>
							</tr>
							<tr>
								<td>
									<h5>보내실 곳</h5>
								</td>
								<td>
									<h5>(우:12345) 서울특별시 마포구 홍대입구역 123-45 6층</h5>
								</td>
							</tr>
						</tbody>
					</table>
					<br>
					<br>
					<h4>반품 및 교환 사유에 따른 요청 가능 기간</h4>
					<p>반품 시 먼저 판매자와 연락하셔서 반품사유, 택배사, 배송비, 반품지 주소 등을 협의하신 후 반품상품을 반송해주시길 바랍니다.</p>
					<p>1.구매자 단순 변심은 상품 수령 후 7일 이내(구매자 반품배송비 부담)</p>
					<p>2.표시/광고와 상이, 상품 하자의 경우 상품 수령 후 3개월 이내 혹은 표시/광고와 다른 사실을 안 날로 부터 둘 중 하나 경과 시 반품/교환 불가 (판매자 반품 배송비 부담)</p>
					<br>
					<h4>반품 및 교환 불가능 사유</h4>
					<p>아래와 같은 경우 반품/교환이 불가능합니다.</p>
					<p>1.반품요청기간이 지난 경우</p>
					<p>2.포장을 개봉하였으나 포장이 훼손되어 상품가치가 현저히 상실된 경우</p>
					<p>3.구매자의 책임 있는 사유로 상품 등이 멸실 또는 훼손된 경우(단, 상품을 확인하기 위하여 포장 등을 훼손한 경우는 제외)</p>
					<p>4.구매자의 사용 또는 일부 소비에 의하여 상품의 가치가 현저히 감소한 경우(라벨이 떨어진 경우)</p>
					<p>5.시간의 경과에 의하여 재판매가 곤란할 정도로 상품 등의 가치가 현저히 감소한 경우</p>
					<p>6.고객주문 확인 후 상품제작에 들어가는 주문제작상품(판매자에게 회복불가능한 손해가 예상되고, 그러한 예정으로 청약철회권 행사가 불가하다는 사실을 서면 동의 받은 경우)</p>
				</div>
			</div>
			<!--//================ 배송환불안내 =================-->
		</div>
	</div>
</section>
<!--================End Product Description Area =================-->
<!--================footer Area  =================-->
<jsp:include page="/main/footer.jsp"></jsp:include>

	<script type="text/javascript">
	
	//결제
	$("#pay").on("click",function(){
		//alert("결제 바로가기");
		//var optSelect = $("#optSelect").val();
		//var oPrice = $("#oPrice").val();
		
		  var cartCnt = $("#sst").val();
		  var oNum = $("#optSelect").val();
		  //var oPrice = $("#optSelect option:checked").text();//oPrice[행사] 01.삼나무 미니싱글(XS) 600 원목 깔판 | 14400원
		  
/* 		  var idx = oPrice.split("|");
		  for(var i in idx){
			  var result = document.write(idx[i]);
		  } */
		  
		  if(confirm("바로 결제 하시겠습니까?") == false) return;
		  
		  console.log("cartCnt: "+ cartCnt);
		  console.log("oNum: "+ oNum);
		  //console.log("oPrice: "+ oPrice);
		  //alert("oPrice"+oPrice);
		  //alert("result"+result);
		  
		  var frm = document.payFrm;
		  frm.cartCnt.value=cartCnt;
		  frm.oNum.value=oNum;
		  
		  frm.action = "${context}/pay/direct_pay.do";
	   	  frm.submit();
	});
		
		
/*  		 $.ajax({
               type:"GET",
               url:"${context}/pay/direct_pay.do",
               dataType:"html",
               data:{
            	   productCnt : sst,
            	   productPrice : bPrice,
            	   discount : discount,
            	   delivery : dPrice,
            	   //option : oPrice
              }, 
            success: function(result){
            	location.href="${context}/pay/direct_pay.jsp";
            },
            error:function(){
                alert("결제창 이동 실패");
            }
           }); 
           //--ajax  */
	});
	
		//장바구니
		$("#cart").on("click",function(){
			//alert("장바구니 담으러!");
	
			var optSelect = $("#optSelect").val();
			var sst = $("#sst").val();
			
			if(confirm("장바구니 담으시겠습니까?") == false) return;
			
			console.log("optSelect: "+ optSelect);
			console.log("cartCnt: "+ sst);
			
			$.ajax({
	               type:"POST",
	               url:"${context}/cart/do_save.do",
	               dataType:"html",
	               data:{
	            	   "oNum" : optSelect,
	            	   "cartCnt" : sst
	              }, 
	            success: function(result){
	
	            	if(result == 1){
	            		alert("카트 담기 성공");
	            		location.href="${context}/cart/get_retrieve.do";
	            	}else{
	            		alert("회원만 사용할 수 있습니다");
	            	}
	            },
	            error:function(){
	                alert("카트 담기 실패");
	            }
	           }); 
	           //--ajax  
		});
		
		//좋아요
		$("#good").on("click",function(){
			//alert("장바구니 담으러!");
	
			var pNum = $("#pNum").val();
			
			if(confirm("좋아요 하시겠습니까?") == false) return;
			
			console.log("pNum: "+ pNum);
			
			$.ajax({
	               type:"POST",
	               url:"${context}/good/do_save.do",
	               dataType:"html",
	               data:{
	            	   "pNum" : pNum,
	              }, 
	            success: function(result){
	
	            	if(result == 1){
	            		alert("좋아요 성공");
	            		location.href="${context}/good/get_retrieve.do";
	            	}else{
	            		alert("회원만 사용할 수 있습니다");
	            	}
	            },
	            error:function(){
	                alert("좋아요 실패");
	            }
	           }); 
	           //--ajax  
		});
		
		//홈 탭
		function goHome(){
			//alert("goHome");
			$("#review").removeClass("show active");
			$("#contact").removeClass("show active");
			$("#profile").removeClass("show active");
			$("#home").addClass("show active");
		}
		//리뷰 탭
		function goReview(){
			//alert("goReview");
			$("#home").removeClass("show active");
			$("#contact").removeClass("show active");
			$("#profile").removeClass("show active");
			$("#review").addClass("show active");
		}
		//문의 탭
		function goContact(){
			//alert("goContact");
			$("#home").removeClass("show active");
			$("#review").removeClass("show active");
			$("#profile").removeClass("show active");
			$("#contact").addClass("show active");
		}
		//정보 탭
		function goProfile(){
			//alert("goProfile");
			$("#home").removeClass("show active");
			$("#review").removeClass("show active");
			$("#contact").removeClass("show active");
			$("#profile").addClass("show active");
		}
		//수량 Up
		function countUp(){
			var result = document.getElementById('sst');
			var sst = result.value;
			if(!isNaN( sst )){
				result.value++;
				return false;
			}
		}
		//수량Down
		function countDown(){
			var result = document.getElementById('sst');
			var sst = result.value;
			if( !isNaN(sst) && sst > 1 ){
				result.value--;
				return false;
			}
		}
		//상품 수정
		function update_product(){
			//alert("update_product");
			$.ajax({
				type : "POST",
				url : "${context}/product/do_update.do",
				dataType : "html",
				data : {
					"pNum" : $("#pNum").val()
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						//alert(jData.msgMsg);
						location.href = "${context}/product/product_mng.jsp";
					} else {
						alert("로딩 실패");
					}
				},
				complete : function(data) {
				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
		}
		//상품 삭제
		function delete_product(){
			//alert("delete_product");
			$.ajax({
				type : "POST",
				url : "${context}/product/do_delete.do",
				dataType : "html",
				data : {
					"pNum" : $("#pNum").val()
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/product/get_retrieve.do";
					} else {
						alert("로딩 실패");
					}
				},
				complete : function(data) {
				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
		}
		$(document).ready( function(){
			//캐러셀
			$("#carousel div:first").addClass("active").show();
			
			//탭 변경 시 active 설정 (ul-li-a)
			$("#myTab li:first a").addClass("active").show();
			$("#myTab li").on("click",function(){
				//alert("tab_click");
				$("#myTab li a").removeClass("active");
				$(this).children('a').addClass("active");
			});
			
		});
	
	
	</script>
</body>
</html>