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


@WebServlet("/join")
public class Join extends HttpServlet {
	String url = "jdbc:mysql://127.0.0.1:3306/test1?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul";
	String user = "KT";
	String Password = "5369";
	String sql = "INSERT INTO member VALUE(?,?,?)";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String userId;
		String userPs;
		String userName;
		
		userId = req.getParameter("userId");
		userPs = req.getParameter("userPs");
		userName = req.getParameter("userName");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, Password);
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, userId);
			st.setString(2, userPs);
			st.setString(3, userName);
			
			st.executeUpdate();
			
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("login.html");
		
		
		
		
	}
	
}
