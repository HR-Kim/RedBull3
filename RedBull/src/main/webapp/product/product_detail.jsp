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
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
	.carousel-control.left, .carousel-control.right {
	  left: 0;
	  z-index: 1;
	}
</style>
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>상품 상세</title>
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
<!--================Form Group - Product==============-->
<form method="post" >
	<input type="hidden" name="pNum" value="${productVO.pNum}"/>	
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
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img class="d-block w-100"
									src="${context}/resources/img/product/single-product/product-1.PNG"
									alt="First slide" />
							</div>
	
							<div class="carousel-item">
								<img class="d-block w-100"
									src="${context}/resources/img/product/single-product/product-2.PNG"
									alt="Second slide" />
							</div>
	
							<div class="carousel-item">
								<img class="d-block w-100"
									src="${context}/resources/img/product/single-product/product-3.PNG"
									alt="Third slide" />
							</div>
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
					<h2>${productVO.bPrice} 원</h2>
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
						<a class="main_btn" href="#">주문하기</a>
						<a class="icon_btn" href="#">
							<i class="lnr lnr lnr-heart"></i>
						</a>
						<hr>
						<a class="main_btn" href="javascript:write_product();">수정하기</a>
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
				<a class="nav-link" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">상품정보</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" id="review-tab" data-toggle="tab" href="#review" role="tab" aria-controls="review"aria-selected="false">리뷰</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" id="contact-tab" data-toggle="tab" href="#contact" role="tab" aria-controls="contact" aria-selected="false">문의</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">교환 및 환불 안내</a>
			</li>
		</ul>

		<div class="tab-content" id="myTabContent">
			<!--================ 상품정보 =================-->
			<div class="tab-pane fade in active" id="home" role="tabpanel" aria-labelledby="home-tab">
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
									<h5>Overall</h5>
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


	<!-- Optional JavaScript -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	<script src="${context}/resources/vendors/lightbox/simpleLightbox.min.js"></script>
	<script src="${context}/resources/vendors/isotope/imagesloaded.pkgd.min.js"></script>
	<script src="${context}/resources/vendors/isotope/isotope-min.js"></script>
	<script type="text/javascript">
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
			if( !isNaN(sst) && sst > 0 ){
				result.value--;
				return false;
			}
		}
		//상품 수정
		function write_product(){
			alert("write_product");
		}
		//상품 삭제
		function delete_product(){
			alert("delete_product");
		}
		//탭 변경 시 active 설정 (ul-li-a)
		$("#myTab li:first a").addClass("active").show();
		$("#myTab li").on("click",function(){
			alert("tab_click");
			$("#myTab li a").removeClass("active");
			$(this).children('a').addClass("active");
		});
	
	
	</script>
</body>
</html>