package com.wuky.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.dubbo.service.TbItemDubboService;
import com.wuky.mapper.TbItemDescMapper;
import com.wuky.mapper.TbItemMapper;
import com.wuky.mapper.TbItemParamItemMapper;
import com.wuky.pojo.TbItem;
import com.wuky.pojo.TbItemDesc;
import com.wuky.pojo.TbItemExample;
import com.wuky.pojo.TbItemParamItem;


public class TbItemDubboServiceImpl implements TbItemDubboService
{
	@Resource
	TbItemMapper tbItemMapper;
	@Resource
	TbItemDescMapper tbItemDescMapper;
	@Resource
	TbItemParamItemMapper tbItemParamItemMapper;

	public EasyUIDataGrid show(final int page, final int rows)
	{
		PageHelper.startPage(page, rows);
		//查询全部
		final List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());

		//分页代码
		//设置分页条件
		final PageInfo<TbItem> pi = new PageInfo<TbItem>(list);

		//放入到实体类
		final EasyUIDataGrid datagrid = new EasyUIDataGrid();
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		return datagrid;
	}

	public int updItemStatus(final TbItem tbItem)
	{
		return tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}

	public int insTbItem(final TbItem tbItem)
	{
		return tbItemMapper.insert(tbItem);
	}

	public int insTbItemDesc(final TbItem tbItem, final TbItemDesc desc, final TbItemParamItem paramItem) throws Exception
	{
		int index = 0;
		try
		{
			index = tbItemMapper.insertSelective(tbItem);
			index += tbItemDescMapper.insertSelective(desc);
			index += tbItemParamItemMapper.insertSelective(paramItem);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		if (index == 3)
		{
			return 1;
		}
		else
		{
			throw new Exception("新增失败,数据还原");
		}
	}

	public List<TbItem> selAllByStatus(final byte status)
	{

		final TbItemExample example = new TbItemExample();
		example.createCriteria().andStatusEqualTo(status);
		return tbItemMapper.selectByExample(example);
	}

	public TbItem selById(final long id)
	{
		return tbItemMapper.selectByPrimaryKey(id);
	}

}
