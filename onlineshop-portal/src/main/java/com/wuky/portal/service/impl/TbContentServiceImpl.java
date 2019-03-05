package com.wuky.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.commons.utils.JsonUtils;
import com.wuky.dubbo.service.TbContentDubboService;
import com.wuky.pojo.TbContent;
import com.wuky.portal.service.TbContentService;
import com.wuky.redis.dao.JedisDao;


@Service
public class TbContentServiceImpl implements TbContentService
{

	@Reference
	TbContentDubboService tbContentDubboServiceImpl;
	@Resource
	private JedisDao JedisDaoImpl;
	@Value("${redis.bigpic.key}")
	private String key;

	public String showBigPic()
	{
		//先判断在redis中是否存在

		if (JedisDaoImpl.exists(key))
		{
			final String value = JedisDaoImpl.get(key);
			if (value != null && !value.equals(""))
			{
				return value;
			}
		}


		//如果不存在从mysql中取,取完后放入redis中
		final List<TbContent> list = tbContentDubboServiceImpl.selByCount(6, true);

		final List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();
		for (final TbContent tc : list)
		{
			final Map<String, Object> map = new HashMap<String, Object>();

			map.put("srcB", tc.getPic2());
			map.put("height", 240);
			map.put("alt", "对不起,加载图片失败");
			map.put("width", 670);
			map.put("src", tc.getPic());
			map.put("widthB", 550);
			map.put("href", tc.getUrl());
			map.put("heightB", 240);

			listResult.add(map);
		}
		final String listJson = JsonUtils.objectToJson(listResult);
		//把数据放入到redis中
		JedisDaoImpl.set(key, listJson);
		return listJson;
	}

}
