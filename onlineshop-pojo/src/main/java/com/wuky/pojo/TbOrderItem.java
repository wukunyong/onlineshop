package com.wuky.pojo;

import java.io.Serializable;


public class TbOrderItem implements Serializable
{
	private String id;

	private String itemId;

	private String orderId;

	private Integer num;

	private String title;

	private Long price;

	private Long totalFee;

	private String picPath;

	public String getId()
	{
		return id;
	}

	public void setId(final String id)
	{
		this.id = id == null ? null : id.trim();
	}

	public String getItemId()
	{
		return itemId;
	}

	public void setItemId(final String itemId)
	{
		this.itemId = itemId == null ? null : itemId.trim();
	}

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(final String orderId)
	{
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public Integer getNum()
	{
		return num;
	}

	public void setNum(final Integer num)
	{
		this.num = num;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(final String title)
	{
		this.title = title == null ? null : title.trim();
	}

	public Long getPrice()
	{
		return price;
	}

	public void setPrice(final Long price)
	{
		this.price = price;
	}

	public Long getTotalFee()
	{
		return totalFee;
	}

	public void setTotalFee(final Long totalFee)
	{
		this.totalFee = totalFee;
	}

	public String getPicPath()
	{
		return picPath;
	}

	public void setPicPath(final String picPath)
	{
		this.picPath = picPath == null ? null : picPath.trim();
	}

	@Override
	public String toString()
	{
		return "TbOrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", num=" + num + ", title=" + title
				+ ", price=" + price + ", totalFee=" + totalFee + ", picPath=" + picPath + "]";
	}


}
