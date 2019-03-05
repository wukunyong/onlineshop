package com.wuky.dubbo.service;

import com.wuky.pojo.TbItemDesc;


public interface TbItemDescDubboService
{
	/**
	 * 商品新增
	 *
	 * @param tbItemDesc
	 * @return
	 */
	int insDesc(TbItemDesc tbItemDesc);

	/**
	 * 根据主键查询商品描述对象
	 * 
	 * @param itemid
	 * @return
	 */
	TbItemDesc selByItemid(long itemid);
}
