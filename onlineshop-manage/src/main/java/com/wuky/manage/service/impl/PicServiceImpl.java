package com.wuky.manage.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wuky.commons.utils.FtpUtil;
import com.wuky.manage.service.PicService;


@Service
public class PicServiceImpl implements PicService
{
	@Value("${ftpclient.host}")
	private String host;
	@Value("${ftpclient.port}")
	private int port;
	@Value("${ftpclient.username}")
	private String username;
	@Value("${ftpclient.password}")
	private String password;
	@Value("${ftpclient.basepath}")
	private String basePath;
	@Value("${ftpclient.filepath}")
	private String filePath;

	public Map<String, Object> upload(final MultipartFile file)
	{
		final Map<String, Object> map = new HashMap<String, Object>();
		final String oldName = file.getOriginalFilename();
		final String fileName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));
		boolean result = false;
		try
		{
			result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, fileName, file.getInputStream());
		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (result)
			{
				map.put("error", 0);
				map.put("url", "http://" + host + ":80" + filePath + fileName);
			}
			else
			{
				map.put("error", 1);
				map.put("msg", "图片上传失败");
			}
		}
		return map;
	}
}
