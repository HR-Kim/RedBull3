<%@page import="kr.co.redbull.cmn.Search"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath }" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%

pageContext.setAttribute("cn", "\n"); //Enter
pageContext.setAttribute("br", "<br/>"); //br 태그

/* 	Search search = (Search)request.getAttribute("searchVO");
	
	String searchDiv = "20";
	
	if(null!=search.getSearchDiv()) {
		searchDiv = search.getSearchDiv();
	}else{
		searchDiv = "20";
	}
 */
%>
<%--
  /**
  * @Class Name : form_template.jsp
  * @Description : Sample Register 화면
  * @Modification Information
  *
  *   수정일                   수정자                      수정내용
  *  -------    --------    ---------------------------
  *  2018.04.26            최초 생성
  *
  * author SIST 개발팀
  * since 2018.04.26
  *
  * Copyright (C) 2009 by KandJang  All right reserved.
  */
--%>

<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>게시관리</title>

<!-- 부트스트랩 -->
<link href="${context}/resources/css/bootstrap.min.css" rel="stylesheet">

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>
${board }
${search }
	<!-- div container -->
	<div class="container">
		<!-- div title -->
		<div class="page-header">
			<h1></h1>
		</div>
		<!--// div title -->

		<!-- div title -->
		<form class="form-horizontal" name="boardEditFrm" id="boardEditFrm"
			method="post" action="do_update.do">
			<input type="hidden" class="form-control" name="bNum" id="bNum"
				value="${board.bNum}" /><br/>
			
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">제목</label>
				<div class="col-sm-6">
					<input type="text" class="form-control" name="title" id="title"
						placeholder="제목" value="<c:out value='${board.title }' />">
				</div>
				<div class="col-sm-2">
					<select name="category" id="category" class="form-control input-sm" >
						<option selected disabled hidden>==선택하세요==</option>
						<option class="opt" value="10">공지</option>
						<option class="opt" value="20">질답카테고리01</option>
						<option class="opt" value="20">질답카테고리02</option>
						<option class="opt" value="20">질답카테고리03</option>
						<option class="opt" value="20">질답카테고리04</option>
	     			</select>
				</div>	
			</div>		
			<div class="form-group">
				<div style="width:800px; background-color: #FAFAFA" id="contents" CONTENTEDITABLE>
					${fn:replace(board.contents, cn, br)}
				</div>
			</div>
						<%-- <div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">내용</label>
				<div class="col-sm-8">
					<textarea class="form-control" name="contents" id="contents"
						rows="7" placeholder="내용"><c:out
							value="${board.contents }" /></textarea>
				</div>
			</div> --%>
			
			<div class="form-group" style="display: none;">
				<label for="inputEmail3" class="col-sm-2 control-label">등록자
					아이디</label>
				<div class="col-sm-8">
					<input type="text" class="form-control" name="regId" id="regId"
						placeholder="등록자 아이디" value="<c:out value='${board.regId }' />"
						disabled="disabled">
				</div>
			</div>
		</form>
			<!-- Button Area -->
		<div class="row">
			<div class="col-lg-10 col-sm-10 col-xs-10">
				<div class="text-right">
					<button type="button" class="btn btn-default btn-sm"
						id="doRetrieve">목록</button>
					<button type="button" class="btn btn-default btn-sm" id="doInit">초기화</button>
					<button type="button" class="btn btn-default btn-sm" id="doSave">등록</button>
					<button type="button" class="btn btn-default btn-sm" id="doUpdate">수정</button>
					<button type="button" class="btn btn-default btn-sm" id="doDelete">삭제</button>
				</div>
			</div>
		</div>
		<div class="col-lg-10"></div>
	</div>
	

	
	<!--// div container -->
	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script src="${context}/resources/js/jquery-1.12.4.js"></script>
	
	<!-- jQuery validate -->
	<script src="${context}/resources/js/jquery.validate.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="${context}/resources/js/bootstrap.min.js"></script>


	<script type="text/javascript">
	
		//목록
		$("#doRetrieve").on("click", function() {
			if (confirm("목록으로 이동 하시겠습니까?") == false)return;
			
			var tnum = ${search.searchDiv};
	
			location.href = "${context}/board/get_retrieve.do?searchDiv="+tnum;
		});

		//초기화
		$("#doInit").on("click", function() {
			$("#boardId").val("")
			$("#title").val("");
			$("#contents").val("");
			$("#regId").val("");
			$("#readCnt").val("");
			$("#regDt").val("")

			//버튼제어:등록,수정,삭제
			$("#doUpdate").prop("disabled", true);
			$("#doDelete").prop("disabled", true);
			$("#doSave").prop("disabled", false);

			$("#regId").prop("disabled", false);

		});
		
		//등록	    
		$("#doSave").on("click", function() {
			
			var target = document.getElementById("category");
			var tNum = target.options[target.selectedIndex].value;
			var category = target.options[target.selectedIndex].text;
			
			//validation
			if($("#boardEditFrm").valid()==false)return;
			
			if (confirm("등록 하시겠습니까?") == false)
				return;
			
			$.ajax({
				type : "POST",
				url : "${context}/board/do_save.do",
				dataType : "html",
				data : {
					"title" : $("#title").val(),
					"contents" : $("#contents").val(),
					"tNum":tNum,
					"category" : category,
					"regId" : $("#regId").val()
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/board/get_retrieve.do?searchDiv="+tNum;

					} else {
						alert(jData.msgId + "|" + jData.msgMsg);
					}
				},
				complete : function(data) {

				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
			//--ajax  

		});

		//수정:submit->control->board_mng.jsp: (성공)?board_list.jsp:board_mng.jsp
		//삭제:submit->
		$("#doUpdate").on("click", function() {
			
			var target = document.getElementById("category");
			var tNum = target.options[target.selectedIndex].value;
			var category = target.options[target.selectedIndex].text;		
			
			//validation
			if($("#boardEditFrm").valid()==false)return;
			
			
			console.log("bNum:" + $("#bNum").val());
			if (confirm("수정 하시겠습니까?") == false)
				return;

			$.ajax({
				type : "GET",
				url : "${context}/board/do_update.do",
				dataType : "html",
				data : {
					"bNum":$("#bNum").val(),
					"title" : $("#title").val(),
					"contents" : $("#contents").text(),
					"tNum":tNum,
					"category" : category,
					"regId" : $("#regId").val()
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/board/get_retrieve.do?searchDiv="+tNum;

					} else {
						alert(jData.msgId + "|" + jData.msgMsg);
					}
				},
				complete : function(data) {

				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
			//--ajax  

		});

		//삭제:submit->
		$("#doDelete").on("click", function() {
			//validation
			console.log("boardId:" + $("#boardId").val());
			if (confirm("삭제 하시겠습니까?") == false)
				return;

			$.ajax({
				type : "POST",
				url : "${context}/board/do_delete.do",
				dataType : "html",
				data : {
					"boardId" : $("#boardId").val()
				},
				success : function(data) {
					var jData = JSON.parse(data);
					if (null != jData && jData.msgId == "1") {
						alert(jData.msgMsg);
						location.href = "${context}/board/get_retrieve.do";

					} else {
						alert(jData.msgId + "|" + jData.msgMsg);
					}
				},
				complete : function(data) {

				},
				error : function(xhr, status, error) {
					alert("error:" + error);
				}
			});
			//--ajax  

		});

		$(document).ready(function() {
			
			var bNum = ${board.bNum};
			
			//console.log(bNum);
			
			if(null==bNum || "0"==bNum){
				document.getElementById("doUpdate").style.display="none";
				document.getElementById("doDelete").style.display="none";
				
			}else if(null!=bNum || 0!=bNum){
				document.getElementById("doSave").style.display="none";
			}			
			
			//form validate
			$("#boardEditFrm").validate({
				rules: {					
					title: {
						required: true,
						minlength: 2,
						maxlength: 100
					},
					contents: {
						required: true,
						minlength: 2,
						maxlength: 1000000
					},
					category: {
						required: true
					}
				},
				messages: {
					title: {
						required: "제목을 입력 하세요.",
						minlength: $.validator.format("{0}자 이상 입력 하세요."),
						maxlength: $.validator.format("{0}자 내로 입력 하세요.")
					},
					contents: {
						required: "내용을 입력 하세요.",
						minlength: $.validator.format("{0}자 이상 입력 하세요."),
						maxlength: $.validator.format("{0}자 내로 입력 하세요.")
					},
					category: {
						required: "카테고리를 선택하세요."
					}
				},
				errorPlacement : function(error, element) {
				     //do nothing
				    },
				    invalidHandler : function(form, validator) {
				     var errors = validator.numberOfInvalids();
				     if (errors) {
				      alert(validator.errorList[0].message);
				      validator.errorList[0].element.focus();
				     }
				}

			});			
		});
	</script>
</body>
</html>