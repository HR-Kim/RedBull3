<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>결제 완료</title>
 	<style>
		.my-box { 
			border:1px solid; 
			padding:10px; 
			border:2px solid blue; 
			padding:100px;
			margin:100 100 100 100;
			width: 600px;
			height: 80%;
			top: 20px;
			left: 35%;
			bottom: 20%;
			text-align: center;
			position: relative;
			border-color: DodgerBlue;

			}
	</style>

</head>
<body>
 	<jsp:include page="/main/header.jsp"></jsp:include>


		<div class="my-box">
		결제가 완료되었습니다:)<br/>
		<a class="main_btn" href="${context}/pay/get_payRetrieve.do">주문확인하기</a>
		</div>

   	<jsp:include page="/main/footer.jsp"></jsp:include>
</body>
</html>