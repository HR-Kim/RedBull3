<%@page import="kr.co.redbull.user.service.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	User user = (User)session.getAttribute("user");
%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
	/* Style the tab */
	.tab {
	  overflow: hidden;
	  border: 1px solid #ccc;
	  background-color: #f1f1f1;
	}
	
	/* Style the buttons that are used to open the tab content */
	.tab button {
	  background-color: inherit;
	  float: left;
	  border: none;
	  outline: none;
	  cursor: pointer;
	  padding: 14px 16px;
	  transition: 0.3s;
	}
	
	/* Change background color of buttons on hover */
	.tab button:hover {
	  background-color: #ddd;
	}
	
	/* Create an active/current tablink class */
	.tab button.active {
	  background-color: #ccc;
	}
	
	/* Style the tab content */
	.tabcontent {
	  display: none;
	  padding: 6px 12px;
	  border: 1px solid #ccc;
	  border-top: none;
	}
</style>
<title>마이 페이지</title>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="${context}/resources/css/bootstrap.css" />
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
	<!--================Header Menu Area =================-->
 	<jsp:include page="/main/header.jsp"></jsp:include>
    <!--================Header Menu Area =================-->
<body>
	 <!-- Tab links -->
	<div class="tab">
	  <button class="tablinks" onclick="openCity(event, 'update')">
	  	<a href="${context}/user/get_updateForm.do"/>
	  	회원정보수정
	  </button>
	  <button class="tablinks" onclick="openCity(event, 'shopping')">
	  	<a href="${context}/pay/get_payRetrieve.do"/>
	  	나의 쇼핑
	  </button>
	  <button class="tablinks" onclick="openCity(event, 'good')">
	  	<a href="${context}/good/get_retrieve.do"/>
	  	나의 좋아요
	  </button>
	  <button class="tablinks" onclick="openCity(event, 'qa')">
	  	<a href="${context}/myboard/get_aRetrieve.do"/>
	  	나의 질문
	  </button>
	</div>
	
    <!--================ start footer Area  =================-->
    	<jsp:include page="/main/footer.jsp"></jsp:include>
    <!--================ End footer Area  =================-->
</body>
</html>