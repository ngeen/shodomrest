package com.shodom.service;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.shodom.model.DownloadFile;

@Service
public class Downloader {

	public String download(DownloadFile downloadFile) throws Exception {
		String result = "";
		String[] cmd = {"/bin/bash", "-c", "/var/www/html/media/gifenc.sh"};
		ProcessBuilder processBuilder = new ProcessBuilder(cmd);
		processBuilder.environment().put("l", downloadFile.getUrl());
		processBuilder.environment().put("f", downloadFile.getFileName());
		processBuilder.redirectErrorStream(true);
		final Process process = processBuilder.start();

		result = IOUtils.toString(process.getInputStream(), Charsets.toCharset("UTF-8"));

		process.waitFor();

		return result;
	}
}
