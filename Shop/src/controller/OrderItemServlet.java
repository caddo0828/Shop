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
 * �Զ����������е����ݽ��д��������γ����ݶ������Լ��ӱ����ҽ����ݱ����ڶ�����ʷ��¼������
 * @author ����
 */
@WebServlet("/OrderItemServlet")
public class OrderItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//ֱ�ӴӶ�Ӧ�Ķ������ݴ�������л�ȡ���еĶ�������
		DataHelper helper = (DataHelper) request.getSession().getAttribute("dataHelper");
		//��ȡ�û�
		User user = (User) request.getSession().getAttribute("loginUser");
		//������¼��ʷ�����Ķ���
		HistoryHelper historyHelper = new HistoryHelper();
		request.getSession().setAttribute("historyHelper", historyHelper);
		
		//���ձ�����Ķ����˵�����
		String orderName = request.getParameter("userName");
		String tel = request.getParameter("telephone");
		String address = request.getParameter("address");
		//������������Ϣ
		Order order = new Order(orderName,tel,address);
	    
		if(helper!=null) {
			ArrayList<Book> list = helper.findAllBook();
			if(list!= null) {
				//���涩����Ϣ���������ݿ��,�ɹ���������ת
				boolean flag = OrderDao.saveOrder(helper, user,order);
				if(flag) {
					//��ȡ�ɹ����ɵĶ����ţ�����ʱ��
					String orderID = OrderDao.lastOrderId;
					String orderTime = OrderDao.findOrderTime(orderID);
					
					
					//ȡ�����ж����鼮������������ɹ�ʱ��ʱ���ٶ����ݿ���鼮���������޸�
					for(Book book : list) {
						//�޸����ݿ����鼮������
						BookDao.updateNums(book);
						//���ݶ�Ӧ���鼮�ҵ�id�����������Ӧ�Ĺ��ﳵ�е��鼮����
						new MyCar().delete(book.getId());
						//����ɹ���������ж�������
						helper.clear();
						//��������Ϣ��ӱ���������ʷ��¼
						double totalPrice = book.getPrice()*book.getShopNums();
						Order ors = new Order(orderID,book,totalPrice,orderTime);
						historyHelper.add(ors);			
					}
					//����Ӻõ���ʷ�������ݽ��з�ת��������Ķ�����¼��ʾ����ǰ��
					//��ת���ϣ������繺������ݷ������
					Collections.reverse(historyHelper.orderList);
					//�ʼ�����
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
