package com.shodom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shodom.model.DownloadFile;
import com.shodom.model.Entry;
import com.shodom.service.Downloader;

@Controller
public class DownloadController {

	@Autowired
	Downloader downloader;

	@ResponseBody
	@RequestMapping(value = "/download", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public String runCmd(@ModelAttribute DownloadFile downloadFile) {
		return downloader.download(downloadFile);
	}
}
