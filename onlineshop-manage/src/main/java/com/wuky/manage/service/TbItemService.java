package com.wuky.manage.service;

import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.pojo.TbItem;


public interface TbItemService
{

	/**
	 * 显示商品
	 *
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid show(int page, int rows);

	/**
	 * 批量修改商品状态
	 *
	 * @param ids
	 * @param status
	 * @return
	 */
	int update(String ids, byte status);

	/**
	 * 商品新增
	 *
	 * @param tbItem
	 * @param desc
	 * @return
	 */
	int save(TbItem tbItem, String desc, String itemParams) throws Exception;
}