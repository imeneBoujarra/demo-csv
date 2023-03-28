package com.example.demo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;


@Service
public class DataGetterService {
	
//getting data from a csv file as a map of string 

	public List<Map<String, String>> getCsvData(InputStream is) throws IOException {
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(is.readAllBytes())))) {
		    List<String> headers = getCsvAttributes(reader.readLine());

	        String separator = getSeparator(reader.readLine());
	        List<Map<String, String>> rows = new ArrayList<>();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            Map<String, String> row = new LinkedHashMap<>();
	            String[] values;
	            if (separator.equals("|")) {
	                values = line.split("\\|");
	            } else {
	                values = line.split(separator);
	            }
	            for (int i = 0; i < headers.size(); i++) {
	                row.put(headers.get(i), values[i].replaceAll("\"", ""));
	            }
	            rows.add(row);
	        }
	        return rows;
	    } catch (IOException e) {
	        throw new RuntimeException("Failed to read CSV file: " + e.getMessage());
	    }
	}


	
	  public List<String> getCsvAttributes(String headerLine) {
	        String separator = getSeparator(headerLine);
	        String[] headerValues = headerLine.split(separator);
	        return Arrays.asList(headerValues);
	    }
	
	private String getSeparator(String header) {
	    for (String separator : Arrays.asList(",", ";", "|")) {
	        if (header.contains(separator)) {
	            return separator;
	        }
	    }
	    return ",";
	}
	

		
	public List<Map<String, String>> getXlsData(InputStream is) throws IOException {
	   
	        Workbook workbook = new HSSFWorkbook(is); // Use HSSFWorkbook for XLS files
	        Sheet sheet = workbook.getSheetAt(0);
	        List<String> headers = getXlsAttributes(sheet.getRow(0));
	        List<Map<String, String>> rows = new ArrayList<>();
	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i);
	            Map<String, String> rowData = new LinkedHashMap<>();
	            for (int j = 0; j < headers.size(); j++) {
	                String cellValue = getCellValueAsString(row.getCell(j));
	                rowData.put(headers.get(j), cellValue);
	            }
	            rows.add(rowData);
	        }
	        return rows;
	    } 
	

	public List<String> getXlsAttributes(Row row) throws IOException {
		
	
	    List<String> headers = new ArrayList<>();
	    for (Cell cell : row ) {
	        headers.add(getCellValueAsString(cell));
	    }
	    return headers;
	    } 
	


	private String getCellValueAsString(Cell cell) {
	    if (cell == null || cell.getCellType() == CellType.BLANK) {
	        return "";
	    } else if (cell.getCellType() == CellType.STRING) {
	        return cell.getStringCellValue();
	    } else if (cell.getCellType() == CellType.NUMERIC) {
	        if (DateUtil.isCellDateFormatted(cell)) {
	            Date date = cell.getDateCellValue();
	            DateFormat df = new SimpleDateFormat("M/d/yyyy");
	            return df.format(date);
	        } else 
	        {
	            return String.valueOf(cell.getNumericCellValue());
	        }
	    } else if (cell.getCellType() == CellType.BOOLEAN) {
	        return String.valueOf(cell.getBooleanCellValue());
	    } else {
	        return "";
	    }
	}

	
	public List<Map<String, String>> getXlsxData(InputStream is) throws IOException {
		
	        Workbook workbook = new XSSFWorkbook(is); // Use XSSFWorkbook for XLSX files
	        Sheet sheet = workbook.getSheetAt(0);
	        List<String> headers = getXlsxAttributes(sheet.getRow(0));
	        List<Map<String, String>> rows = new ArrayList<>();
	        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	            Row row = sheet.getRow(i);
	            Map<String, String> rowData = new LinkedHashMap<>();
	            for (int j = 0; j < headers.size(); j++) {
	                String cellValue = getCellValueAsString(row.getCell(j));
	                rowData.put(headers.get(j), cellValue);
	            }
	            rows.add(rowData);
	        }
	        return rows;
	}

	public List<String> getXlsxAttributes(Row row) throws IOException {
		
	       // Use XSSFWorkbook for XLSX files
	   
	    List<String> headers = new ArrayList<>();
	    for (Cell cell : row) {
	        headers.add(getCellValueAsString(cell));
	    } 
	    return headers;
	    } 
	

	

}
