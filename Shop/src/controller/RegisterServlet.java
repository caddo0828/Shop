package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import bean.User;
import dao.UserDao;
/**
 * 对用户注册传入的数据进行交互式判断，
 * 用户注册名，邮箱，联系电话唯一，且用户注册数据不允许为空
 * 满足以上条件，成功插入数据库users，并且跳转
 * @author 老腰
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		
		//接收从表单中提交的数据
		String name = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		User user = new User(name, pwd, email, tel);
		
		ObjectMapper mapper = new ObjectMapper();
		//先判断用户名是否已经注册
		if(UserDao.findByName(name)) {
			mapper.writeValue(response.getOutputStream(),"{nameExit:'true',nameMsg:'用户名已经被占用，请更换一个'}" );
		}
		 if(UserDao.findByEmail(email)){
			//判断注册邮箱是否已存在
			mapper.writeValue(response.getOutputStream(),"{emailExit:'true',emailMsg:'该邮箱已经被注册使用，请更换一个'}");
		}
		 if(UserDao.findByTel(tel)) {
			//判断电话号码是否已存在
			mapper.writeValue(response.getOutputStream(), "{telExit:'true',telMsg:'此电话号码已经被注册使用，请更换一个'}");
		}
	    
		 if(name!=null&&pwd!=null&&email!=null&&tel!=null) {
			 if(UserDao.insert(user)) {
		    	//注册成功跳转到reg-result.html界面，直接转向到登录界面
		    	response.sendRedirect("/Shop/reg-result.html");
		    	return ;
		    }else {
		    	//注册失败
		    	response.sendRedirect("/Shop/register.html");
		    	return;
		    }
		}
	}
		
		
		
		
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
