package com.wuky.manage.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuky.commons.pojo.EasyUiTree;
import com.wuky.manage.service.TbItemCatService;


@Controller
public class TbItemCatController
{
	@Resource
	private TbItemCatService tbItemCatServiceImpl;

	@RequestMapping("item/cat/list")
	@ResponseBody
	public List<EasyUiTree> showCat(@RequestParam(defaultValue = "0") final long id)
	{
		return tbItemCatServiceImpl.show(id);

	}

}
