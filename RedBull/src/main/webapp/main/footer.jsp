<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
     
<html lang="ko">  
<body>

  <!--================ start footer Area  =================-->
  <footer class="footer-area section_gap">
    <div class="container">
      <div class="row">
      
        <div class="col-lg-2 col-md-6 single-footer-widget">
          <ul>
          	<h4>고객센터</h4>
            <li><a>02-336-8546</a></li>
            <li><a>평일 10:00~17:00</a></li>
            <li><a>(점심시간 12:00~13:00 / 주말&공휴일 제외)</a></li>
          </ul>
        </div>
        
        <div class="col-lg-2 col-md-6 single-footer-widget">
          <ul>
          	<h4>이메일</h4>
            <li><a>abc@xyz.com</a></li>
          </ul>
        </div>
        
        <div class="col-lg-2 col-md-6 single-footer-widget">
          <ul>
          	<h4>대표이사</h4>
            <li><a>홍길동</a></li>
          </ul>
        </div>
        
        <div class="col-lg-2 col-md-6 single-footer-widget">
          <ul>
          	<h4>주소</h4>
            <li><a>서울특별시 마포구 서교동 447-5 풍성빌딩</a></li>
          </ul>
        </div>
        
        <div class="col-lg-4 col-md-6 single-footer-widget">
          <h4>뉴스레터</h4>
          <p>할인정보, 신상품정보를 뉴스레터로 보내드립니다.</p>
          <div class="form-wrap" id="mc_embed_signup">
            <form target="_blank" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
              method="get" class="form-inline">
              <input class="form-control" name="EMAIL" placeholder="이메일 주소" onfocus="this.placeholder = ''"
                onblur="this.placeholder = 'Your Email Address '" required="" type="email">
              <button class="click-btn btn btn-default">구독하기</button>
              <div style="position: absolute; left: -5000px;">
                <input name="b_36c4fd991d266f23781ded980_aefe40901a" tabindex="-1" value="" type="text">
              </div>

              <div class="info"></div>
            </form>
          </div>
        </div>
      </div>
      <div class="footer-bottom row align-items-center">
        <p class="footer-text m-0 col-lg-8 col-md-12"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
		Copyright &copy;<script>document.write(new Date().getFullYear());</script> by 집순이쇼핑몰 CORP. All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
		
		<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
        <div class="col-lg-4 col-md-12 footer-social">
          <a href="#"><i class="fa fa-facebook"></i></a>
          <a href="#"><i class="fa fa-twitter"></i></a>
          <a href="#"><i class="fa fa-dribbble"></i></a>
          <a href="#"><i class="fa fa-behance"></i></a>
        </div>
      </div>
    </div>
  </footer>
  <!--================ End footer Area  =================-->
  
  <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  
  <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
  <script src="${context}/resources/js/bootstrap.min.js"></script>   
  
</body>
</html>