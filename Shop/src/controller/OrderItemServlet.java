package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.Book;
import bean.Order;
import bean.User;
import dao.BookDao;
import dao.OrderDao;
import service.DataHelper;
import service.HistoryHelper;
import service.MyCar;
import service.SendMail;
/**
 * 对订单辅助类中的数据进行处理。操作形成数据订单表以及子表，并且将数据保存在订单历史记录对象中
 * @author 老腰
 */
@WebServlet("/OrderItemServlet")
public class OrderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//直接从对应的订单数据处理对象中获取所有的订单数据
		DataHelper helper = (DataHelper) request.getSession().getAttribute("dataHelper");
		//获取用户
		User user = (User) request.getSession().getAttribute("loginUser");
		//创建记录历史订单的对象
		HistoryHelper historyHelper = new HistoryHelper();
		request.getSession().setAttribute("historyHelper", historyHelper);
		
		//接收表单传入的订货人的数据
		String orderName = request.getParameter("userName");
		String tel = request.getParameter("telephone");
		String address = request.getParameter("address");
		//创建订购者信息
		Order order = new Order(orderName,tel,address);
	    
		if(helper!=null) {
			ArrayList<Book> list = helper.findAllBook();
			if(list!= null) {
				//保存订单信息，生成数据库表,成功插入则跳转
				boolean flag = OrderDao.saveOrder(helper, user,order);
				if(flag) {
					//获取成功生成的订单号，订单时间
					String orderID = OrderDao.lastOrderId;
					String orderTime = OrderDao.findOrderTime(orderID);
					
					
					//取出所有订单书籍，当真正购买成功时的时候再对数据库的书籍数量进行修改
					for(Book book : list) {
						//修改数据库中书籍的数量
						BookDao.updateNums(book);
						//根据对应的书籍找到id，并且清除对应的购物车中的书籍数据
						new MyCar().delete(book.getId());
						//购买成功后，清除所有订单数据
						helper.clear();
						//将订单消息添加保存生成历史记录
						double totalPrice = book.getPrice()*book.getShopNums();
						Order ors = new Order(orderID,book,totalPrice,orderTime);
						historyHelper.add(ors);			
					}
					//将添加好的历史订单数据进行翻转，将最早的订单记录显示在最前面
					//翻转集合，将最早购买的数据放最后面
					Collections.reverse(historyHelper.orderList);
					//邮件发送
					try {
						SendMail.sendMail(user.getEmail());
					} catch (MessagingException e) {
						e.printStackTrace();
					}
					request.getRequestDispatcher("shopping-result.html").forward(request, response);
					return;
				}
			}
			
		}else if(user==null) {
			response.sendRedirect("/Shop/login.jsp");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
