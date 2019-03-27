package com.wuky.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.commons.pojo.TbItemChild;
import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.commons.utils.CookieUtils;
import com.wuky.commons.utils.HttpClientUtil;
import com.wuky.commons.utils.IDUtils;
import com.wuky.commons.utils.JsonUtils;
import com.wuky.dubbo.service.TbItemDubboService;
import com.wuky.dubbo.service.TbOrderDubboService;
import com.wuky.order.pojo.MyOrderParam;
import com.wuky.order.service.TbOrderService;
import com.wuky.pojo.TbItem;
import com.wuky.pojo.TbOrder;
import com.wuky.pojo.TbOrderItem;
import com.wuky.pojo.TbOrderShipping;
import com.wuky.redis.dao.JedisDao;


@Service
public class TbOrderServiceImpl implements TbOrderService
{
	@Resource
	private JedisDao jedisDaoImpl;

	@Value("${cart.key}")
	private String cartKey;

	@Value("${passport.url}")
	private String passportUrl;

	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;

	@Reference
	private TbOrderDubboService tbOrderDubboServiceImpl;

	public List<TbItemChild> showOrderCart(final List<Long> ids, final HttpServletRequest request)
	{
		final String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		final String jsonUser = HttpClientUtil.doPost(passportUrl + token);
		final onlineshopResult oResult = JsonUtils.jsonToPojo(jsonUser, onlineshopResult.class);
		//redis中的key
		final String key = cartKey + ((LinkedHashMap) oResult.getData()).get("username");

		final String json = jedisDaoImpl.get(key);
		final List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);

		final List<TbItemChild> newlist = new ArrayList<TbItemChild>();
		for (final TbItemChild child : list)
		{
			for (final Long id : ids)
			{
				final long id1 = child.getId();
				final long id2 = id;
				if (id1 == id2)
				{
					//判断购买量是否大于等于库存
					final TbItem tbItem = tbItemDubboServiceImpl.selById(id);
					if (tbItem.getNum() >= child.getNum())
					{
						child.setEnough(true);
					}
					else
					{
						child.setEnough(false);
					}
					newlist.add(child);
				}
			}
		}
		return newlist;
	}

	public onlineshopResult create(final MyOrderParam param, final HttpServletRequest request)
	{
		//订单表数据
		final TbOrder tbOrder = new TbOrder();
		tbOrder.setPayment(param.getPayment());
		tbOrder.setPaymentType(param.getPaymentType());
		tbOrder.setOrderId(param.getOrderid());
		final Date date = new Date();
		tbOrder.setCreateTime(date);
		tbOrder.setUpdateTime(date);

		final String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		final String result = HttpClientUtil.doPost(passportUrl + token);
		final onlineshopResult oResult = JsonUtils.jsonToPojo(result, onlineshopResult.class);
		final Map user = (LinkedHashMap) oResult.getData();

		tbOrder.setUserId(Long.parseLong(user.get("id").toString()));
		tbOrder.setBuyerNick(user.get("username").toString());
		tbOrder.setBuyerRate(0);

		//订单-商品表
		for (final TbOrderItem item : param.getOrderItems())
		{
			item.setId(IDUtils.genItemId() + "");
			item.setOrderId(param.getOrderid());
		}

		//收货人信息
		final TbOrderShipping tbOrderShipping = param.getOrderShipping();
		tbOrderShipping.setOrderId(param.getOrderid());
		tbOrderShipping.setCreated(date);
		tbOrderShipping.setUpdated(date);

		final onlineshopResult or = new onlineshopResult();
		try
		{
			final int index = tbOrderDubboServiceImpl.insOrder(tbOrder, param.getOrderItems(), tbOrderShipping);
			if (index > 0)
			{
				or.setStatus(200);
				//删除购买的商品
				final String json = jedisDaoImpl.get(cartKey + user.get("username"));
				final List<TbItemChild> listCart = JsonUtils.jsonToList(json, TbItemChild.class);
				final List<TbItemChild> listNew = new ArrayList<TbItemChild>();
				for (final TbItemChild child : listCart)
				{
					for (final TbOrderItem item : param.getOrderItems())
					{
						System.out.println("1" + child.getId().longValue());
						System.out.println("2" + Long.parseLong(item.getItemId()));

						if (child.getId().longValue() == Long.parseLong(item.getItemId()))
						{
							listNew.add(child);
						}
					}
				}
				for (final TbItemChild mynew : listNew)
				{
					listCart.remove(mynew);
				}
				jedisDaoImpl.set(cartKey + user.get("username"), JsonUtils.objectToJson(listCart));
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return or;
	}

}
