// DAO(Data Access Object)

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model.BoardBean;

public class BoardDAO {
	
	// 싱글톤 : 객체 생성을 한번만 수행 하는것.
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {		// 정적 메소드
		return instance;
	}

	// 컨넥션풀에서 컨넥션을 구해오는 메소드
	private Connection getConnection() throws Exception{
		Context init = new InitialContext();
  		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
  		return ds.getConnection();
	}
	
	// 글작성 : 원문작성
	public int insert(BoardBean board) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();

String sql="insert into model2board values(model2board_seq.nextval,?,?,?,?,";
       sql+="model2board_seq.nextval,?,?,?,sysdate)";
       
       		pstmt = con.prepareStatement(sql);
       		pstmt.setString(1, board.getBoard_name());
       		pstmt.setString(2, board.getBoard_pass());
       		pstmt.setString(3, board.getBoard_subject());
       		pstmt.setString(4, board.getBoard_content());
       		pstmt.setInt(5, 0);			// board_re_lev
       		pstmt.setInt(6, 0);			// board_re_seq
       		pstmt.setInt(7, 0);			// board_readcount
			result = pstmt.executeUpdate();		// insert SQL문 실행
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
		return result;
	}
	
	// 총 데이터 갯수 구하기
	public int getCount() {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
			String sql="select count(*) from model2board";
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();		// select SQL문 실행
			
			if(rs.next()) {
//				result = rs.getInt(1);
				result = rs.getInt("count(*)");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
		return result;
	}
	
	// 글목록 : 데이터 10개 구하기
	public List<BoardBean> getList(int start, int end){
		List<BoardBean> list = new ArrayList<BoardBean>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			
String sql="select * from ( select rownum rnum, board.* from ";
       sql+=" (select * from model2board order by board_re_ref desc, ";
       sql+=" board_re_seq asc) board ) ";
       sql+=" where rnum >= ? and rnum <= ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();		// select SQL문 실행
			
			while(rs.next()) {
				BoardBean board = new BoardBean();
				
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_name(rs.getString("board_name"));
				board.setBoard_pass(rs.getString("board_pass"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getTimestamp("board_date"));
				
				list.add(board);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
		return list;
	}
	
	
	
	
	
	
	
	
	
	
}
