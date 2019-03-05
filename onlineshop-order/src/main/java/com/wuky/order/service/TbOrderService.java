package com.wuky.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wuky.commons.pojo.TbItemChild;
import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.order.pojo.MyOrderParam;


public interface TbOrderService
{
	/**
	 * 提交订单里面包含的商品
	 *
	 * @param id
	 * @return
	 */
	List<TbItemChild> showOrderCart(List<Long> id, HttpServletRequest request);

	/**
	 * 创建订单
	 *
	 * @param param
	 * @return
	 */
	onlineshopResult create(MyOrderParam param, HttpServletRequest request);
}
