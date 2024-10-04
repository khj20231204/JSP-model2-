package service;

import java.io.PrintWriter;

import dao.BoardDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BoardBean;

public class BoardDelete implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("BoardDelete");
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();

		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		String board_pass = request.getParameter("board_pass");
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardBean db = dao.getDetail(board_num);      	// 상세정보 구하기
		
		// 비번 비교
		if(db.getBoard_pass().equals(board_pass)) {		// 비번 일치시
			int result = dao.delete(board_num);			// delete SQL문 실행
			if(result == 1) System.out.println("삭제 성공");
			
				response.sendRedirect("./board/delete.jsp?page="+page);
			
			return null;
			
		}else {		// 비번 불일치시
			out.println("<script>");
			out.println("alert('비번이 일치하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			
			return null;
		}	
		
//		ActionForward forward = new ActionForward();
//		forward.setRedirect(false);
//		forward.setPath("/BoardListAction.do?page="+page);
//		
//		return forward;
	}

}
