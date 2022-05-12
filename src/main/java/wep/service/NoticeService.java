package wep.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wep.Notice;

public class NoticeService {
	
	public List<Notice> getNoticeList(){
		
		return getNoticeList("title", "", 1);
	}
	
	public List<Notice> getNoticeList(int page){
		
		return getNoticeList("title", "", page);
	}
	
	public List<Notice> getNoticeList(String field,String query, int page){
		String url = "jdbc:mysql://127.0.0.1:3306/test1?useUnicode=true&serverTimezone=Asia/Seoul";
		String user = "KT";
		String Password = "5369";
		String sql = "select n.* from (select @rownum := @rownum +1 rn , n.* "
				+ "from (select * from notice where "+ field +" like ? order by REGDATE desc) n, (select @rownum := 0) r) n "
				+ "where n.rn between ? and ?";
		
		List<Notice> list = new ArrayList<Notice>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, Password);
			PreparedStatement st = con.prepareStatement(sql); 
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*5);
			st.setInt(3, page*5);
			
			ResultSet rs = st.executeQuery();
			
			
			while(rs.next()) {
				Notice notice = new Notice();
				
				notice.setBoardnum(rs.getInt("BOARDNUM"));
				notice.setTitle(rs.getString("TITLE"));
				notice.setUserId(rs.getString("USERID"));
				notice.setRegDate(rs.getDate("REGDATE"));
				notice.setHit(rs.getInt("HIT"));
				
				list.add(notice);
				
			}
			
			rs.close();
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int getNoticeCount() {
		return getNoticeCount("title","");
	}
	
	public int getNoticeCount(String field , String query) {
		String url = "jdbc:mysql://127.0.0.1:3306/test1?useUnicode=true&serverTimezone=Asia/Seoul";
		String user = "KT";
		String Password = "5369";
		String sql = "select count(*) count from notice "
					+ "where "+ field +" like ?";
		
		int count = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, Password);
			PreparedStatement st = con.prepareStatement(sql); 
			st.setString(1, "%"+query+"%");
			ResultSet rs = st.executeQuery();
			
			rs.next();
			
			count = rs.getInt("count");
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return count;
	}

}
