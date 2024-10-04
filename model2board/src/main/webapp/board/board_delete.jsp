<%@ page language="java" contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<title>MVC 게시판</title>	
	<script src="http://code.jquery.com/jquery-latest.js"></script>	
	<script src="<%=request.getContextPath() %>/board/script.js"></script>
</head>
<body>

board_num1 : <%=request.getParameter("board_num") %><br>
page1 : <%=request.getParameter("page") %><br><br>

board_num2 : ${param.board_num }<br>
page2 : ${param.page }<br>

<form action="<%=request.getContextPath() %>/BoardDelete.do" method="post">
<input type="hidden" name="board_num" value="${param.board_num}">
<input type="hidden" name="page" value="${param.page}">

<table cellpadding="0" cellspacing="0" align=center border=1 width=300>
	<caption><h3>글삭제</h3></caption>
	<tr align="center" valign="middle">
		<td colspan="5">MVC 게시판</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12" height="16">
			<div align="center">비밀번호</div>
		</td>
		<td>
			<input type="password" name="board_pass" id="board_pass" size="10" maxlength="10"> 
		</td>
	</tr>
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	<tr align="center" valign="middle">
		<td colspan="5">
			<input type=submit value="삭제">
			<input type=reset value="취소">
		</td>
	</tr>
</table>
</form>

</body>
</html>