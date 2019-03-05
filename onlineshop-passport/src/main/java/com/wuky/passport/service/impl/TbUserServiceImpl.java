package com.wuky.passport.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.commons.utils.CookieUtils;
import com.wuky.commons.utils.JsonUtils;
import com.wuky.dubbo.service.TbUserDubboService;
import com.wuky.passport.service.TbUserService;
import com.wuky.pojo.TbUser;
import com.wuky.redis.dao.JedisDao;


@Service
public class TbUserServiceImpl implements TbUserService
{
	@Reference
	private TbUserDubboService tbUserDubboServiceImpl;

	@Resource
	private JedisDao jedisDaoImpl;

	public onlineshopResult login(final TbUser user, final HttpServletResponse response, final HttpServletRequest request)
	{
		final onlineshopResult or = new onlineshopResult();
		final TbUser tbUser = tbUserDubboServiceImpl.selByUser(user);
		if (tbUser != null)
		{
			or.setStatus(200);
			//当用户登录成功后把用户信息放入到redis中
			final String key = UUID.randomUUID().toString();
			jedisDaoImpl.set(key, JsonUtils.objectToJson(tbUser));
			jedisDaoImpl.expire(key, 60 * 60 * 24 * 7);

			//产生Cookie
			CookieUtils.setCookie(request, response, "TT_TOKEN", key, 60 * 60 * 24 * 7);
		}
		else
		{
			or.setMsg("用户名或密码错误");

		}
		return or;
	}

	public onlineshopResult getUserInfoByToken(final String token)
	{
		final onlineshopResult oResult = new onlineshopResult();
		final String json = jedisDaoImpl.get(token);

		if (json != null && !json.equals(""))
		{
			final TbUser tbuser = JsonUtils.jsonToPojo(json, TbUser.class);
			tbuser.setPassword(null);
			oResult.setStatus(200);
			oResult.setMsg("ok");
			oResult.setData(tbuser);
		}

		return oResult;
	}

	public onlineshopResult logout(final String token, final HttpServletResponse response, final HttpServletRequest request)
	{
		jedisDaoImpl.del(token);
		CookieUtils.deleteCookie(request, response, "TT_TOKEN");
		final onlineshopResult oResult = new onlineshopResult();
		oResult.setStatus(200);
		oResult.setMsg("ok");
		return oResult;
	}

}
