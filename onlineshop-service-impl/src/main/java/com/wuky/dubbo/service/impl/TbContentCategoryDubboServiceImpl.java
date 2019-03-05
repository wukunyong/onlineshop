package com.wuky.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.wuky.dubbo.service.TbContentCategoryDubboService;
import com.wuky.mapper.TbContentCategoryMapper;
import com.wuky.pojo.TbContentCategory;
import com.wuky.pojo.TbContentCategoryExample;


public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService
{
	@Resource
	TbContentCategoryMapper tbContentCategoryMapper;

	public List<TbContentCategory> selByPid(final long id)
	{
		final TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(id).andStatusEqualTo(1);
		return tbContentCategoryMapper.selectByExample(example);
	}

	public int insTbContentCategory(final TbContentCategory cate)
	{
		return tbContentCategoryMapper.insertSelective(cate);
	}

	public int updIsParentById(final TbContentCategory cate)
	{
		return tbContentCategoryMapper.updateByPrimaryKeySelective(cate);
	}

	public TbContentCategory selById(final long id)
	{
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}

}
