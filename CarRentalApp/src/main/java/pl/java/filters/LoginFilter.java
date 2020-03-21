package pl.java.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import pl.java.model.User;
import pl.java.services.UserService;

@WebFilter("/*")
public class LoginFilter implements Filter {
	@Inject
	private UserService userService;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		if(httpServletRequest.getUserPrincipal() != null && httpServletRequest.getSession().getAttribute("user") == null) {
			saveUserInSession(httpServletRequest);
		}
		chain.doFilter(request, response);
	}
	
	private void saveUserInSession(HttpServletRequest httpServletRequest) {
		String username = httpServletRequest.getUserPrincipal().getName();
		System.out.println(username);
		User user = userService.readUserByUsername(username);
		httpServletRequest.getSession(true).setAttribute("user", user);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
	
	public void destroy() {

	}
}
