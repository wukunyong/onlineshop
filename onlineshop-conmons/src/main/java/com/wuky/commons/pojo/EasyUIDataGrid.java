package com.wuky.commons.pojo;

import java.io.Serializable;
import java.util.List;


public class EasyUIDataGrid implements Serializable
{
	//当前页显示数据
	private List<?> rows;
	//总条数
	private long total;

	public List<?> getRows()
	{
		return rows;
	}

	public void setRows(final List<?> rows)
	{
		this.rows = rows;
	}

	public long getTotal()
	{
		return total;
	}

	public void setTotal(final long total)
	{
		this.total = total;
	}

}
