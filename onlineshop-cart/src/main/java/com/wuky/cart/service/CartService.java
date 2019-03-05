package com.wuky.cart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wuky.commons.pojo.TbItemChild;
import com.wuky.commons.pojo.onlineshopResult;


public interface CartService
{
	/**
	 * 加入购物车
	 *
	 * @param id
	 * @param num
	 */
	void addCart(long id, int num, HttpServletRequest request);

	/**
	 * 显示购物车
	 *
	 * @return
	 */
	List<TbItemChild> ShowCart(HttpServletRequest request);

	/**
	 * 根据id修改数量
	 *
	 * @param id
	 * @param num
	 * @return
	 */
	onlineshopResult update(long id, int num, HttpServletRequest request);

	/**
	 * 根据id删除购物车商品
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	onlineshopResult delete(long id, HttpServletRequest request);
}
