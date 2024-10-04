package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.Action;
import service.ActionForward;
import service.BoardAddAction;

import java.io.IOException;

import org.apache.catalina.connector.Request;

@WebServlet("*.do") //do 확장자는 모두 이쪽으로
public class Controller extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI(); //전체 URI를 가져온다
		String contextPath = request.getContextPath(); //현재 프로제트명을 가져온다.
		String command = requestURI.substring(contextPath.length());
		
		System.out.println(requestURI);  //mymodel2board/LoginForm.do
		System.out.println(contextPath); //mymodel2board
		System.out.println(command); 	 //LoginForm.do
		
		Action action = null;
		ActionForward forward = null;
		
		//글작성(원문 작성)
		if(command.equals("/BoardAddAction.do")) {
			try {
				action = new BoardAddAction();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//포워딩 처리
		if(forward != null) {
			if(forward.isRedirect()) { //redirect 방식으로 포워딩
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	//링크를 걸거나 location이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doProcess(request, response);
	}

	//form을 통해 전송 시
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doProcess(request, response);
	}

}

/*
HTTP Request는 웹 서버에 데이터를 요청하는 방식입니다. 일반적으로 다음과 같은 메소드를 사용합니다.

GET: 서버에서 특정 리소스를 가져오는 데 사용됩니다.
POST: 서버에 새로운 데이터를 생성하는 데 사용됩니다. 예를 들어, 웹 양식 데이터를 서버에 전송하거나 파일을 업로드할 때 사용됩니다.
PUT: 서버에 있는 기존 리소스를 전체적으로 업데이트하는 데 사용됩니다.
DELETE: 서버에 있는 기존 리소스를 삭제하는 데 사용됩니다.
PATCH: 서버에 있는 기존 리소스를 부분적으로 업데이트하는 데 사용됩니다.
HEAD: GET 요청과 비슷하지만, 응답 본문 대신 응답 헤더만 반환합니다. 주로 리소스의 메타데이터를 확인할 때 사용됩니다.
*/