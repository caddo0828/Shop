package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import dao.BookDao;
/** ������������ݵķ�ҳ�����Լ���ת����Ӧҳ�����߼�����
 * @author ����
 */
@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int pageNumber = 1; //��ǰ������ҳ�����ӿͻ��λ�ȡ,Ĭ��Ϊ1����ʼ����

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���Ҷ�Ӧ����ʼ�����鼮��Ϣ����ȡ��ҳ��
		ArrayList<Book> list = BookDao.findBook(pageNumber);
		int pageCount = BookDao.pageCount;
		//��ȡ��ҳ��,���ҽ���ҳ����ŵ�request�������
		request.setAttribute("pageCount",pageCount);
	    
		//���մӱ��д��ݵ�ҳ��
		String page = request.getParameter("pageNumber");
		if(page!=""&&page!=null) {
			pageNumber = Integer.parseInt(page);
		}
		
		//��������ҳ������pageCount���ҳ����ص��ڵ�һҳ��
		if(pageNumber > pageCount) {
			list = BookDao.findBook(1);
			pageNumber = 1;
		}else {
			//���δ�ɹ�������תҳ��������Ĭ�ϴӵ�һҳչ�����ݣ�����ָ��ҳ��չʾ����
			list = BookDao.findBook(pageNumber);
		}
		request.setAttribute("bookList", list);
		request.setAttribute("pageNumber", pageNumber);
		request.getRequestDispatcher("book_list.jsp").forward(request, response);
		return;
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
