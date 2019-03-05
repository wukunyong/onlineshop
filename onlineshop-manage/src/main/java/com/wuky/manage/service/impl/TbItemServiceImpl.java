package com.wuky.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.commons.utils.HttpClientUtil;
import com.wuky.commons.utils.IDUtils;
import com.wuky.commons.utils.JsonUtils;
import com.wuky.dubbo.service.TbItemDescDubboService;
import com.wuky.dubbo.service.TbItemDubboService;
import com.wuky.manage.service.TbItemService;
import com.wuky.pojo.TbItem;
import com.wuky.pojo.TbItemDesc;
import com.wuky.pojo.TbItemParamItem;
import com.wuky.redis.dao.JedisDao;


@Service
public class TbItemServiceImpl implements TbItemService
{
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Reference
	private TbItemDescDubboService tbItemDescDubboServiceImpl;

	@Value("${search.url}")
	private String url;

	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.item.key}")
	private String itemKey;

	public EasyUIDataGrid show(final int page, final int rows)
	{
		return tbItemDubboServiceImpl.show(page, rows);
	}

	public int update(final String ids, final byte status)
	{
		int index = 0;
		final TbItem item = new TbItem();
		final String[] idsStr = ids.split(",");
		for (final String id : idsStr)
		{
			item.setId(Long.parseLong(id));
			item.setStatus(status);
			index += tbItemDubboServiceImpl.updItemStatus(item);

			if (status == 2 || status == 3)
			{
				jedisDaoImpl.del(itemKey + id);
			}
		}
		if (index == idsStr.length)
		{
			return 1;
		}
		return 0;
	}

	public int save(final TbItem tbItem, final String desc, final String itemParams) throws Exception
	{
		final TbItem itemFinal = tbItem;
		final String descFinal = desc;

		//不考虑事务回滚
		/*
		 * final long id = IDUtils.genItemId(); tbItem.setId(id); final Date date = new Date(); tbItem.setCreated(date);
		 * tbItem.setUpdated(date); tbItem.setStatus((byte) 1); int index = tbItemDubboServiceImpl.insTbItem(tbItem); if
		 * (index > 0) { final TbItemDesc tbItemDesc = new TbItemDesc(); tbItemDesc.setItemDesc(desc);
		 * tbItemDesc.setItemId(id); tbItemDesc.setCreated(date); tbItemDesc.setUpdated(date); index +=
		 * tbItemDescDubboServiceImpl.insDesc(tbItemDesc); } if (index == 2) { return 1; }
		 */

		//调用dubbo考虑事务回滚的方法
		final long id = IDUtils.genItemId();
		tbItem.setId(id);
		final Date date = new Date();
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		tbItem.setStatus((byte) 1);

		final TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setItemId(id);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);

		final TbItemParamItem paramItem = new TbItemParamItem();
		paramItem.setCreated(date);
		paramItem.setUpdated(date);
		paramItem.setItemId(id);
		paramItem.setParamData(itemParams);

		int index = 0;
		index = tbItemDubboServiceImpl.insTbItemDesc(tbItem, tbItemDesc, paramItem);
		System.out.println("index:" + index);

		new Thread()
		{
			@Override
			public void run()
			{
				final Map<String, Object> map = new HashMap<String, Object>();
				map.put("item", itemFinal);
				map.put("desc", descFinal);

				HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(map));
				//使用java代码调用其它项目的控制器
			};
		}.start();



		return index;
	}

}
