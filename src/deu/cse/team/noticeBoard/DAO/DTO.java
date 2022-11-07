package deu.cse.team.noticeBoard.DAO;

import deu.cse.team.noticeBoard.model.board;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

public class DTO extends DAO{
        /*int BNUM;
        String TITLE;
        String CONTENT;
        String SID;
        String DATE;
        String BPS;*/
	public void boardInsert(String s1, String s2, String s3, String s4,String s5,String s6){
		String sql="insert into board(no, title, sid, date, type, bps) values(?,?,?,?,?,?)";
                try {
			st = con.prepareStatement(sql);
			st.setString(1, s1);
                        st.setString(2, s2);
                        st.setString(3, s3);
                        st.setString(4, s4);
                        st.setString(5, s5);
                        st.setString(6, s6);
			st.executeUpdate();
		} catch (SQLException e) {}
	}
	
	public void boardUpdate(String s1, String s2, String s3, String s4,String s5,String s6){
		String sql="update board set no=?, title=?, sid=?, date=?, type=?, bps=? where no=?";
		try {
			st = con.prepareStatement(sql);
			st.setString(1, s1);
                        st.setString(2, s2);
                        st.setString(3, s3);
                        st.setString(4, s4);
                        st.setString(5, s5);
                        st.setString(6, s6);
			st.executeUpdate();
		} catch (SQLException e) {}
	}

	public void boardDelete(String s1) {
		String sql="delete from board where BNUM=?";
		try {
			st = con.prepareStatement(sql);
			st.setString(1, s1);
			st.executeUpdate();
		} catch (SQLException e) {}
	}

	public ArrayList<board> boardSelect() throws SQLException ,NamingException { //커뮤니티 글 초기화, 불러오기
		ArrayList<board> list = new ArrayList<>();
		String sql="select no, title, sid, date, type, bps from board";
		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery();	
			while(rs.next()) {
				board b = new board();
				b.setBNUM(rs.getInt(1));
				b.setTITLE(rs.getString(2));
                                b.setSID(rs.getString(3));
                                b.setDATE(rs.getString(4));
                                b.setTYPE(rs.getString(5));
				list.add(b);
			}
		} catch (SQLException e) {}
		return list;
	}

}
