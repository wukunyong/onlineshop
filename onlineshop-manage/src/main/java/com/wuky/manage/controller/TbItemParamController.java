package com.wuky.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.manage.service.TbItemParamService;
import com.wuky.pojo.TbItemParam;


@Controller
public class TbItemParamController
{
	@Resource
	private TbItemParamService tbItemParamServiceImpl;

	/**
	 * 规格参数分页显示
	 *
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/param/list")
	@ResponseBody
	public EasyUIDataGrid showPage(final int page, final int rows)
	{
		return tbItemParamServiceImpl.showPage(page, rows);
	}

	/**
	 * 批量删除规格参数
	 *
	 * @param ids
	 * @return
	 */
	@RequestMapping("item/param/delete")
	@ResponseBody
	public onlineshopResult delete(final String ids)
	{
		final onlineshopResult or = new onlineshopResult();
		try
		{
			final int index = tbItemParamServiceImpl.delete(ids);
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

	/**
	 * 点击商品类目按钮显示添加分组按钮 判断类目是否已经添加模板
	 *
	 * @param catId
	 * @return
	 */
	@RequestMapping("item/param/query/itemcatid/{catId}")
	@ResponseBody
	public onlineshopResult show(@PathVariable final long catId)
	{
		return tbItemParamServiceImpl.showParam(catId);
	}

	/**
	 * 商品类目新增
	 * 
	 * @param param
	 * @param catId
	 * @return
	 */
	@RequestMapping("item/param/save/{catId}")
	@ResponseBody
	public onlineshopResult save(final TbItemParam param, @PathVariable final long catId)
	{
		param.setItemCatId(catId);
		return tbItemParamServiceImpl.save(param);

	}
}
