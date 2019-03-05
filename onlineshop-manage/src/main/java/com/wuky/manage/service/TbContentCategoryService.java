package com.wuky.manage.service;

import java.util.List;

import com.wuky.commons.pojo.EasyUiTree;
import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.pojo.TbContentCategory;


public interface TbContentCategoryService
{
	/**
	 * 查询所有类目并转换为EasyUiTree的属性要求
	 *
	 * @return
	 */
	List<EasyUiTree> showCategory(long id);

	/**
	 * 类目新增
	 *
	 * @param cate
	 * @return
	 */
	onlineshopResult create(TbContentCategory cate);

	/**
	 * 类目重命名
	 *
	 * @param cate
	 * @return
	 */
	onlineshopResult update(TbContentCategory cate);

	/**
	 * 删除类目
	 * 
	 * @param id
	 * @return
	 */
	onlineshopResult delete(TbContentCategory cate);
}
