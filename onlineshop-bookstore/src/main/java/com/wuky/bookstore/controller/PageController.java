package com.wuky.bookstore.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wuky.bookstore.service.TbContentService;


@Controller
public class PageController
{
	@Resource
	private TbContentService tbContentServiceImpl;

	@RequestMapping("/")
	public String welcome()
	{
		return "forward:/showBigPic";

	}
}
