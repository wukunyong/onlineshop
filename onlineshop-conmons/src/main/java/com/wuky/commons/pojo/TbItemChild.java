package com.wuky.commons.pojo;

import com.wuky.pojo.TbItem;


public class TbItemChild extends TbItem
{
	private String[] images;

	/*
	 * 判断库存
	 */
	Boolean enough;

	public String[] getImages()
	{
		return images;
	}

	public void setImages(final String[] images)
	{
		this.images = images;
	}

	public Boolean getEnough()
	{
		return enough;
	}

	public void setEnough(final Boolean enough)
	{
		this.enough = enough;
	}

}
