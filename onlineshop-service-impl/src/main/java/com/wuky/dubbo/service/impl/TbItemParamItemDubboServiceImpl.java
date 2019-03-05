package com.wuky.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.wuky.dubbo.service.TbItemParamItemDubboService;
import com.wuky.mapper.TbItemParamItemMapper;
import com.wuky.pojo.TbItemParamItem;
import com.wuky.pojo.TbItemParamItemExample;


public class TbItemParamItemDubboServiceImpl implements TbItemParamItemDubboService
{
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;

	public TbItemParamItem selByItemid(final long itemId)
	{
		final TbItemParamItemExample example = new TbItemParamItemExample();
		example.createCriteria().andItemIdEqualTo(itemId);
		final List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(example);

		if (list != null && list.size() > 0)
		{
			return list.get(0);
		}
		return null;
	}

}
