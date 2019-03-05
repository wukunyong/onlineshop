package com.wuky.item.pojo;

public class ParamNode
{
	private String k;
	private String v;

	public String getK()
	{
		return k;
	}

	public void setK(final String k)
	{
		this.k = k;
	}

	public String getV()
	{
		return v;
	}

	public void setV(final String v)
	{
		this.v = v;
	}

	@Override
	public String toString()
	{
		return "ParamNode [k=" + k + ", v=" + v + "]";
	}

}
