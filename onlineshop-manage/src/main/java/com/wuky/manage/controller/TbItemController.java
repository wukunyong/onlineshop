package com.wuky.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.manage.service.TbItemService;
import com.wuky.pojo.TbItem;


@Controller
public class TbItemController
{
	@Resource
	private TbItemService tbItemServiceImpl;

	/**
	 * 分页显示商品
	 *
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/list")
	@ResponseBody
	public EasyUIDataGrid show(final int page, final int rows)
	{
		return tbItemServiceImpl.show(page, rows);
	}

	/**
	 * 显示商品修改
	 *
	 * @return
	 */
	@RequestMapping("rest/page/item-edit")
	public String showItemEdit()
	{
		return "item-edit";
	}

	/**
	 * 商品删除
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/delete")
	public onlineshopResult delete(final String ids)
	{
		final onlineshopResult oResult = new onlineshopResult();
		final int index = tbItemServiceImpl.update(ids, (byte) 3);
		if (index == 1)
		{
			oResult.setStatus(200);
		}
		return oResult;

	}

	/**
	 * 商品上架
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/reshelf")
	public onlineshopResult reshelf(final String ids)
	{
		final onlineshopResult oResult = new onlineshopResult();
		final int index = tbItemServiceImpl.update(ids, (byte) 1);
		if (index == 1)
		{
			oResult.setStatus(200);
		}
		return oResult;
	}

	/**
	 * 商品下架
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping("/rest/item/instock")
	public onlineshopResult instock(final String ids)
	{
		final onlineshopResult oResult = new onlineshopResult();
		final int index = tbItemServiceImpl.update(ids, (byte) 2);
		if (index == 1)
		{
			oResult.setStatus(200);
		}
		return oResult;

	}

	/**
	 * 商品新增
	 *
	 * @param tbItem
	 * @param desc
	 * @return
	 */
	@RequestMapping("item/save")
	@ResponseBody
	public onlineshopResult insert(final TbItem tbItem, final String desc, final String itemParams)
	{
		final onlineshopResult or = new onlineshopResult();
		int index;
		try
		{
			index = tbItemServiceImpl.save(tbItem, desc, itemParams);
			if (index == 1)
			{
				or.setStatus(200);
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
			or.setData(e.getMessage());
		}

		return or;
	}
}
