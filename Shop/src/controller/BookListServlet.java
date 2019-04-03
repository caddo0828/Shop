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
/** 处理购物大厅数据的分页技术以及跳转到对应页数的逻辑处理
 * @author 老腰
 */
@WebServlet("/BookListServlet")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int pageNumber = 1; //当前所处的页数，从客户段获取,默认为1，初始界面

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先找对应的起始界面书籍信息，获取总页数
		ArrayList<Book> list = BookDao.findBook(pageNumber);
		int pageCount = BookDao.pageCount;
		//获取总页数,并且将总页数存放到request域对象中
		request.setAttribute("pageCount",pageCount);
	    
		//接收从表单中传递的页数
		String page = request.getParameter("pageNumber");
		if(page!=""&&page!=null) {
			pageNumber = Integer.parseInt(page);
		}
		
		//如果输入的页数大于pageCount最大页，则回到在第一页中
		if(pageNumber > pageCount) {
			list = BookDao.findBook(1);
			pageNumber = 1;
		}else {
			//如果未成功接收跳转页数请求，则默认从第一页展开数据，否则指定页数展示数据
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
