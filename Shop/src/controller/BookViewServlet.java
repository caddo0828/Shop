package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import dao.BookDao;
/**
 * ͨ���û���ѡ�񣬵�һ��չʾѡ�е���Ʒ����Ϣ
 * @author ����
 */
@WebServlet("/BookViewServlet")
public class BookViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ܴ��ݵ��鼮idֵ
		String id = request.getParameter("id");
		String pageNumber = request.getParameter("pageNumber");
		Book book = BookDao.findById(id);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("bookView", book);
		request.getRequestDispatcher("book_view.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
