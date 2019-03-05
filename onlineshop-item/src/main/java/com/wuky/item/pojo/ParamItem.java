package com.wuky.item.pojo;

import java.util.List;


public class ParamItem
{
	private String group;
	private List<ParamNode> params;

	public String getGroup()
	{
		return group;
	}

	public void setGroup(final String group)
	{
		this.group = group;
	}

	public List<ParamNode> getParams()
	{
		return params;
	}

	public void setParams(final List<ParamNode> params)
	{
		this.params = params;
	}

	@Override
	public String toString()
	{
		return "ParamItem [group=" + group + ", params=" + params + "]";
	}

}
