package com.filewatcher.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class Log4J2Controller {

	private static final Logger logger = LogManager.getLogger(Log4J2Controller.class);
	
	/*
	 * @GetMapping(value="/greeting") public String greeting() {
	 * logger.debug("Debugging log in our greeting method");
	 * logger.info("Info log in our greeting method");
	 * logger.warn("Warning log in our greeting method");
	 * logger.error("Error in our greeting method");
	 * logger.fatal("Damn! Fatal error. Please fix me.");
	 * 
	 * return "Hello!!!"; }
	 */
	
	public void error(String dec, Throwable throwable)
	{
		logger.error(dec, throwable);
	}
	
	public void error(String dec, String exceptionString)
	{
		logger.error(dec, exceptionString);
	}
	
	public void info(String dec, String exceptionString)
	{
		logger.info(dec, exceptionString);
	}
	
	public void debug(String dec, String exceptionString)
	{
		logger.info(dec, exceptionString);
	}
	
	public void warn(String dec, String exceptionString)
	{
		logger.info(dec, exceptionString);
	}
	
	public void fatal(String dec, String exceptionString)
	{
		logger.info(dec, exceptionString);
	}
	
}
