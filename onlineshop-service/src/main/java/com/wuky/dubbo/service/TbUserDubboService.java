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

}
