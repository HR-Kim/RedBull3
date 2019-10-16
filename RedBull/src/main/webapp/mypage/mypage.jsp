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
<body>
	 <!-- Tab links -->
	<div class="tab">
	  <button class="tablinks" onclick="openCity(event, 'shopping')">나의 쇼핑</button>
	  <button class="tablinks" onclick="openCity(event, 'active')">나의 활동</button>
	  <button class="tablinks" onclick="openCity(event, 'update')">회원정보수정</button>
	</div>
	
	<!-- Tab content -->
	<div id="shopping" class="tabcontent">
	  <h3>쇼핑</h3>
	</div>
	
	<div id="active" class="tabcontent">
	  <div class="tab">
		  <button class="tablinks" onclick="openCity(event, 'good')">좋아요</button>
		  <button class="tablinks" onclick="openCity(event, 'question')">질문</button>
		  <button class="tablinks" onclick="openCity(event, 'answer')">답변</button>
		</div>
	</div>
	
	<div id="update" class="tabcontent">
	  <h3>회원정보수정</h3>
	</div>
	
	<div id="good" class="tabcontent">
	  <h3>좋아요</h3>
	</div>
	
	<div id="question" class="tabcontent">
	  <h3>질문</h3>
	</div>
	
	<div id="answer" class="tabcontent">
	  <h3>답변</h3>
	</div>
    
    <script>
    function openPage(evt, pageName) {
    	  // Declare all variables
    	  var i, tabcontent, tablinks;

    	  // Get all elements with class="tabcontent" and hide them
    	  tabcontent = document.getElementsByClassName("tabcontent");
    	  for (i = 0; i < tabcontent.length; i++) {
    	    tabcontent[i].style.display = "none";
    	  }

    	  // Get all elements with class="tablinks" and remove the class "active"
    	  tablinks = document.getElementsByClassName("tablinks");
    	  for (i = 0; i < tablinks.length; i++) {
    	    tablinks[i].className = tablinks[i].className.replace("active", "");
    	  }

    	  // Show the current tab, and add an "active" class to the button that opened the tab
    	  document.getElementById(pageName).style.display = "block";
    	  evt.currentTarget.className += " active";
    	}
    </script>
</body>
</html>