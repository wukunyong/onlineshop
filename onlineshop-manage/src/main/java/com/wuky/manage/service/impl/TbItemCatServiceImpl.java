package com.wuky.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.commons.pojo.EasyUiTree;
import com.wuky.dubbo.service.TbItemCatDubboService;
import com.wuky.manage.service.TbItemCatService;
import com.wuky.pojo.TbItemCat;


@Service
public class TbItemCatServiceImpl implements TbItemCatService
{
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;

	public List<EasyUiTree> show(final long pid)
	{
		final List<TbItemCat> list = tbItemCatDubboServiceImpl.show(pid);
		final List<EasyUiTree> listTree = new ArrayList<EasyUiTree>();
		for (final TbItemCat cat : list)
		{
			final EasyUiTree tree = new EasyUiTree();
			tree.setId(cat.getId());
			tree.setText(cat.getName());
			tree.setState(cat.getIsParent() ? "closed" : "open");
			listTree.add(tree);
		}
		return listTree;
	}

}
