package com.wuky.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuky.pojo.TbItemCat;
import com.wuky.pojo.TbItemCatExample;


public interface TbItemCatMapper
{
	int countByExample(TbItemCatExample example);

	int deleteByExample(TbItemCatExample example);

	int deleteByPrimaryKey(Long id);

	int insert(TbItemCat record);

	int insertSelective(TbItemCat record);

	List<TbItemCat> selectByExample(TbItemCatExample example);

	TbItemCat selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") TbItemCat record, @Param("example") TbItemCatExample example);

	int updateByExample(@Param("record") TbItemCat record, @Param("example") TbItemCatExample example);

	int updateByPrimaryKeySelective(TbItemCat record);

	int updateByPrimaryKey(TbItemCat record);
}