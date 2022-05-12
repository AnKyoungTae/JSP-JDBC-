package wep;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class DetailController extends HttpServlet{
	String url = "jdbc:mysql://127.0.0.1:3306/test1?useUnicode=true&serverTimezone=Asia/Seoul";
	String user = "KT";
	String Password = "5369";
	String sql = "SELECT * FROM NOTICE WHERE BOARDNUM = ?";
	String update = "update notice set hit = hit + 1 where boardnum = ?";
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardNo;
		
		boardNo = Integer.parseInt(req.getParameter("id"));
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, Password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, boardNo);
			ResultSet rs = st.executeQuery();
			
			rs.next();
			Notice notice = new Notice();
			
			notice.setBoardnum(rs.getInt("BOARDNUM"));
			notice.setTitle(rs.getString("TITLE"));
			notice.setContent(rs.getString("CONTENT"));
			notice.setUserId(rs.getString("USERiD"));
			notice.setRegDate(rs.getDate("REGDATE"));
			notice.setHit(rs.getInt("HIT"));
			
			req.setAttribute("n", notice);
			
			PreparedStatement st2 = con.prepareStatement(update);
			st2.setInt(1, boardNo);
			
			st2.executeUpdate();
			
			rs.close();
			st.close();
			st2.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		req.getRequestDispatcher("WEB-INF/view/notice/detail.jsp").forward(req, resp);
		
		
	}
	
}
