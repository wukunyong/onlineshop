package com.wuky.dubbo.service;

import java.util.List;

import com.wuky.pojo.TbOrder;
import com.wuky.pojo.TbOrderItem;
import com.wuky.pojo.TbOrderShipping;


public interface TbOrderDubboService
{
	/**
	 * 创建订单
	 *
	 * @param tbOrder
	 * @param list
	 * @param tbOrderShipping
	 * @return
	 */
	int insOrder(TbOrder tbOrder, List<TbOrderItem> list, TbOrderShipping tbOrderShipping) throws Exception;

}
