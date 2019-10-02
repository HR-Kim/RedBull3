<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="context" value="${pageContext.request.contextPath}" />    
<%	
	//서버에 저장
	//String uploadPath = request.getRealPath(request.getContextPath());
	//uploadPath = uploadPath+"/resources/img/product";
	
	//git에 저장
	String uploadPath = "git/RedBull3/RedBull/src/main/webapp/resources/img/product";
	String user =  System.getProperty("user.home");
	uploadPath = user+File.separator+uploadPath;
	out.print(uploadPath);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>상품 상세</title>
<!-- 부트스트랩 -->
<title>File</title>
</head>
<body>
	<h3>File Upload</h3>
	<hr/>
	<form action="do_save.do" method="post" enctype="multipart/form-data">
		파일1:<input type="file" name="file01" /><br/>
		파일2:<input type="file" name="file02" /><br/>
		<input type="hidden" name="root_path" value=<%=uploadPath%> size="15"><br/>
		<input type="submit" value="전송" />	
	</form>
	${fileList}
	
	<br/>
	<br/>
	<h3>File Download</h3>
	<hr/>
	<form name="fileDown" id="fileDown" 
	       method="post" action="download.do">
	       <input type="text" name="orgFileNm" id="orgFileNm">
	       <input type="text" name="saveFileNm" id="saveFileNm">
	</form>
	
	<table border="1" id="listTable">
		<thead>
			<tr>
				<td>원본파일명</td>
				<td>저장파일명</td>
				<td>사이즈</td>
				<td>확장자</td>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fileList.size()>0}">
					<c:forEach var="vo" items="${fileList}">
						<tr>
							<td><c:out value="${vo.orgFileNm}"/></td>
							<td><c:out value="${vo.saveFileNm}"/></td>
							<td><c:out value="${vo.fileSize}"/></td>
							<td><c:out value="${vo.ext}"/></td>
						</tr>
					</c:forEach>		
				</c:when>
				<c:otherwise>
					<tr><td colspan="99"></td></tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
<script src="${context}/resources/js/jquery-1.12.4.js"></script>
<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
<script src="${context}/resources/js/bootstrap.min.js"></script>	
<script type="text/javascript">
	

	$("#listTable>tbody").on("click","tr",function(){
		//console.log("listTable");
		var tr = $(this);
		var tdArray = tr.children();
		
		var orgFileNm  = tdArray.eq(0).text();
		var saveFileNm = tdArray.eq(1).text();
		console.log("orgFileNm:"+orgFileNm);
		console.log("saveFileNm:"+saveFileNm);
		
		if(confirm("다운로드 하시겠습니까?")==false)return;
		
		var frm = document.fileDown;
		frm.orgFileNm.value = orgFileNm;
		frm.saveFileNm.value= saveFileNm;
		frm.submit();
		
	});

	$(document).ready(function(){
		console.log("ready");		
	});
</script>	
</body>
</html>