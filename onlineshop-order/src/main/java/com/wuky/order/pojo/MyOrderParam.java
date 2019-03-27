package com.wuky.order.pojo;

import java.util.List;

import com.wuky.pojo.TbOrderItem;
import com.wuky.pojo.TbOrderShipping;


public class MyOrderParam
{
	private String orderid;
	private int paymentType;
	private String payment;
	private List<TbOrderItem> orderItems;
	private TbOrderShipping orderShipping;


	public String getOrderid()
	{
		return orderid;
	}

	public void setOrderid(final String orderid)
	{
		this.orderid = orderid;
	}

	public int getPaymentType()
	{
		return paymentType;
	}

	public void setPaymentType(final int paymentType)
	{
		this.paymentType = paymentType;
	}

	public String getPayment()
	{
		return payment;
	}

	public void setPayment(final String payment)
	{
		this.payment = payment;
	}

	public List<TbOrderItem> getOrderItems()
	{
		return orderItems;
	}

	public void setOrderItems(final List<TbOrderItem> orderItems)
	{
		this.orderItems = orderItems;
	}

	public TbOrderShipping getOrderShipping()
	{
		return orderShipping;
	}

	public void setOrderShipping(final TbOrderShipping orderShipping)
	{
		this.orderShipping = orderShipping;
	}

	@Override
	public String toString()
	{
		return "MyOrderParam [paymentType=" + paymentType + ", payment=" + payment + ", orderItems=" + orderItems
				+ ", orderShipping=" + orderShipping + "]";
	}


}
