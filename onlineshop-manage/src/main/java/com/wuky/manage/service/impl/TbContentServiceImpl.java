package com.wuky.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.commons.utils.JsonUtils;
import com.wuky.dubbo.service.TbContentDubboService;
import com.wuky.manage.service.TbContentService;
import com.wuky.pojo.TbContent;
import com.wuky.redis.dao.JedisDao;


@Service
public class TbContentServiceImpl implements TbContentService
{
	@Reference
	private TbContentDubboService tbContentDubboServiceImpl;

	@Resource
	private JedisDao jedisDaoImpl;

	@Value("${redis.bigpic.key}")
	private String key;

	public EasyUIDataGrid showContent(final long categoryId, final int page, final int rows)
	{
		return tbContentDubboServiceImpl.selContentByPage(categoryId, page, rows);
	}

	public int save(final TbContent content)
	{
		final Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);

		final int index = tbContentDubboServiceImpl.insTbContent(content);

		//判断redis中是否有缓存数据
		if (jedisDaoImpl.exists(key))
		{
			final String value = jedisDaoImpl.get(key);
			if (value != null && !value.equals(""))
			{
				final List<HashMap> list = JsonUtils.jsonToList(value, HashMap.class);

				final HashMap<String, Object> map = new HashMap<String, Object>();

				map.put("srcB", content.getPic2());
				map.put("height", 240);
				map.put("alt", "对不起,加载图片失败");
				map.put("width", 670);
				map.put("src", content.getPic());
				map.put("widthB", 550);
				map.put("href", content.getUrl());
				map.put("heightB", 240);

				//保证6个
				if (list.size() == 6)
				{
					list.remove(5);
				}
				list.add(0, map);

				jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
			}
		}
		return index;
	}

}
