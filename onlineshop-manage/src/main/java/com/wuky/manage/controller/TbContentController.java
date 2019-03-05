package com.wuky.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.manage.service.TbContentService;
import com.wuky.pojo.TbContent;


@Controller
public class TbContentController
{
	@Resource
	TbContentService tbContentServiceImpl;

	/**
	 * 显示内容信息
	 *
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("content/query/list")
	@ResponseBody
	public EasyUIDataGrid showContent(final long categoryId, final int page, final int rows)
	{
		return tbContentServiceImpl.showContent(categoryId, page, rows);

	}

	/**
	 * 内容新增
	 * 
	 * @param content
	 * @return
	 */
	@RequestMapping("content/save")
	@ResponseBody
	public onlineshopResult save(final TbContent content)
	{
		final onlineshopResult or = new onlineshopResult();
		final int index = tbContentServiceImpl.save(content);
		if (index > 0)
		{
			or.setStatus(200);
		}
		return or;

	}
}
