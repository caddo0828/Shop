package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MyCar;
/**
 * ��̬��ʾ���ﳵ�е�����
 * @author ����
 */
@WebServlet("/ShowCarServlet")
public class ShowCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ���ﳵ�е�����������Ϣ�������ݸ����ﳵ��ʾ����
		MyCar mycar = (MyCar) request.getSession().getAttribute("myCar");
		
		//�жϹ��ﳵ�����Ƿ�Ϊ�գ��վ��ȵ�¼
		if(mycar!=null) {
			//���յ���治��ʱ�Ĵ�����Ϣ
			request.setAttribute("errorMsg", request.getAttribute("error"));
			request.setAttribute("bookList", mycar.findAllBook());
			request.getRequestDispatcher("shoppingCar.jsp").forward(request, response);
			return;
		}else {
			response.sendRedirect("/Shop/login.jsp");
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
