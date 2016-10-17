package com.bit2016.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bit2016.mysite.vo.UserVo;

public class UserDao {
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
	
	// 로그인
	public UserVo get(String email, String password){
		UserVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = "select no,name from users where email = ? and password = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			

			rs = pstmt.executeQuery();
			
			if(rs.next()){
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				vo =new UserVo();
				vo.setNo(no);
				vo.setName(name);
			}
			
		} catch (SQLException e) {
			System.out.println("error : " + e);
		} finally{
			try{
				if(rs !=null){
					rs.close();
				}
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
		return vo;
	}
	public void insert(UserVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			String sql = "insert INTO USERS VALUES(user_seq.nextval , ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
		
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
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
}
