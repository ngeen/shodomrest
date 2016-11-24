package com.shodom.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shodom.model.DownloadFile;
import com.shodom.service.Downloader;

@Controller
public class DownloadController {

	@Autowired
	Downloader downloader;

	@ResponseBody
	@RequestMapping(value = "/download", method = RequestMethod.POST)
	public ResponseEntity<String> runCmd(@ModelAttribute DownloadFile downloadFile) {
		String result = "";
		try{
			result = downloader.download(downloadFile);
		} catch (Exception e) {
			return new ResponseEntity<String>(ExceptionUtils.getStackTrace(e), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
