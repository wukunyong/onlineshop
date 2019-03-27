package com.wuky.item.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.dubbo.service.TbItemDescDubboService;
import com.wuky.item.service.TbItemDescService;
import com.wuky.redis.dao.JedisDao;


@Service
public class TbItemDescServiceImpl implements TbItemDescService
{

	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;


	@Resource
	private JedisDao jedisDaoImpl;

	@Value("${redis.desc.key}")
	private String descKey;

	public String showDesc(final long itemId)
	{

		final String key = descKey + itemId;
		if (jedisDaoImpl.exists(key))
		{
			final String json = jedisDaoImpl.get(key);
			if (json != null && !json.equals(""))
			{
				return json;
			}
		}
		final String result = tbItemDescDubboServiceImpl.selByItemid(itemId).getItemDesc();
		jedisDaoImpl.set(key, result);
		return result;

		//return tbItemDescDubboServiceImpl.selByItemid(itemId).getItemDesc();
	}

}
