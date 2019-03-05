package com.wuky.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wuky.dubbo.service.TbItemCatDubboService;
import com.wuky.item.pojo.PortalMenu;
import com.wuky.item.pojo.PortalMenuNode;
import com.wuky.item.service.TbItemCatService;
import com.wuky.pojo.TbItemCat;


@Service
public class TbItemCatServiceImpl implements TbItemCatService
{
	@Reference
	private TbItemCatDubboService tbItemCatDubboServiceImpl;

	public PortalMenu showCatMenu()
	{
		//查询所有一级菜单
		final List<TbItemCat> list = tbItemCatDubboServiceImpl.show(0);
		final PortalMenu pm = new PortalMenu();
		pm.setData(selAllMenu(list));
		return pm;
	}

	public List<Object> selAllMenu(final List<TbItemCat> list)
	{
		final List<Object> listNode = new ArrayList<Object>();
		for (final TbItemCat tbItemCat : list)
		{
			if (tbItemCat.getIsParent())
			{
				final PortalMenuNode pmd = new PortalMenuNode();
				pmd.setU("/products/" + tbItemCat.getId() + ".html");
				pmd.setN("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				pmd.setI(selAllMenu(tbItemCatDubboServiceImpl.show(tbItemCat.getId())));
				listNode.add(pmd);
			}
			else
			{
				listNode.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
			}

		}

		return listNode;
	}
}
