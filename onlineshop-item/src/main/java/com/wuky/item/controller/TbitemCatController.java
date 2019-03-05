package com.wuky.item.controller;

import javax.annotation.Resource;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuky.item.service.TbItemCatService;


@Controller
public class TbitemCatController
{
	@Resource
	private TbItemCatService tbItemCatServiceImpl;

	/**
	 * 返回jsonp数据格式，里面包含所有菜单信息
	 *
	 * @param callback
	 * @return
	 */
	@RequestMapping("rest/itemcat/all")
	@ResponseBody
	public MappingJacksonValue showMenu(final String callback)
	{
		final MappingJacksonValue mjv = new MappingJacksonValue(tbItemCatServiceImpl.showCatMenu());
		mjv.setJsonpFunction(callback);
		return mjv;
	}
}
