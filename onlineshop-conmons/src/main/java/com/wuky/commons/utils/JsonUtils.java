package com.wuky.commons.utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 商城自定义响应结构
 */
public class JsonUtils
{

	// 定义jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 将对象转换成json字符串。
	 * <p>
	 * Title: pojoToJson
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 *
	 * @param data
	 * @return
	 */
	public static String objectToJson(final Object data)
	{
		try
		{
			final String string = MAPPER.writeValueAsString(data);
			return string;
		}
		catch (final JsonProcessingException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json结果集转化为对象
	 *
	 * @param jsonData json数据
	 * @param clazz 对象中的object类型
	 * @return
	 */
	public static <T> T jsonToPojo(final String jsonData, final Class<T> beanType)
	{
		try
		{
			final T t = MAPPER.readValue(jsonData, beanType);
			return t;
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json数据转换成pojo对象list
	 * <p>
	 * Title: jsonToList
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 *
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> jsonToList(final String jsonData, final Class<T> beanType)
	{
		final JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try
		{
			final List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

}
