package com.wuky.passport.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.pojo.TbUser;


public interface TbUserService
{
	/**
	 * 用户登录
	 *
	 * @param user
	 * @return
	 */
	onlineshopResult login(TbUser user, HttpServletResponse response, HttpServletRequest request);

	/**
	 * 根据token查询用户信息
	 *
	 * @param token
	 * @return
	 */
	onlineshopResult getUserInfoByToken(String token);

	/**
	 * 退出登录
	 *
	 * @param token
	 * @param response
	 * @param request
	 * @return
	 */
	onlineshopResult logout(String token, HttpServletResponse response, HttpServletRequest request);

	/**
	 * 用户注册
	 *
	 * @param user
	 * @return
	 */
	int save(TbUser user);

	/**
	 * @param param
	 * @param type
	 * @return
	 */
	public onlineshopResult checkUserInfo(String param, int type);
}
