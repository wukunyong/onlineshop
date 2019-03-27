package com.wuky.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.wuky.dubbo.service.TbUserDubboService;
import com.wuky.mapper.TbUserMapper;
import com.wuky.pojo.TbUser;
import com.wuky.pojo.TbUserExample;


public class TbUserDubboServiceImpl implements TbUserDubboService
{
	@Resource
	private TbUserMapper tbUserMapper;

	public TbUser selByUser(final TbUser user)
	{
		final TbUserExample example = new TbUserExample();
		example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
		final List<TbUser> list = tbUserMapper.selectByExample(example);
		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

	public int insByUser(final TbUser user)
	{
		return tbUserMapper.insertSelective(user);
	}

	public boolean selUserInfo(final String param, final int type)
	{
		final TbUserExample example = new TbUserExample();
		if (type == 1)
		{
			example.createCriteria().andUsernameEqualTo(param);
		}
		else if (type == 2)
		{
			example.createCriteria().andEmailEqualTo(param);
		}

		final List<TbUser> list = tbUserMapper.selectByExample(example);

		if (list != null && list.size() > 0)
		{
			return false;
		}
		return true;
	}



}
