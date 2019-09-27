<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%  String context = request.getContextPath(); %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File</title>

<script src="<%=context%>/resources/js/jquery-1.12.4.js"></script>

</head>
<body>
	<h3>File Upload</h3>
	<hr/>
	<form action="do_save.do" method="post" enctype="multipart/form-data">
		파일1:<input type="file" name="file01" /><br/>
		파일2:<input type="file" name="file02" /><br/>
		작업구분:<input type="text" name="work_div" size="15"><br/>
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