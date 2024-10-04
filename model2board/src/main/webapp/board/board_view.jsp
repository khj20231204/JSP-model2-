<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
</head>
<body>

<table border=1 width=600 align=center>
	<caption><h3>상세 페이지</h3></caption>
	<tr>
		<td>제목</td>
		<td>${board.board_subject }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td>
			<pre>${board.board_content }</pre>
			${content }
		</td>
	</tr>
	<tr>
		<td colspan=2 align="center">
			<input type="button" value="댓글" 
onclick="location.href='./BoardReplyForm.do?board_num=${board.board_num}&page=${page}' ">
			
			<input type="button" value="수정" 
onclick="location.href='./BoardModifyForm.do?board_num=${board.board_num}&page=${page}' ">
			
			<input type="button" value="삭제" 
onclick="location.href='./BoardDeleteForm.do?board_num=${board.board_num}&page=${page}' ">
			
			<input type="button" value="목록" 
onclick="location.href='./BoardListAction.do?page=${page}'">
		</td>
	</tr>
</table>

</body>
</html>


