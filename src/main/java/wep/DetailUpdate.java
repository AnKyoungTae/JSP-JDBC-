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

@WebServlet("/datailUpdate")
public class DetailUpdate extends HttpServlet{
	String url = "jdbc:mysql://127.0.0.1:3306/test1?useUnicode=true&serverTimezone=Asia/Seoul";
	String user = "KT";
	String Password = "5369";
	String sql = "update NOTICE "
			+ "set title= ?, content = ? "
			+ "where BOARDNUM = ?";
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		int boaruNum = Integer.parseInt(req.getParameter("boardnum"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, Password);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setInt(3, boaruNum);
			
			st.executeUpdate();
			
			
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("notice");
		
	}
	
}
