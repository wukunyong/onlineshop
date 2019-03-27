package com.wuky.passport.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.passport.service.TbUserService;
import com.wuky.pojo.TbUser;


@Controller
public class TbUserController
{
	@Resource
	private TbUserService tbUserServiceImpl;

	/**
	 * 显示登录页面
	 *
	 * @return
	 */
	@RequestMapping("user/showLogin")
	public String showLogin(@RequestHeader(value = "Referer", defaultValue = "") final String url, final Model model,
			final String interurl)
	{
		if (interurl != null && !interurl.equals(""))
		{
			model.addAttribute("redirect", interurl);
		}
		else if (!url.equals(""))
		{
			model.addAttribute("redirect", url);
		}
		return "newlogin";
	}

	/**
	 * 登录
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping("user/login")
	@ResponseBody
	public onlineshopResult login(final TbUser user, final HttpServletRequest request, final HttpServletResponse response)
	{
		return tbUserServiceImpl.login(user, response, request);
	}

	/**
	 * 通过token获取用户信息
	 *
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping("user/token/{token}")
	@ResponseBody
	public Object getUserInfo(@PathVariable final String token, final String callback)
	{
		final onlineshopResult oResult = tbUserServiceImpl.getUserInfoByToken(token);
		if (callback != null && !callback.equals(""))
		{
			final MappingJacksonValue mjv = new MappingJacksonValue(oResult);
			mjv.setJsonpFunction(callback);
			return mjv;
		}
		return oResult;
	}

	/**
	 * 退出
	 *
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping("user/logout/{token}")
	@ResponseBody
	public Object logout(@PathVariable final String token, final String callback, final HttpServletRequest request,
			final HttpServletResponse response)
	{
		final onlineshopResult oResult = tbUserServiceImpl.logout(token, response, request);
		if (callback != null && !callback.equals(""))
		{
			final MappingJacksonValue mjv = new MappingJacksonValue(oResult);
			mjv.setJsonpFunction(callback);
			return mjv;
		}
		return oResult;
	}

	/**
	 * 显示注册页面
	 *
	 * @return
	 */
	@RequestMapping("user/showRegister")
	public String showRegister()
	{
		return "register";
	}

	@RequestMapping("/user/register")
	@ResponseBody
	public onlineshopResult register(final TbUser tbUser)
	{
		final onlineshopResult oResult = new onlineshopResult();
		int index;

		index = tbUserServiceImpl.save(tbUser);
		try
		{
			if (index == 1)
			{
				oResult.setStatus(200);
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			oResult.setData(e.getMessage());
		}
		return oResult;
	}

	@RequestMapping("user/check/{param}/{type}")
	@ResponseBody
	public onlineshopResult checkUserInfo(@PathVariable final String param, @PathVariable final Integer type)
	{
		final onlineshopResult oResult = tbUserServiceImpl.checkUserInfo(param, type);
		return oResult;

	}
}
