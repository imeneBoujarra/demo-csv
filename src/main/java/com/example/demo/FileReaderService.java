package com.example.demo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class FileReaderService {
	
	@Autowired 
	DataGetterService dataGetterService ;
	
	public List<Map<String, String>> getFileData(MultipartFile file) throws IOException 
	{
        String fileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(fileName);
        

        if (fileExtension.equalsIgnoreCase("csv")) {
            return dataGetterService.getCsvData(file.getInputStream());
        } else if (fileExtension.equalsIgnoreCase("xls") ) {
            return dataGetterService.getXlsData(file.getInputStream());
        } else if (fileExtension.equalsIgnoreCase("xlsx")) 
        {
        return dataGetterService.getXlsxData(file.getInputStream());}
        else {
            throw new IllegalArgumentException("Unsupported file format");
        }
    }
	
	public List<String> getFileAttributes (MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(fileName);
        
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getInputStream().readAllBytes())))) {

        if (fileExtension.equalsIgnoreCase("csv")) {
            return dataGetterService.getCsvAttributes(reader.readLine());
        } else if (fileExtension.equalsIgnoreCase("xls") ) {
        	Workbook workbook = new HSSFWorkbook(file.getInputStream()); // Use HSSFWorkbook for XLS files
  	        Sheet sheet = workbook.getSheetAt(0);
            return dataGetterService.getXlsAttributes(sheet.getRow(0));
        } else if (fileExtension.equalsIgnoreCase("xlsx")) 
        {Workbook workbook = new XSSFWorkbook(file.getInputStream()); // Use HSSFWorkbook for XLS files
	     Sheet sheet = workbook.getSheetAt(0);
        return dataGetterService.getXlsxAttributes(sheet.getRow(0));}
        else {
            throw new IllegalArgumentException("Unsupported file format");
        }
        }
        catch (IOException e) {
	        throw new RuntimeException("Failed to read CSV file: " + e.getMessage());
	    }
	
	}

	String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }


}
