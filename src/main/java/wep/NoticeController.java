package wep;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wep.service.NoticeService;

@WebServlet("/notice")
public class NoticeController extends HttpServlet{
	String url = "jdbc:mysql://127.0.0.1:3306/test1?useUnicode=true&serverTimezone=Asia/Seoul";
	String user = "KT";
	String Password = "5369";
	String sql = "SELECT * FROM NOTICE order by boardnum desc";
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeService noticeList = new NoticeService();
		
		int page = 1;
		String page_ = req.getParameter("p");
		String title = req.getParameter("t");
		String query = req.getParameter("q");
		
		if( title != null && !title.equals("") && query != null && !query.equals("")) {
			if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
			List<Notice> list = noticeList.getNoticeList(title, query, page);
			req.setAttribute("list", list);
		}
		else if(page_ != null && !page_.equals("")) {
			page = Integer.parseInt(page_);
			List<Notice> list = noticeList.getNoticeList(page);
			req.setAttribute("list", list);
		}
		else {
			List<Notice> list = noticeList.getNoticeList();
			req.setAttribute("list", list);
		}
		
		int count = 0;
		
		if( title != null && !title.equals("") && query != null && !query.equals("")) {
			if(page_ != null && !page_.equals("")) page = Integer.parseInt(page_);
			count = noticeList.getNoticeCount(title, query);
			req.setAttribute("count", count);
		}
		else {
			count = noticeList.getNoticeCount();
			req.setAttribute("count", count);
		}
		
		
		
		req.getRequestDispatcher("WEB-INF/view/notice/notice.jsp").forward(req, resp);
		
	}
}
