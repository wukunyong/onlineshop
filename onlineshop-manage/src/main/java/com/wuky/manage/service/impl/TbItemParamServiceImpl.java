package com.wuky.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.dubbo.service.TbItemCatDubboService;
import com.wuky.dubbo.service.TbItemParamDubboService;
import com.wuky.manage.pojo.TbItemParamChild;
import com.wuky.manage.service.TbItemParamService;
import com.wuky.pojo.TbItemParam;


@Service
public class TbItemParamServiceImpl implements TbItemParamService
{
	//dubbo接口实现用resources，项目接口实现用@Reference
	@Reference
	private TbItemParamDubboService tbItemParamDubboServiceImpl;
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;

	public EasyUIDataGrid showPage(final int page, final int rows)
	{
		final EasyUIDataGrid dataGrid = tbItemParamDubboServiceImpl.showPage(page, rows);
		final List<TbItemParam> list = (List<TbItemParam>) dataGrid.getRows();
		final List<TbItemParamChild> listchild = new ArrayList<TbItemParamChild>();
		for (final TbItemParam param : list)
		{
			final TbItemParamChild child = new TbItemParamChild();
			child.setCreated(param.getCreated());
			child.setId(param.getId());
			child.setItemCatId(param.getItemCatId());
			child.setParamData(param.getParamData());
			child.setUpdated(param.getUpdated());
			child.setItemCatName(tbItemCatDubboServiceImpl.selById(child.getItemCatId()).getName());
			listchild.add(child);
		}
		dataGrid.setRows(listchild);
		return dataGrid;
	}

	public int delete(final String ids) throws Exception
	{
		return tbItemParamDubboServiceImpl.delByIds(ids);
	}

	public onlineshopResult showParam(final long catId)
	{
		final onlineshopResult or = new onlineshopResult();
		final TbItemParam param = tbItemParamDubboServiceImpl.selByCatid(catId);
		if (param != null)
		{
			or.setStatus(200);
			or.setData(param);
		}
		return or;
	}

	public onlineshopResult save(final TbItemParam param)
	{
		final Date date = new Date();
		param.setCreated(date);
		param.setUpdated(date);
		final int index = tbItemParamDubboServiceImpl.insParam(param);
		final onlineshopResult or = new onlineshopResult();
		if (index > 0)
		{
			or.setStatus(200);
		}
		return or;
	}

}
