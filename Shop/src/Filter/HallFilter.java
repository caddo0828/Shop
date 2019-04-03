package Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 对所有的.jsp文件进行过滤
 * @author 老腰
 */
@WebFilter(filterName="jspFilter",urlPatterns={"*.jsp"})
public class HallFilter implements Filter {
	public void destroy() {
		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String path = req.getServletPath();
		String uri = path.substring(path.lastIndexOf("/")+1, path.length());
		if(uri.equals("login.jsp")) {
			chain.doFilter(request, response);
		}else {
			if(req.getSession().getAttribute("loginUser")==null) {
				req.getRequestDispatcher("login.jsp").forward(req, resp);
				return;
			}else {
				chain.doFilter(request, response);			
			}
		}
		
		    
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
