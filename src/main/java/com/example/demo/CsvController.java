package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class CsvController {
	
	@Autowired
	FileReaderService fileReaderService ; 
	
	
	@PostMapping ("/getData")
	public List<Map<String, String>> getData( @RequestParam("file") MultipartFile file) throws IOException 
	{
		return fileReaderService.getFileData(file);
	}
	//aaaa
	
	@PostMapping ("/getAttributes")
	public List<String> getAttributes( @RequestParam("file") MultipartFile file) throws IOException 
	{
		return fileReaderService.getFileAttributes(file);
	}
	

}

