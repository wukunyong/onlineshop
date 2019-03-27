package com.wuky.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.dubbo.service.TbContentDubboService;
import com.wuky.mapper.TbContentMapper;
import com.wuky.pojo.TbContent;
import com.wuky.pojo.TbContentExample;


public class TbContentDubboServiceImpl implements TbContentDubboService
{
	@Resource
	private TbContentMapper tbContentMapper;

	public EasyUIDataGrid selContentByPage(final long categoryId, final int page, final int rows)
	{
		PageHelper.startPage(page, rows);
		final TbContentExample example = new TbContentExample();
		if (categoryId != 0)
		{
			example.createCriteria().andCategoryIdEqualTo(categoryId);
		}
		final List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);

		final PageInfo<TbContent> pi = new PageInfo<TbContent>(list);

		final EasyUIDataGrid datagrid = new EasyUIDataGrid();
		datagrid.setRows(pi.getList());
		datagrid.setTotal(pi.getTotal());
		return datagrid;
	}

	public int insTbContent(final TbContent content)
	{
		return tbContentMapper.insertSelective(content);
	}

	public List<TbContent> selByCount(final int count, final boolean isSort)
	{
		final TbContentExample example = new TbContentExample();

		if (isSort)
		{
			example.setOrderByClause("updated desc");
		}
		if (count != 0)
		{
			PageHelper.startPage(1, count);
			final List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			final PageInfo<TbContent> pInfo = new PageInfo<TbContent>(list);
			return pInfo.getList();
		}
		else
		{
			return tbContentMapper.selectByExampleWithBLOBs(example);
		}
	}



}
