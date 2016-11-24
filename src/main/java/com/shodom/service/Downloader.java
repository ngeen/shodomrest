package com.shodom.service;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import com.shodom.model.DownloadFile;

@Service
public class Downloader {

	public String download(DownloadFile downloadFile) throws Exception {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		CommandLine commandLine = new CommandLine("/bin/sh");
		commandLine.addArgument("-c");
		commandLine.addArgument("/var/www/html/media/gifenc.sh");
		commandLine.addArguments(downloadFile.getUrl());
		commandLine.addArgument(downloadFile.getFileName());
		
		Executor exec = new DefaultExecutor();
		exec.setExitValue(0);
	    PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
	    exec.setStreamHandler(streamHandler);
	    exec.execute(commandLine);

		return outputStream.toString("UTF-8");
	}
}
