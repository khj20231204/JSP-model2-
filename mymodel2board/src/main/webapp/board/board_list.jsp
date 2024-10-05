<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="./BoardForm.do">글작성</a>
	<br>
	<%-- 
	BoardListAction.java에서 requst.setAttribute로 공유를 했기 때문에 여기서 바로 el로 출력 가능 
	BoardListAction.java에서 바로 dispatcher했다
	--%> 
	글 갯수 : ${listcount} 개
	
	<br>
	
	<%
	int count = ((Integer)request.getAttribute("listcount")).intValue();
	%>
	
	<br>
	
	글 갯수 : <%= count %> 
	
<table border=1 width=700 align=center>
	<caption>게시판 목록</caption>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>날짜</td>
		<td>조회수</td>
	</tr>
	
	<c:set var="num" value="${listcount -(page-1) * limit}"/>
	
	<%-- BoardListAction.java에서 request에 데이터를 담고 dispatcher를 했기 때문에 가능 --%>
	<c:forEach var="b" items="${boardlist}">
		<tr>
			<td>${num}
				<c:set var="num" value="${num-1}" />
			</td>
			<td>
				<%-- 
				댓글 제목 앞에 공백 표기하기 위한 코드 board_re_lev > 0 이면 댓글
				--%>
				<c:if test="${b.board_re_lev > 0}">
					<c:forEach var="b" begin="0" end="${b.board_re_lev}">
						&nbsp;
					</c:forEach>
				</c:if>
				<a href="./BoardDetailAction.do?board_num=${b.board_num}&page=${page}"> ${b.board_subject}</a>
			</td>
			<td>${b.board_name}</td>
			<td>
				<fmt:formatDate value="${b.board_date}" pattern="yyyy-MM-dd HH:mm:ss EE요일"/>
			</td>
			<td>${b.board_readcount}</td>
		</tr>
	</c:forEach>
</table>

<!-- 페이지 처리 -->
<center>
<c:if test="${listcount > 0}">
	
	<!-- 1page로 이동 -->
	<a href="./BoardListAction.do?page=1" style="text-decoration:none"><<</a>
		
	<!-- 이전 페이지로 이동 -->
	<c:if test="${startPage > limit}">
		<a href="./BoardListAction.do?page=${startPage - limit}" style="text-decoration:none">[이전]</a>
	</c:if>
		
	<!-- 각 블럭에 10개의 페이지 출력 -->
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
		<c:if test="${i == page}"> 
			[${i}]
		</c:if>
		<c:if test="${i != page}">
			<a href="./BoardListAction.do?page=${i}">[${i}]</a>
		</c:if>
	</c:forEach>
	
	<!-- 다음 페이지로 이동 -->
	<c:if test="${endPage < pageCount }">
			<a href="./BoardListAction.do?page=${startPage+limit}" style="text-decoration:none">[다음]</a>
	</c:if>
	
	<!-- 마지막 페이지로 이동 -->
	<a href="./BoardListAction.do?page=${pageCount}" style="text-decoration: none"> >> </a>
</c:if>
</center>
</body>
</html>