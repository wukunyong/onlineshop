package com.wuky.manage.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuky.commons.pojo.EasyUiTree;
import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.manage.service.TbContentCategoryService;
import com.wuky.pojo.TbContentCategory;


@Controller
public class TbContentCategoryController
{
	@Resource
	TbContentCategoryService tbContentCategoryServiceImpl;

	/**
	 * 查询商品类目
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("content/category/list")
	@ResponseBody
	//@RequestParam(defaultValue = "0")设置id默认值为0
	public List<EasyUiTree> showCategory(@RequestParam(defaultValue = "0") final long id)
	{
		return tbContentCategoryServiceImpl.showCategory(id);
	}

	/**
	 * 新增内容类目
	 *
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/create")
	@ResponseBody
	public onlineshopResult create(final TbContentCategory cate)
	{
		return tbContentCategoryServiceImpl.create(cate);
	}

	/**
	 * 重命名
	 *
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/update")
	@ResponseBody
	public onlineshopResult update(final TbContentCategory cate)
	{
		return tbContentCategoryServiceImpl.update(cate);

	}

	/**
	 * 类目删除
	 * 
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/delete")
	@ResponseBody
	public onlineshopResult delete(final TbContentCategory cate)
	{
		return tbContentCategoryServiceImpl.delete(cate);

	}
}
