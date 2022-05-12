package wep;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/noticedelete")
public class NoticeDeleteController extends HttpServlet{
	String url = "jdbc:mysql://127.0.0.1:3306/test1?useUnicode=true&serverTimezone=Asia/Seoul";
	String user = "KT";
	String Password = "5369";
	String sql = "delete from notice where boardnum = ?";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardNo;
		
		boardNo = Integer.parseInt(req.getParameter("id"));
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, Password);
			PreparedStatement st = con.prepareCall(sql);
			
			st.setInt(1, boardNo);
			
			st.executeUpdate();
			
			st.close();
			con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		resp.sendRedirect("notice");
		
	}
}
