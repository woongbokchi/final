package com.ex.domain;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ex.persistence.UserDAO;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Inject
	UserDAO udao;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		// String uauth = (String) session.getAttribute("uauth");
		// ·Î±×ÀÎ X

		if (session.getAttribute("uno") == null) {
			// System.out.println("prehandle.................................");
			response.sendRedirect("loginF");
			return false;
		}

		return super.preHandle(request, response, handler);
	}

}