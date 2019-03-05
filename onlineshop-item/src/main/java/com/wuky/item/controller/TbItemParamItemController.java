package com.wuky.item.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuky.item.service.TbItemParamItemService;


@Controller
public class TbItemParamItemController
{
	@Resource
	private TbItemParamItemService tbItemParamItemServiceImpl;

	/**
	 * 显示商品规格参数
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "item/param/{id}.html", produces = "text/html;charset=utf-8")
	@ResponseBody
	public String param(@PathVariable final long id)
	{
		return tbItemParamItemServiceImpl.showParam(id);
	}
}
