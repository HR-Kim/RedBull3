<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>상품 상세</title>
<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">

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
					<h2>Product Details</h2>
					<p>Very us move be blessed multiply night</p>
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
					<div id="myCarousel" class="carousel slide" data-ride="carousel">
						<!-- Indicators -->
						<ol class="carousel-indicators">
							<!-- 이미지 제한 사이즈 : 60X60  -->
							<li data-target="#myCarousel" data-slide-to="0" class="active">
								<img class="d-block w-100" src="${context}/resources/img/product/single-product/product-small-1.PNG" />
							</li>
							<li data-target="#myCarousel" data-slide-to="1">
								<img class="d-block w-100" src="${context}/resources/img/product/single-product/product-small-2.PNG" />
							</li>
							<li data-target="#myCarousel" data-slide-to="2">
								<img class="d-block w-100" src="${context}/resources/img/product/single-product/product-small-3.PNG" />
							</li>
						</ol>

						<!-- Wrapper for slides -->
						<div class="carousel-inner">
							<div class="item active">
								<img class="d-block w-100"
									src="${context}/resources/img/product/single-product/product-1.PNG"
									alt="First slide" />
							</div>

							<div class="item">
								<img class="d-block w-100"
									src="${context}/resources/img/product/single-product/product-2.PNG"
									alt="Second slide" />
							</div>

							<div class="item">
								<img class="d-block w-100"
									src="${context}/resources/img/product/single-product/product-3.PNG"
									alt="Third slide" />
							</div>
						</div>

						<!-- Left and right controls -->
						<a class="left carousel-control" href="#myCarousel"
							data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="right carousel-control" href="#myCarousel"
							data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span> <span
							class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>
			<div class="col-lg-5 offset-lg-1">
				<div class="s_product_text">
					<h3>Faded SkyBlu Denim Jeans</h3>
					<h2>$149.99</h2>
					<ul class="list">
						<li>
							<a class="active" href="#"><span>Category</span> :	Household</a>
						</li>
						<li>
							<a href="#"> <span>Availibility</span> : In Stock</a>
						</li>
					</ul>
					<p>Mill Oil is an innovative oil filled radiator with the most
						modern technology. If you are looking for something that can make
						your interior look awesome, and at the same time give you the
						pleasant warm feeling during the winter.
					</p>
					<div class="product_count">
						<label for="qty">Quantity:</label> <input type="text" name="qty" id="sst" maxlength="12" value="1" title="Quantity:" class="input-text qty" />
						<button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst )) result.value++;return false;" class="increase items-count" type="button">
							<i class="lnr lnr-chevron-up"></i>
						</button>
						<button onclick="var result = document.getElementById('sst'); var sst = result.value; if( !isNaN( sst ) &amp;&amp; sst > 0 ) result.value--;return false;" class="reduced items-count" type="button">
							<i class="lnr lnr-chevron-down"></i>
						</button>
					</div>
					<div class="card_area">
						<a class="main_btn" href="#">Add to Cart</a> <a class="icon_btn" href="#">
							<i class="lnr lnr lnr-diamond"></i>
						</a>
						<a class="icon_btn" href="#">
							<i class="lnr lnr lnr-heart"></i>
						</a>
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
			<li class="nav-item active">
				<a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Description</a></li>
			<li class="nav-item"><a class="nav-link" id="profile-tab"
				data-toggle="tab" href="#profile" role="tab"
				aria-controls="profile" aria-selected="false">Specification</a></li>
			<li class="nav-item"><a class="nav-link" id="contact-tab"
				data-toggle="tab" href="#contact" role="tab"
				aria-controls="contact" aria-selected="false">Comments</a></li>
			<li class="nav-item"><a class="nav-link" id="review-tab"
				data-toggle="tab" href="#review" role="tab" aria-controls="review"
				aria-selected="false">Reviews</a></li>
		</ul>

		<div class="tab-content" id="myTabContent">
			<!--================ Description =================-->
			<div class="tab-pane fade in active" id="home" role="tabpanel"
				aria-labelledby="home-tab">
				<p>It is often frustrating to attempt to plan meals that are
					designed for one. Despite this fact, we are seeing more and more
					recipe books and Internet websites that are dedicated to the act
					of cooking for one. Divorce and the death of spouses or grown
					children leaving for college are all reasons that someone
					accustomed to cooking for more than one would suddenly need to
					learn how to adjust all the cooking practices utilized before into
					a streamlined plan of cooking that is more efficient for one
					person creating less
				</p>
			</div>
			<!--//================ Description =================-->

			<!--================ info =================-->
			<div class="tab-pane fade" id="profile" role="tabpanel"
				aria-labelledby="profile-tab">
				<div class="table-responsive">
					<table class="table">
						<tbody>
							<tr>
								<td>
									<h5>Width</h5>
								</td>
								<td>
									<h5>128mm</h5>
								</td>
							</tr>
							<tr>
								<td>
									<h5>Height</h5>
								</td>
								<td>
									<h5>508mm</h5>
								</td>
							</tr>
							<tr>
								<td>
									<h5>Depth</h5>
								</td>
								<td>
									<h5>85mm</h5>
								</td>
							</tr>
							<tr>
								<td>
									<h5>Weight</h5>
								</td>
								<td>
									<h5>52gm</h5>
								</td>
							</tr>
							<tr>
								<td>
									<h5>Quality checking</h5>
								</td>
								<td>
									<h5>yes</h5>
								</td>
							</tr>
							<tr>
								<td>
									<h5>Freshness Duration</h5>
								</td>
								<td>
									<h5>03days</h5>
								</td>
							</tr>
							<tr>
								<td>
									<h5>When packeting</h5>
								</td>
								<td>
									<h5>Without touch of hand</h5>
								</td>
							</tr>
							<tr>
								<td>
									<h5>Each Box contains</h5>
								</td>
								<td>
									<h5>60pcs</h5>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!--//================ info =================-->

			<!--================ comments =================-->
			<div class="tab-pane fade" id="contact" role="tabpanel"
				aria-labelledby="contact-tab">
				<div class="row">
					<div class="col-lg-6">
						<div class="comment_list">
							<div class="review_item">
								<div class="media">
									<div class="d-flex">
										<img
											src="${context}/resources/img/product/single-product/review-1.png"
											alt="" />
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
										<img
											src="${context}/resources/img/product/single-product/review-2.png"
											alt="" />
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
										<img
											src="${context}/resources/img/product/single-product/review-3.png"
											alt="" />
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
							<form class="row contact_form" action="contact_process.php"
								method="post" id="contactForm" novalidate="novalidate">
								<div class="col-md-12">
									<div class="form-group">
										<input type="text" class="form-control" id="name" name="name"
											placeholder="Your Full name" />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="email" class="form-control" id="email"
											name="email" placeholder="Email Address" />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="text" class="form-control" id="number"
											name="number" placeholder="Phone Number" />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<textarea class="form-control" name="message" id="message"
											rows="1" placeholder="Message"></textarea>
									</div>
								</div>
								<div class="col-md-12 text-right">
									<button type="submit" value="submit" class="btn submit_btn">
										Submit Now</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!--//================ comments =================-->

			<!--================ review =================-->
			<div class="tab-pane fade" id="review" role="tabpanel"
				aria-labelledby="review-tab">
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
										<li><a href="#">5 Star <i class="fa fa-star"></i> <i
												class="fa fa-star"></i> <i class="fa fa-star"></i> <i
												class="fa fa-star"></i> <i class="fa fa-star"></i> 01
										</a></li>
										<li><a href="#">4 Star <i class="fa fa-star"></i> <i
												class="fa fa-star"></i> <i class="fa fa-star"></i> <i
												class="fa fa-star"></i> <i class="fa fa-star"></i> 01
										</a></li>
										<li><a href="#">3 Star <i class="fa fa-star"></i> <i
												class="fa fa-star"></i> <i class="fa fa-star"></i> <i
												class="fa fa-star"></i> <i class="fa fa-star"></i> 01
										</a></li>
										<li><a href="#">2 Star <i class="fa fa-star"></i> <i
												class="fa fa-star"></i> <i class="fa fa-star"></i> <i
												class="fa fa-star"></i> <i class="fa fa-star"></i> 01
										</a></li>
										<li><a href="#">1 Star <i class="fa fa-star"></i> <i
												class="fa fa-star"></i> <i class="fa fa-star"></i> <i
												class="fa fa-star"></i> <i class="fa fa-star"></i> 01
										</a></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="review_list">
							<div class="review_item">
								<div class="media">
									<div class="d-flex">
										<img
											src="${context}/resources/img/product/single-product/review-1.png"
											alt="" />
									</div>
									<div class="media-body">
										<h4>Blake Ruiz</h4>
										<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i>
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
										<img
											src="${context}/resources/img/product/single-product/review-2.png"
											alt="" />
									</div>
									<div class="media-body">
										<h4>Blake Ruiz</h4>
										<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i>
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
										<img
											src="${context}/resources/img/product/single-product/review-3.png"
											alt="" />
									</div>
									<div class="media-body">
										<h4>Blake Ruiz</h4>
										<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i> <i class="fa fa-star"></i> <i
											class="fa fa-star"></i>
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
							<form class="row contact_form" action="contact_process.php"
								method="post" id="contactForm" novalidate="novalidate">
								<div class="col-md-12">
									<div class="form-group">
										<input type="text" class="form-control" id="name" name="name"
											placeholder="Your Full name" />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="email" class="form-control" id="email"
											name="email" placeholder="Email Address" />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="text" class="form-control" id="number"
											name="number" placeholder="Phone Number" />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<textarea class="form-control" name="message" id="message"
											rows="1" placeholder="Review"></textarea>
									</div>
								</div>
								<div class="col-md-12 text-right">
									<button type="submit" value="submit" class="btn submit_btn">
										Submit Now</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!--//================ review =================-->
		</div>
	</div>
</section>
<!--================End Product Description Area =================-->


	<!-- Optional JavaScript -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>
	
	<script src="${context}/resources/js/stellar.js"></script>
	<script src="${context}/resources/vendors/lightbox/simpleLightbox.min.js"></script>
	<script src="${context}/resources/vendors/isotope/imagesloaded.pkgd.min.js"></script>
	<script src="${context}/resources/vendors/isotope/isotope-min.js"></script>
	<script src="${context}/resources/js/mail-script.js"></script>
	<script src="${context}/resources/js/theme.js"></script>
</body>
</html>