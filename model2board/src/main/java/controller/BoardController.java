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
import service.BoardListAction;

import java.io.IOException;

@WebServlet("*.do")  // do 확장자로 요청하는 모든 요청을 받는다는 의미
public class BoardController extends HttpServlet {

	// doGet(), doPost() 메소드의 공통적인 작업을 처리하는 메소드
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		System.out.println("requestURI:"+ requestURI);   // /model2board/BoardAddAction.do
		System.out.println("contextPath:"+ contextPath); // /model2board
		System.out.println("command:"+ command);         // /BoardAddAction.do
		
		Action action = null;
		ActionForward forward = null;
		
		// 글작성(원문작성)
		if(command.equals("/BoardAddAction.do")) {
			try {
				action = new BoardAddAction();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// 글작성 폼	
		}else if(command.equals("/BoardForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/board_write.jsp");
			
		// 글목록	
		}else if(command.equals("/BoardListAction.do")) {
			try {
				action = new BoardListAction();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		// 포워딩 처리
		if(forward != null) {
			if(forward.isRedirect()) {    // redirect 방식으로 포워딩
				response.sendRedirect(forward.getPath());
			}else {	                      // dispatcher 방식으로 포워딩
				RequestDispatcher dispatcher = 
					request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}			
		}	
		
	}	// doProcess() end
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get");
		
		doProcess(request, response);			// doProcess()메소드 호출
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		
		doProcess(request, response);			// doProcess()메소드 호출
	}

}
