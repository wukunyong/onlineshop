package com.wuky.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.commons.pojo.EasyUiTree;
import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.commons.utils.IDUtils;
import com.wuky.dubbo.service.TbContentCategoryDubboService;
import com.wuky.manage.service.TbContentCategoryService;
import com.wuky.pojo.TbContentCategory;


@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService
{
	@Reference
	TbContentCategoryDubboService tbContentCategoryDubboServiceImpl;

	public List<EasyUiTree> showCategory(final long id)
	{
		final List<EasyUiTree> listTree = new ArrayList<EasyUiTree>();
		final List<TbContentCategory> list = tbContentCategoryDubboServiceImpl.selByPid(id);
		for (final TbContentCategory cate : list)
		{
			final EasyUiTree tree = new EasyUiTree();

			tree.setId(cate.getId());
			tree.setText(cate.getName());
			tree.setState(cate.getIsParent() ? "closed" : "open");
			listTree.add(tree);

		}
		return listTree;
	}

	public onlineshopResult create(final TbContentCategory cate)
	{
		final onlineshopResult or = new onlineshopResult();

		//判断当前节点名称是否已经存在
		final List<TbContentCategory> children = tbContentCategoryDubboServiceImpl.selByPid(cate.getParentId());
		for (final TbContentCategory child : children)
		{
			if (child.getName().equals(cate.getName()))
			{
				return or;
			}
		}

		final Date date = new Date();
		cate.setCreated(date);
		cate.setUpdated(date);
		cate.setStatus(1);
		cate.setSortOrder(1);
		cate.setIsParent(false);
		final long id = IDUtils.genItemId();
		cate.setId(id);
		final int index = tbContentCategoryDubboServiceImpl.insTbContentCategory(cate);
		if (index > 0)
		{
			final TbContentCategory parent = new TbContentCategory();
			parent.setId(cate.getParentId());
			parent.setIsParent(true);
			tbContentCategoryDubboServiceImpl.updIsParentById(parent);
		}
		or.setStatus(200);
		final Map<String, Long> map = new HashMap<String, Long>();
		map.put("id", id);
		or.setData(map);
		return or;
	}

	public onlineshopResult update(final TbContentCategory cate)
	{
		final onlineshopResult or = new onlineshopResult();
		//查询当前节点信息
		final TbContentCategory cateSelect = tbContentCategoryDubboServiceImpl.selById(cate.getId());
		//查询当前节点的父节点的所有子节点的所有信息
		final List<TbContentCategory> children = tbContentCategoryDubboServiceImpl.selByPid(cateSelect.getParentId());
		for (final TbContentCategory child : children)
		{
			if (child.getName().equals(cate.getName()))
			{
				return or;
			}
		}

		final int index = tbContentCategoryDubboServiceImpl.updIsParentById(cate);
		if (index > 0)
		{
			or.setStatus(200);
		}
		return or;
	}

	public onlineshopResult delete(final TbContentCategory cate)
	{
		final onlineshopResult or = new onlineshopResult();

		cate.setStatus(0);
		final int index = tbContentCategoryDubboServiceImpl.updIsParentById(cate);
		if (index > 0)
		{
			final TbContentCategory curr = tbContentCategoryDubboServiceImpl.selById(cate.getId());
			final List<TbContentCategory> list = tbContentCategoryDubboServiceImpl.selByPid(curr.getParentId());
			if (list == null || list.size() == 0)
			{
				final TbContentCategory parent = new TbContentCategory();
				parent.setId(curr.getParentId());
				parent.setIsParent(false);
				final int result = tbContentCategoryDubboServiceImpl.updIsParentById(parent);
				if (result > 0)
				{
					or.setStatus(200);
				}
				else
				{
					or.setStatus(200);
				}
			}
		}
		return or;
	}

}
