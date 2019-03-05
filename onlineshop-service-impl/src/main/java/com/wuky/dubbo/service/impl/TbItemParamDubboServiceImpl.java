package com.wuky.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuky.commons.pojo.EasyUIDataGrid;
import com.wuky.dubbo.service.TbItemParamDubboService;
import com.wuky.mapper.TbItemParamMapper;
import com.wuky.pojo.TbItemParam;
import com.wuky.pojo.TbItemParamExample;


/**
 * 查询产品规格参数实现
 */
public class TbItemParamDubboServiceImpl implements TbItemParamDubboService
{
	@Resource
	private TbItemParamMapper tbItemParamMapper;

	public EasyUIDataGrid showPage(final int page, final int rows)
	{
		//先设置分页条件
		PageHelper.startPage(page, rows);
		//查询全部
		//XXXXExample() 设置了什么，相当于在sql中where从句中添加条件

		//如果表中有一个或一个以上的列是text类型，生成的方法是xxxwithBLOBs()
		//如果使用xxxwithBLOBs() 查询结果中包含有text的类，如果没有使用withBLOBs 方法不带有text列
		final List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		//根据自己编写的SQL语句结合分页插件产生最终结果，封装到PageInfo
		final PageInfo<TbItemParam> pi = new PageInfo<TbItemParam>(list);

		//设置方法返回结果
		final EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(pi.getList());
		dataGrid.setTotal(pi.getTotal());
		return dataGrid;
	}

	public int delByIds(final String ids) throws Exception
	{
		int index = 0;
		final String[] idsStr = ids.split(",");
		for (final String id : idsStr)
		{
			index += tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		if (index == idsStr.length)
		{
			return 1;
		}
		else
		{
			//插入数据异常，自己手写报错，事务回滚：在application.xml配置rollback-for="java.lang.Exception
			throw new Exception("删除失败，可能原因：数据已经不存在");
		}
	}

	public TbItemParam selByCatid(final long catId)
	{
		final TbItemParamExample example = new TbItemParamExample();
		example.createCriteria().andItemCatIdEqualTo(catId);
		final List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() > 0)
		{
			//要求每个类目只能有一个模板
			return list.get(0);
		}
		return null;
	}

	public int insParam(final TbItemParam param)
	{
		return tbItemParamMapper.insertSelective(param);
	}

}
