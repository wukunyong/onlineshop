package com.wuky.manage.service;

import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.commons.pojo.onlineshopResult;
import com.wuky.pojo.TbItemParam;


public interface TbItemParamService
{
	/**
	 * 分页显示商品规格参数
	 *
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showPage(int page, int rows);

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	int delete(String ids) throws Exception;

	/**
	 * 根据类目查询模板信息
	 *
	 * @param catId
	 * @return
	 */
	onlineshopResult showParam(long catId);

	/**
	 * 新增模板信息
	 *
	 * @param param
	 * @return
	 */
	onlineshopResult save(TbItemParam param);
}
