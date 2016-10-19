package com.bit2016.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit2016.mysite.action.board.ListAction;
import com.bit2016.mysite.vo.BoardVo;
import com.bit2016.mysite.vo.GuestBookVo;

public class BoardDao {
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection( url, "webdb", "webdb" );
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩실패 : " + e);
		}
		
		return conn;
	}
	
	public void update(BoardVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "update board set order_no = order_no +1 where group_no = ? and order_no > ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, vo.getNo());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally{
			try{
				if(pstmt !=null){
					pstmt.close();
				}
				if(conn !=null){
					conn.close();
				}
			}catch(SQLException e) {
				System.out.println("error : " + e);
			} 
		}
	}
	
	public void newWrite(BoardVo vo){

	}
	public void reWrite(){
		
	}

	public List<BoardVo> getList(int pageS,int listS){
		
		List<BoardVo> list = new ArrayList<BoardVo>();
	
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			conn = getConnection();
		
			String sql = "select * from (select no, title, hit, reg_date, depth, name, users_no, rownum as rn "+
					    				 "from(select a.no,a.title,a.hit,to_char(a.reg_date,'yyyy-mm-dd hh:mi:ss') as reg_date, a.depth, b.name , a.users_no "+		  
					    				 		 "from board a, users b "+
					    				 		"where a.users_no = b.no "+
					    				 		//-- and title like '%kwd%' or content like '%kwd%'
					    				 	 "order by GROUP_NO desc, order_no asc)) "+
					    		  "where (?-1)*?+1 <= rn and rn <= ?*?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pageS);
			pstmt.setInt(2, listS);
			pstmt.setInt(3, pageS);
			pstmt.setInt(4, listS);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				long no = rs.getLong( 1 );
				String title = rs.getString( 2 );
				int hit = rs.getInt( 3 );
				String regDate = rs.getString( 4 );
				int depth = rs.getInt( 5 );
				String userName = rs.getString( 6 );
				long userNo = rs.getLong( 7 );
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setDepth(depth);
				vo.setUserName(userName);
				vo.setUserNo(userNo);
				
				list.add( vo );
			}
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		}finally{
			try{
				if(rs != null){
					rs.close();
				}
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
				conn.close();
			}
			}catch(SQLException e){
				System.out.println("error : " + e);
			}
		}
		
		return list;
	}
	
}
