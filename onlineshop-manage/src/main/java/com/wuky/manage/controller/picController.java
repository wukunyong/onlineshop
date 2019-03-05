package com.wuky.manage.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wuky.manage.service.PicService;


@Controller
public class picController
{
	@Resource
	private PicService picServiceImpl;

	/**
	 * 文件上传
	 *
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping("pic/upload")
	@ResponseBody
	public Map<String, Object> upload(final MultipartFile uploadFile)
	{
		return picServiceImpl.upload(uploadFile);

	}

}
