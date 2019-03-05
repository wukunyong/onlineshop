package com.wuky.item.service;

import com.wuky.commons.pojo.TbItemChild;


public interface TbItemService
{
	/**
	 * 显示商品详情
	 *
	 * @param id
	 * @return
	 */
	TbItemChild show(long id);

}
