package service;

import java.util.List;

import dao.BoardDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardListAction");
		
		int page = 1; //1)현재 페이지 번호 
		int limit = 10; //2)한 페이지에 출력할 데이터 갯수
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("BoardListAction page:"+page);
		
		//3)총 데이터 갯수
		BoardDAO board = BoardDAO.getInstance();
		int listcount = board.getCount();
		
		//1) 2) 3)으로 부터 파생되는 변수 - startRow, endRow, 총페이지 수
		
		/*
		(1)startRow, (2)endRow => 데이터베이스의 데이터 rownum 수, 실제 board_num이 아니라 가상의 rownum
		startRow와 endRow는 page와 limit로 정의
		limit : 10
		page=1 : startRow=1, endRow=10   
	    page=2 : startRow=11, endRow=20   
	    page=3 : startRow=21, endRow=30
	    
	    limit : 5
	    page=1 : startRow=1, endRow=5
	    page=2 : startRow=6, endRow=10
	    page=3 : startRow=11, endRow=15
	    */  
		int startRow = (page-1) * limit + 1;
		int endRow = page * limit;	
		
		//게시판 목록 10개 구하기(startRow, endRow)
		List<BoardDTO> boardlist = board.getList(startRow, endRow);
		System.out.println("boardlist:"+boardlist);
		
		/*
		(3)총페이지 수
		160/10 + ((170%10 == 0) ? 0 : 1) = 16+0 = 16; 
		166/10 + ((170%10 == 0) ? 0 : 1) = 16+1 = 17;
		int형 끼리 연산 결과는 int형
		*/
		int pageCount = listcount/limit + ((listcount % limit == 0) ? 0 : 1);
		//[1] [2] [3] ... [17]
		
		int startPage = ((page-1)/limit)*limit + 1; //[1], [11], [21], ...
		int endPage = startPage + limit - 1; //[10], [20], [30], ...
		
		/*
		stratPage부터 endPage까지 for문을 돌리데 마지막 없는 페이지 체크
		예를 들어  
		[10] [11] [12] [13] ... [20] 페이지가 있고, 마지막 17페이지부터는 데이터가 없는 경우
		마지막 페이지를 16까지로 입력
		*/
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		//공유 설정
		//request객체로 공유해서 id는 세션, 나머지는 request => dispahter로 포워딩
		//int형은 ${page}로 el로 바로 출력 가능
		//list, array형은  foreach문에서 items에 먼저 삽입. request로 공유한 경우 
		request.setAttribute("page", page);
		request.setAttribute("limit", limit);
		request.setAttribute("listcount", listcount);
		request.setAttribute("boardlist", boardlist);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./board/board_list.jsp");
		
		return forward;
	}
	
}
