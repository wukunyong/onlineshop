package com.wuky.commons.utils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpClientUtil
{

	public static String doGet(final String url, final Map<String, String> param)
	{

		// 创建Httpclient对象
		final CloseableHttpClient httpclient = HttpClients.createDefault();

		String resultString = "";
		CloseableHttpResponse response = null;
		try
		{
			// 创建uri
			final URIBuilder builder = new URIBuilder(url);
			if (param != null)
			{
				for (final String key : param.keySet())
				{
					builder.addParameter(key, param.get(key));
				}
			}
			final URI uri = builder.build();

			// 创建http GET请求
			final HttpGet httpGet = new HttpGet(uri);

			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200)
			{
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (response != null)
				{
					response.close();
				}
				httpclient.close();
			}
			catch (final IOException e)
			{
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static String doGet(final String url)
	{
		return doGet(url, null);
	}

	public static String doPost(final String url, final Map<String, String> param)
	{
		// 创建Httpclient对象
		final CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try
		{
			// 创建Http Post请求
			final HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null)
			{
				final List<NameValuePair> paramList = new ArrayList<NameValuePair>();
				for (final String key : param.keySet())
				{
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				final UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				response.close();
			}
			catch (final IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultString;
	}

	public static String doPost(final String url)
	{
		return doPost(url, null);
	}

	public static String doPostJson(final String url, final String json)
	{
		// 创建Httpclient对象
		final CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try
		{
			// 创建Http Post请求
			final HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			final StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				response.close();
			}
			catch (final IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return resultString;
	}
}
