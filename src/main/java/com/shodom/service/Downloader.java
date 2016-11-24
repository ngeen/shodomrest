package com.shodom.service;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import com.shodom.model.DownloadFile;

@Service
public class Downloader {

	public String download(DownloadFile downloadFile) throws Exception {
		int exitValue;
		ExecuteWatchdog watchdog = null;

		// build up the command line to using a 'java.io.File'
		CommandLine commandLine = new CommandLine("/bin/bash");
		commandLine.addArgument("/var/www/html/media/gifenc.sh");
		commandLine.addArguments(downloadFile.getUrl());
		commandLine.addArgument(downloadFile.getFileName());

		// create the executor and consider the exitValue '0' as success
		Executor executor = new DefaultExecutor();
		executor.setExitValue(0);

		// create a watchdog if requested
		watchdog = new ExecuteWatchdog(10000);
		executor.setWatchdog(watchdog);

		// handle output
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
		executor.setStreamHandler(streamHandler);

		exitValue = executor.execute(commandLine);

		return outputStream.toString("UTF-8");
	}
}
