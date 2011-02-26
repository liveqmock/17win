package net.win;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.win.exception.BuildingException;
import net.win.utils.Constant;

/**
 * 控制系统的
 * 
 * @author Administrator
 * 
 */
public class SystemStopFilter implements Filter {

	public String[] INCLUDE_SUFFIX = new String[] { ".php", ".jsp" };

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest requestTemp,
			ServletResponse responseTemp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) requestTemp;
		HttpServletResponse response = (HttpServletResponse) responseTemp;
		String requestURI = request.getRequestURI();

		if (!requestURI.contains("admin") && checkIncludeSuffix(requestURI)) {
			if (Constant.stopAll) {
				throw new BuildingException("");
			}
			if (Constant.stopTask) {
				if (requestURI.contains("taskManager")
						|| requestURI.contains("taskRepository")) {
					throw new BuildingException("");
				}
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * TRUE 要检查
	 * 
	 * @param requestURI
	 * @return
	 */
	private boolean checkIncludeSuffix(String requestURI) {
		for (String path : INCLUDE_SUFFIX) {
			if (requestURI.toLowerCase().endsWith(path)) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
