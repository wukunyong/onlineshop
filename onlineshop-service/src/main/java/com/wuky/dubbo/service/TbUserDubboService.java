package com.wuky.dubbo.service;

import com.wuky.pojo.TbUser;


public interface TbUserDubboService
{
	/**
	 * 根据用户名密码查询登录
	 *
	 * @param user
	 * @return
	 */
	TbUser selByUser(TbUser user);

	/**
	 * 用户注册
	 *
	 * @param user
	 * @return
	 */
	int insByUser(TbUser user);

	/**
	 * 查询用户名，邮箱是否被使用
	 *
	 * @param param
	 * @param type
	 * @return
	 */
	boolean selUserInfo(String param, int type);
}
