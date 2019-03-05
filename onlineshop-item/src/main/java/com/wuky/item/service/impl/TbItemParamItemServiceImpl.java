package com.wuky.item.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.commons.utils.JsonUtils;
import com.wuky.dubbo.service.TbItemParamItemDubboService;
import com.wuky.item.pojo.ParamItem;
import com.wuky.item.service.TbItemParamItemService;
import com.wuky.pojo.TbItemParamItem;


@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService
{
	@Reference
	private TbItemParamItemDubboService tbItemParamItemDubboServiceImpl;

	public String showParam(final long itemId)
	{
		final TbItemParamItem item = tbItemParamItemDubboServiceImpl.selByItemid(itemId);
		final List<ParamItem> list = JsonUtils.jsonToList(item.getParamData(), ParamItem.class);
		System.out.println(list + "1");
		final StringBuffer sBuffer = new StringBuffer();
		for (final ParamItem paramItem : list)
		{
			sBuffer.append("<table width='500' style='color:gray;'>");

			for (int i = 0; i < paramItem.getParams().size(); i++)
			{
				if (i == 0)
				{
					sBuffer.append("<tr>");
					sBuffer.append("<td align='right' width='30%'>" + paramItem.getGroup() + "</td>");
					sBuffer.append("<td align='right' width='30%'>" + paramItem.getParams().get(i).getK() + "</td>");
					sBuffer.append("<td>" + paramItem.getParams().get(i).getV() + "</td>");
					sBuffer.append("<tr/>");
				}
				else
				{
					sBuffer.append("<tr>");
					sBuffer.append("<td> </td>");
					sBuffer.append("<td align='right'>" + paramItem.getParams().get(i).getK() + "</td>");
					sBuffer.append("<td>" + paramItem.getParams().get(i).getV() + "</td>");
					sBuffer.append("</tr>");
				}
			}
			sBuffer.append("</table>");
			sBuffer.append("<hr style='color:gray;'/>");
		}
		return sBuffer.toString();
	}

}
