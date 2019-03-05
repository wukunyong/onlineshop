package com.wuky.dubbo.service.impl;

import javax.annotation.Resource;

import com.wuky.dubbo.service.TbItemDescDubboService;
import com.wuky.mapper.TbItemDescMapper;
import com.wuky.pojo.TbItemDesc;


public class TbItemDescDubboServiceImpl implements TbItemDescDubboService
{
	@Resource
	private TbItemDescMapper tbItemDescMapper;

	public int insDesc(final TbItemDesc tbItemDesc)
	{
		return tbItemDescMapper.insert(tbItemDesc);
	}

	public TbItemDesc selByItemid(final long itemid)
	{
		return tbItemDescMapper.selectByPrimaryKey(itemid);
	}

}
