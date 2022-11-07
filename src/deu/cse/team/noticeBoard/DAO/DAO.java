package deu.cse.team.noticeBoard.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	
	protected Connection con;
	protected PreparedStatement st;
	protected ResultSet rs;
	
	public DAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/데이터베이스이름","아이디","비밀번호");
			
		} catch (ClassNotFoundException | SQLException e) {
			e.getMessage();
		}
	}

}
