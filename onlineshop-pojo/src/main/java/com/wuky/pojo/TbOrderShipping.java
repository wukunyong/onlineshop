package com.wuky.pojo;

import java.io.Serializable;
import java.util.Date;


public class TbOrderShipping implements Serializable
{
	private String orderId;

	private String receiverName;

	private String receiverPhone;

	private String receiverMobile;

	private String receiverState;

	private String receiverCity;

	private String receiverDistrict;

	private String receiverAddress;

	private String receiverZip;

	private Date created;

	private Date updated;

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(final String orderId)
	{
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getReceiverName()
	{
		return receiverName;
	}

	public void setReceiverName(final String receiverName)
	{
		this.receiverName = receiverName == null ? null : receiverName.trim();
	}

	public String getReceiverPhone()
	{
		return receiverPhone;
	}

	public void setReceiverPhone(final String receiverPhone)
	{
		this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
	}

	public String getReceiverMobile()
	{
		return receiverMobile;
	}

	public void setReceiverMobile(final String receiverMobile)
	{
		this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
	}

	public String getReceiverState()
	{
		return receiverState;
	}

	public void setReceiverState(final String receiverState)
	{
		this.receiverState = receiverState == null ? null : receiverState.trim();
	}

	public String getReceiverCity()
	{
		return receiverCity;
	}

	public void setReceiverCity(final String receiverCity)
	{
		this.receiverCity = receiverCity == null ? null : receiverCity.trim();
	}

	public String getReceiverDistrict()
	{
		return receiverDistrict;
	}

	public void setReceiverDistrict(final String receiverDistrict)
	{
		this.receiverDistrict = receiverDistrict == null ? null : receiverDistrict.trim();
	}

	public String getReceiverAddress()
	{
		return receiverAddress;
	}

	public void setReceiverAddress(final String receiverAddress)
	{
		this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
	}

	public String getReceiverZip()
	{
		return receiverZip;
	}

	public void setReceiverZip(final String receiverZip)
	{
		this.receiverZip = receiverZip == null ? null : receiverZip.trim();
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(final Date created)
	{
		this.created = created;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setUpdated(final Date updated)
	{
		this.updated = updated;
	}

	@Override
	public String toString()
	{
		return "TbOrderShipping [orderId=" + orderId + ", receiverName=" + receiverName + ", receiverPhone=" + receiverPhone
				+ ", receiverMobile=" + receiverMobile + ", receiverState=" + receiverState + ", receiverCity=" + receiverCity
				+ ", receiverDistrict=" + receiverDistrict + ", receiverAddress=" + receiverAddress + ", receiverZip=" + receiverZip
				+ ", created=" + created + ", updated=" + updated + "]";
	}


}
