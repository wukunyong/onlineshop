package com.wuky.portal.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuky.portal.service.TbContentService;


@Controller
public class TbContentController
{
	@Resource
	private TbContentService tbContentServiceImpl;

	@RequestMapping("showBigPic")
	public String showBigPic(final Model model)
	{
		//这里一直抛空指针异常
		//model.addAttribute("ad1", tbContentServiceImpl.showBigPic());
		return "index";
	}
}
