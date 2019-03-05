package com.wuky.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.wuky.dubbo.service.TbOrderDubboService;
import com.wuky.mapper.TbOrderItemMapper;
import com.wuky.mapper.TbOrderMapper;
import com.wuky.mapper.TbOrderShippingMapper;
import com.wuky.pojo.TbOrder;
import com.wuky.pojo.TbOrderItem;
import com.wuky.pojo.TbOrderShipping;


public class TbOrderDubboServiceImpl implements TbOrderDubboService
{
	@Resource
	private TbOrderMapper tbOrderMapper;

	@Resource
	private TbOrderItemMapper tbOrderItemMapper;

	@Resource
	private TbOrderShippingMapper tbOrderShippingMapper;

	public int insOrder(final TbOrder tbOrder, final List<TbOrderItem> list, final TbOrderShipping tbOrderShipping)
			throws Exception
	{
		int index = tbOrderMapper.insertSelective(tbOrder);
		for (final TbOrderItem tbOrderItem : list)
		{
			index += tbOrderItemMapper.insertSelective(tbOrderItem);
		}

		index += tbOrderShippingMapper.insertSelective(tbOrderShipping);

		if (index == 2 + list.size())
		{
			return 1;
		}
		else
		{
			throw new Exception("创建订单失败");
		}
	}

}
