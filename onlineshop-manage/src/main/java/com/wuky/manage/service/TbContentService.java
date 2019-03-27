package com.wuky.manage.service;

import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.pojo.TbContent;


public interface TbContentService
{
	/**
	 * 内容分页显示
	 *
	 * @param categoryId
	 * @param pahe
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showContent(long categoryId, int pahe, int rows);

	/**
	 * 内容新增
	 *
	 * @param content
	 * @return
	 */
	int save(TbContent content);


}
