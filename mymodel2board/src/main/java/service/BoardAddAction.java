package service;

import dao.BoardDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BoardDTO;

public class BoardAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("boardAddaction");
		
		request.setCharacterEncoding("utf-8"); //한글로 깨지는 것을 방지
		
		BoardDTO board = new BoardDTO();
		board.setBoard_name(request.getParameter("board_name"));
		board.setBoard_pass(request.getParameter("board_pass"));
		board.setBoard_subject(request.getParameter("board_subject"));
		board.setBoard_content(request.getParameter("board_content"));
		
		BoardDAO dao = new BoardDAO();
		int result = dao.insert(board); //원문 글작성
		
		if(result == 1) {
			System.out.println("원문 글 작성 성공");
		}else {
			System.out.println("원문 글 작성 실패");
		}
			
		ActionForward forward = new ActionForward();
		forward.setRedirect(true); //만약 false이면 새로고침하면 똑같은 데이터가 여러번 입력
		forward.setPath("./BoardListAction.do");
		
		return forward;
	} //원문 글 작성 처리 서비스

}
