package com.wuky.cart.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.commons.utils.CookieUtils;
import com.wuky.commons.utils.HttpClientUtil;
import com.wuky.commons.utils.JsonUtils;


public class LoginInterceptor implements HandlerInterceptor
{
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception
	{
		final String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		if (token != null && !token.equals(""))
		{
			final String json = HttpClientUtil.doPost("http://localhost:8084/user/token/" + token);
			final onlineshopResult oResult = JsonUtils.jsonToPojo(json, onlineshopResult.class);
			if (oResult.getStatus() == 200)
			{
				return true;
			}
		}
		final String num = request.getParameter("num");
		response.sendRedirect("http://localhost:8084/user/showLogin?interurl=" + request.getRequestURL() + "%3Fnum=" + num);
		return false;
	}

	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception
	{

	}

	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final Exception ex) throws Exception
	{

	}

}
