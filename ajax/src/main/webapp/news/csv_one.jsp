<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- csv_two.jsp 문서를 AJAX 기능으로 요청하여 CSV 형식의 텍스트 데이타로 응답 받아 태그로 
변경하여 클라이언트에게 전달하는 JSP 문서 --%> 
<%-- => CSV 형식의 텍스트 데이타를 제공받아 HTML 태그로 변환하여 페이지의 요소 변경 - 파싱(Parsing) 처리 --%>
<%-- CSV(Comma Separated Values) : 콤마(,)를 사용하여 값을 구분하는 비구조적인 데이타 표현 방식 --%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX</title>
<style type="text/css">
#newsList {
	width: 50%;
	margin: 0 auto;
}

#newsHead {
	padding: 5px;
	text-align: center;
	font-size: x-large;
	border: 2px solid black;
}

#newsContent {
	padding: 5px;
	font-size: medium;
	border: 2px dashed black;
	display: none;
}
</style>
</head>
<body>
	<h1>헤드라인 뉴스</h1>
	<hr>
	<div id="newsList">
		<div id="newsHead">헤드라인 뉴스</div>
		<div id="newsContent">
			<ol>
				<li>뉴스제목-1[언론사-1]</li>
				<li>뉴스제목-2[언론사-2]</li>
				<li>뉴스제목-3[언론사-3]</li>
				<li>뉴스제목-4[언론사-4]</li>
				<li>뉴스제목-5[언론사-5]</li>
			</ol>
		</div>
	</div>
	
	<script type="text/javascript">
	document.getElementById("newsList").onmouseover=function() {
		document.getElementById("newsContent").style="display: block;";
	}
	
	document.getElementById("newsList").onmouseout=function() {
		document.getElementById("newsContent").style="display: none;";
	}
	</script>
</body>
</html>










