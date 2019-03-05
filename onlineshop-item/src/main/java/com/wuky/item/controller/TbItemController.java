package com.wuky.item.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuky.item.service.TbItemService;


@Controller
public class TbItemController
{
	@Resource
	private TbItemService tbItemServiceImpl;

	/**
	 * 显示商品详情
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("item/{id}.html")
	public String showItemDetails(@PathVariable final long id, final Model model)
	{
		model.addAttribute("item", tbItemServiceImpl.show(id));
		return "item";

	}

}
