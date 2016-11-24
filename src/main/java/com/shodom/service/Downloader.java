package com.shodom.service;

import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import com.shodom.model.DownloadFile;

@Service
public class Downloader {

	public String download(DownloadFile downloadFile){
		String result = "";
	    try {
			ProcessBuilder processBuilder = new ProcessBuilder("/var/www/media/gifenc.sh", downloadFile.getUrl(), downloadFile.getFileName());
			processBuilder.redirectErrorStream(true);
			final Process process = processBuilder.start();

			result = IOUtils.toString(process.getInputStream(), Charsets.toCharset("UTF-8"));
					
			process.waitFor();
			System.out.println("Waiting ...");

			System.out.println("Returned Value :" + process.exitValue());
		} catch (Exception e) {
			result = ExceptionUtils.getStackTrace(e);
		}
	    
	    return result;
	}
}
