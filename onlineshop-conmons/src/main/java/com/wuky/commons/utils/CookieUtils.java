package com.wuky.commons.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * Cookie 工具类
 *
 */
public final class CookieUtils
{

	/**
	 * 得到Cookie的值, 不编码
	 *
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(final HttpServletRequest request, final String cookieName)
	{
		return getCookieValue(request, cookieName, false);
	}

	/**
	 * 得到Cookie的值,
	 *
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(final HttpServletRequest request, final String cookieName, final boolean isDecoder)
	{
		final Cookie[] cookieList = request.getCookies();
		if (cookieList == null || cookieName == null)
		{
			return null;
		}
		String retValue = null;
		try
		{
			for (int i = 0; i < cookieList.length; i++)
			{
				if (cookieList[i].getName().equals(cookieName))
				{
					if (isDecoder)
					{
						retValue = URLDecoder.decode(cookieList[i].getValue(), "UTF-8");
					}
					else
					{
						retValue = cookieList[i].getValue();
					}
					break;
				}
			}
		}
		catch (final UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return retValue;
	}

	/**
	 * 得到Cookie的值,
	 *
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookieValue(final HttpServletRequest request, final String cookieName, final String encodeString)
	{
		final Cookie[] cookieList = request.getCookies();
		if (cookieList == null || cookieName == null)
		{
			return null;
		}
		String retValue = null;
		try
		{
			for (int i = 0; i < cookieList.length; i++)
			{
				if (cookieList[i].getName().equals(cookieName))
				{
					retValue = URLDecoder.decode(cookieList[i].getValue(), encodeString);
					break;
				}
			}
		}
		catch (final UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return retValue;
	}

	/**
	 * 设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
	 */
	public static void setCookie(final HttpServletRequest request, final HttpServletResponse response, final String cookieName,
			final String cookieValue)
	{
		setCookie(request, response, cookieName, cookieValue, -1);
	}

	/**
	 * 设置Cookie的值 在指定时间内生效,但不编码
	 */
	public static void setCookie(final HttpServletRequest request, final HttpServletResponse response, final String cookieName,
			final String cookieValue, final int cookieMaxage)
	{
		setCookie(request, response, cookieName, cookieValue, cookieMaxage, false);
	}

	/**
	 * 设置Cookie的值 不设置生效时间,但编码
	 */
	public static void setCookie(final HttpServletRequest request, final HttpServletResponse response, final String cookieName,
			final String cookieValue, final boolean isEncode)
	{
		setCookie(request, response, cookieName, cookieValue, -1, isEncode);
	}

	/**
	 * 设置Cookie的值 在指定时间内生效, 编码参数
	 */
	public static void setCookie(final HttpServletRequest request, final HttpServletResponse response, final String cookieName,
			final String cookieValue, final int cookieMaxage, final boolean isEncode)
	{
		doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, isEncode);
	}

	/**
	 * 设置Cookie的值 在指定时间内生效, 编码参数(指定编码)
	 */
	public static void setCookie(final HttpServletRequest request, final HttpServletResponse response, final String cookieName,
			final String cookieValue, final int cookieMaxage, final String encodeString)
	{
		doSetCookie(request, response, cookieName, cookieValue, cookieMaxage, encodeString);
	}

	/**
	 * 删除Cookie带cookie域名
	 */
	public static void deleteCookie(final HttpServletRequest request, final HttpServletResponse response, final String cookieName)
	{
		doSetCookie(request, response, cookieName, "", -1, false);
	}

	/**
	 * 设置Cookie的值，并使其在指定时间内生效
	 *
	 * @param cookieMaxage cookie生效的最大秒数
	 */
	private static final void doSetCookie(final HttpServletRequest request, final HttpServletResponse response,
			final String cookieName, String cookieValue, final int cookieMaxage, final boolean isEncode)
	{
		try
		{
			if (cookieValue == null)
			{
				cookieValue = "";
			}
			else if (isEncode)
			{
				cookieValue = URLEncoder.encode(cookieValue, "utf-8");
			}
			final Cookie cookie = new Cookie(cookieName, cookieValue);
			if (cookieMaxage > 0)
			{
				cookie.setMaxAge(cookieMaxage);
			}
			if (null != request)
			{// 设置域名的cookie
				final String domainName = getDomainName(request);
				System.out.println(domainName);
				//                if (!"localhost".equals(domainName)) {
				//                	cookie.setDomain(domainName);
				//                }
			}
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 设置Cookie的值，并使其在指定时间内生效
	 *
	 * @param cookieMaxage cookie生效的最大秒数
	 */
	private static final void doSetCookie(final HttpServletRequest request, final HttpServletResponse response,
			final String cookieName, String cookieValue, final int cookieMaxage, final String encodeString)
	{
		try
		{
			if (cookieValue == null)
			{
				cookieValue = "";
			}
			else
			{
				cookieValue = URLEncoder.encode(cookieValue, encodeString);
			}
			final Cookie cookie = new Cookie(cookieName, cookieValue);
			if (cookieMaxage > 0)
			{
				cookie.setMaxAge(cookieMaxage);
			}
			if (null != request)
			{// 设置域名的cookie
				final String domainName = getDomainName(request);
				System.out.println(domainName);
				/*
				 * if (!"localhost".equals(domainName)) { cookie.setDomain(domainName); }
				 */
			}
			cookie.setPath("/");
			response.addCookie(cookie);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 得到cookie的域名
	 */
	private static final String getDomainName(final HttpServletRequest request)
	{
		String domainName = null;

		String serverName = request.getRequestURL().toString();
		if (serverName == null || serverName.equals(""))
		{
			domainName = "";
		}
		else
		{
			final int end = serverName.indexOf("/");
			serverName = serverName.substring(0, end);
			final String[] domains = serverName.split("\\.");
			final int len = domains.length;
			if (len > 3)
			{
				// www.xxx.com.cn
				domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
			}
			else if (len <= 3 && len > 1)
			{
				// xxx.com or xxx.cn
				domainName = "." + domains[len - 2] + "." + domains[len - 1];
			}
			else
			{
				domainName = serverName;
			}
		}

		if (domainName != null && domainName.indexOf(":") > 0)
		{
			final String[] ary = domainName.split("\\:");
			domainName = ary[0];
		}
		return domainName;
	}

}
