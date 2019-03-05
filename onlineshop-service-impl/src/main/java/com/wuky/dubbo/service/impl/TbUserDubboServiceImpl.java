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

}
