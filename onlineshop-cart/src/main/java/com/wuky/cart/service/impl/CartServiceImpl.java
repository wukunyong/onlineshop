package com.wuky.cart.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.cart.service.CartService;
import com.wuky.commons.pojo.TbItemChild;
import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.commons.utils.CookieUtils;
import com.wuky.commons.utils.HttpClientUtil;
import com.wuky.commons.utils.JsonUtils;
import com.wuky.dubbo.service.TbItemDubboService;
import com.wuky.pojo.TbItem;
import com.wuky.redis.dao.JedisDao;


@Service
public class CartServiceImpl implements CartService
{
	@Resource
	private JedisDao jedisDaoImpl;

	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;

	@Value("${passport.url}")
	private String passportUrl;

	@Value("${cart.key}")
	private String cartKey;

	@Value("${addcart.url}")
	private String addcartUrl;

	public void addCart(final long id, final int num, final HttpServletRequest request)
	{
		//集合中存放所有购物车商品
		List<TbItemChild> list = new ArrayList<TbItemChild>();

		final String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		final String jsonUser = HttpClientUtil.doPost(passportUrl + token);
		final onlineshopResult oResult = JsonUtils.jsonToPojo(jsonUser, onlineshopResult.class);
		//redis中的key
		final String key = cartKey + ((LinkedHashMap) oResult.getData()).get("username");

		if (jedisDaoImpl.exists(key))
		{
			final String json = jedisDaoImpl.get(key);
			if (json != null && !json.equals(""))
			{
				list = JsonUtils.jsonToList(json, TbItemChild.class);
				for (final TbItemChild tbItemChild : list)
				{
					if (tbItemChild.getId() == id)
					{
						//购物车中存在该商品
						tbItemChild.setNum(tbItemChild.getNum() + num);
						//重新添加到redis中
						jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
						return;
					}
				}
			}
		}

		//购物车中没有这个商品
		final TbItem tbItem = tbItemDubboServiceImpl.selById(id);
		final TbItemChild child = new TbItemChild();

		child.setId(tbItem.getId());
		child.setTitle(tbItem.getTitle());
		child.setImages(tbItem.getImage() == null || tbItem.getImage().equals("") ? new String[1] : tbItem.getImage().split(","));
		child.setNum(num);
		child.setPrice(tbItem.getPrice());

		list.add(child);

		jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
	}

	public List<TbItemChild> ShowCart(final HttpServletRequest request)
	{
		final String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		final String jsonUser = HttpClientUtil.doPost(passportUrl + token);
		final onlineshopResult oResult = JsonUtils.jsonToPojo(jsonUser, onlineshopResult.class);
		//redis中的key
		final String key = cartKey + ((LinkedHashMap) oResult.getData()).get("username");

		final String json = jedisDaoImpl.get(key);
		return JsonUtils.jsonToList(json, TbItemChild.class);
	}

	public onlineshopResult update(final long id, final int num, final HttpServletRequest request)
	{
		final String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		final String jsonUser = HttpClientUtil.doPost(passportUrl + token);
		final onlineshopResult oResult = JsonUtils.jsonToPojo(jsonUser, onlineshopResult.class);
		//redis中的key
		final String key = cartKey + ((LinkedHashMap) oResult.getData()).get("username");

		final String json = jedisDaoImpl.get(key);
		final List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);
		for (final TbItemChild child : list)
		{
			if (child.getId() == id)
			{
				child.setNum(num);

			}
		}
		final String ok = jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
		final onlineshopResult or = new onlineshopResult();
		if (or.equals("OK"))
		{
			or.setStatus(200);
		}
		return or;
	}

	public onlineshopResult delete(final long id, final HttpServletRequest request)
	{
		final String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		final String jsonUser = HttpClientUtil.doPost(passportUrl + token);
		final onlineshopResult oResult = JsonUtils.jsonToPojo(jsonUser, onlineshopResult.class);
		//redis中的key
		final String key = cartKey + ((LinkedHashMap) oResult.getData()).get("username");

		final String json = jedisDaoImpl.get(key);
		final List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);
		TbItemChild tbItemChild = null;
		for (final TbItemChild child : list)
		{
			if (child.getId() == id)
			{
				tbItemChild = child;
			}
		}
		list.remove(tbItemChild);
		final String ok = jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
		final onlineshopResult or = new onlineshopResult();
		if (ok.equals("OK"))
		{
			or.setStatus(200);
		}
		return or;
	}

}
