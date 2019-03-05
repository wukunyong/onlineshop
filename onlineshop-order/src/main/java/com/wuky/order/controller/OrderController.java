package com.wuky.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.order.pojo.MyOrderParam;
import com.wuky.order.service.TbOrderService;


@Controller
public class OrderController
{
	@Resource
	private TbOrderService tbOrderServiceImpl;

	/**
	 * 显示确认订单商品信息
	 *
	 * @param model
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping("order/order-cart.html")
	public String showCartOrder(final Model model, @RequestParam("id") final List<Long> ids, final HttpServletRequest request)
	{
		model.addAttribute("cartList", tbOrderServiceImpl.showOrderCart(ids, request));
		return "order-cart";
	}

	@RequestMapping("order/create.html")
	public String createOrder(final MyOrderParam param, final HttpServletRequest request)
	{
		final onlineshopResult result = tbOrderServiceImpl.create(param, request);
		if (result.getStatus() == 200)
		{
			return "my-orders";
		}
		else
		{
			request.setAttribute("message", "无法创建订单！！！");
			return "error/exception";
		}
	}

}
