package com.wuky.item.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.commons.pojo.TbItemChild;
import com.wuky.commons.utils.JsonUtils;
import com.wuky.dubbo.service.TbItemDubboService;
import com.wuky.item.service.TbItemService;
import com.wuky.pojo.TbItem;
import com.wuky.redis.dao.JedisDao;


@Service
public class TbItemServiceImpl implements TbItemService
{

	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;

	@Resource
	private JedisDao jedisDaoImpl;

	@Value("${redis.item.key}")
	private String itemKey;

	@SuppressWarnings("unlikely-arg-type")
	public TbItemChild show(final long id)
	{
		final String key = itemKey + id;
		if (jedisDaoImpl.equals(key))
		{
			final String json = jedisDaoImpl.get(key);
			if (json != null && !json.equals(""))
			{
				return JsonUtils.jsonToPojo(json, TbItemChild.class);
			}
		}

		final TbItem item = tbItemDubboServiceImpl.selById(id);
		final TbItemChild child = new TbItemChild();
		child.setId(item.getId());
		child.setTitle(item.getTitle());
		child.setPrice(item.getPrice());
		child.setSellPoint(item.getSellPoint());
		child.setImages(item.getImage() != null && !item.equals("") ? item.getImage().split(",") : new String[1]);
		//存到数据库中
		jedisDaoImpl.set(key, JsonUtils.objectToJson(child));
		return child;
	}
}
