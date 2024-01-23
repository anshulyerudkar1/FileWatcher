package com.filewatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.filewatcher.controller.Log4J2Controller;
import com.filewatcher.fileservice.FileWatcherService;

@SpringBootApplication
public class FileWatcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileWatcherApplication.class, args);
		
		FileWatcherService fileWatcherService = new FileWatcherService();
		fileWatcherService.watchDirectories();
	}

}
