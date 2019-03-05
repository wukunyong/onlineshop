package com.wuky.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.wuky.dubbo.service.TbItemCatDubboService;
import com.wuky.mapper.TbItemCatMapper;
import com.wuky.pojo.TbItemCat;
import com.wuky.pojo.TbItemCatExample;


public class TbItemCatDubboServiceImpl implements TbItemCatDubboService
{
	@Resource
	private TbItemCatMapper tbItemCatMapper;

	public List<TbItemCat> show(final long pid)
	{
		final TbItemCatExample example = new TbItemCatExample();
		example.createCriteria().andParentIdEqualTo(pid);
		return tbItemCatMapper.selectByExample(example);
	}

	public TbItemCat selById(final long id)
	{
		return tbItemCatMapper.selectByPrimaryKey(id);
	}

}
