package com.wuky.dubbo.service;

import java.util.List;

import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.pojo.TbContent;


public interface TbContentDubboService
{
	/**
	 * 分页查询
	 *
	 * @param categoryid
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid selContentByPage(long categoryid, int page, int rows);

	/**
	 * 新增
	 *
	 * @param content
	 * @return
	 */
	int insTbContent(TbContent content);

	/**
	 * 查询出最近的前n个
	 * 
	 * @param count
	 * @param isSort
	 * @return
	 */
	List<TbContent> selByCount(int count, boolean isSort);
}
