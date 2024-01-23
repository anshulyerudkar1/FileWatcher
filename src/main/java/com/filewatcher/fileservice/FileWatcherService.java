package com.filewatcher.fileservice;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.filewatcher.controller.Log4J2Controller;

import java.nio.file.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileWatcherService {
	
	private static final Log4J2Controller lgController=new Log4J2Controller();
	
	public static List<String> readFolderNames(String excelFilePath) {
		List<String> fileList=new ArrayList();
        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFilePath))) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    fileList.add(cell.getStringCellValue());
                }
            }
            lgController.info("Folder paths are added in the list ", null);
        } catch(IOException e) {
        	e.printStackTrace();
        	lgController.error("Error found in FileWatcherService class readFolderNames method : ", e);
        }
        return fileList;
    }
	
    
	/*
	 * private static final String DIRECTORY_TO_WATCH = "path/to/watched/directory";
	 * 
	 * @Scheduled(fixedRate = 5000) // Adjust the rate as needed public void
	 * watchFiles() { try { WatchService watchService =
	 * FileSystems.getDefault().newWatchService(); Path directory =
	 * Paths.get(DIRECTORY_TO_WATCH);
	 * 
	 * directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
	 * StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
	 * 
	 * WatchKey key = watchService.take(); for (WatchEvent<?> event :
	 * key.pollEvents()) { if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE)
	 * { // Handle file creation System.out.println("File created: " +
	 * event.context()); } else if (event.kind() ==
	 * StandardWatchEventKinds.ENTRY_DELETE) { // Handle file deletion
	 * System.out.println("File deleted: " + event.context()); } else if
	 * (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) { // Handle file
	 * modification System.out.println("File modified: " + event.context()); } }
	 * 
	 * key.reset(); } catch (Exception e) { // Handle exceptions
	 * e.printStackTrace(); } }
	 */
    
    

    @Scheduled(fixedDelay = 5000) // Adjust the delay as needed
    public void watchDirectories() {
    	
    	final List<String> directories=readFolderNames("D:/0000 Interview prep/Project/FileWatcher/src/main/resources/PathList.xlsx");
    	
        for (String directory : directories) {
            try {
                WatchService watchService = FileSystems.getDefault().newWatchService();
                Path path = Paths.get(directory);
                path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

                WatchKey key;
                while ((key = watchService.take()) != null) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                            // Handle file creation
                        	lgController.info("File created: " + event.context().toString(), null);
                        } else if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                            // Handle file deletion
                        	lgController.info("File deleted: " + event.context().toString(), null);
                        } else if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                            // Handle file modification
                        	lgController.info("File modified: " + event.context().toString(), null);
                        }
                    }
                    key.reset();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	
	
}
