package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.Order;
import bean.User;
import service.HistoryHelper;
/**
 * 展示订单购买的历史记录类
 * @author 老腰
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//从历史记录辅助类中取出数据
		HistoryHelper helper = (HistoryHelper) request.getSession().getAttribute("historyHelper");
		//获取用户
		User user = (User) request.getSession().getAttribute("loginUser");
		if(helper!=null) {
			ArrayList<Order> list = helper.orderList;
			request.setAttribute("orderList", list);
		}else if(user==null){
			response.sendRedirect("/Shop/login.jsp");
			return ;
		}
		request.getRequestDispatcher("history_data.jsp").forward(request, response);
		return ;
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
