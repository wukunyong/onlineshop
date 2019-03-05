package com.wuky.manage.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;


public interface PicService
{
	/**
	 * 文件上传
	 * 
	 * @param file
	 * @return
	 */
	Map<String, Object> upload(MultipartFile file);

}
