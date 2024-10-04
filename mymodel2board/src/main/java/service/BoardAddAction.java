package service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("boardAddaction");
		
		ActionForward forward = new ActionForward();
		
		return forward;
	} //원문 글 작성 처리 서비스

}
