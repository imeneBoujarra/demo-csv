package com.example.demo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

@RestController
@CrossOrigin("*")
public class testCsv {

	/*@RestController
	public class CsvController {

		@GetMapping("/csv")
		public List<List<String>> readCSVFile(InputStream is) {
	        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
	            List<String> headers = null;
				try {
					headers = getcsvAtribute(is);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            List<List<String>> data = new ArrayList<>();
	            for (CSVRecord record : csvParser) {
	                List<String> row = new ArrayList<>();
	                for (String header : headers) {
	                    row.add(record.get(header));
	                }
	                data.add(row);
	            }
	            return data;
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to read CSV file: " + e.getMessage());
	        }
	    }
	    
	    private List<String> getcsvAtribute(InputStream is) {
	        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
	            String header = csvParser.getHeaderNames().get(0);
	            String separator = null;
				try {
					separator = getSeparator(header);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            if (";".equals(separator)) {
	                return Arrays.asList(header.split(separator));
	            } else {
	                return csvParser.getHeaderNames();
	            }
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to read CSV file: " + e.getMessage());
	        }
	    }

	    private String getSeparator(String header) {
	        for (String separator : Arrays.asList(",", ";", "|")) {
	            if (header.contains(separator)) {
	                return separator;
	            }
	        }
	        return ",";
	    }
	}

	
	
	public static List<Map<String, String>> parse(List<String> header, String content) {
		  List<Map<String, String>> tableData = new ArrayList<>();
		  CSVParser csvRecords = readCsvRecords(header, content);
		  Collection<String> headerToUse = header.isEmpty() ?
		      csvRecords.getHeaderMap().keySet() :
		      header;
		  for (CSVRecord record : csvRecords) {
		    tableData.add(createRow(headerToUse, record));
		  }
		  return tableData;
		}
	
	public void read(InputStream is) throws IOException {
		// Define the path to your CSV file
		String pathToFile = "path/to/your/csv/file.csv";

		// Read the contents of the CSV file into a string
		String content = new String(Files.readAllBytes(Paths.get(pathToFile)));

		// Define the headers for your CSV file as a list of strings
		List<String> header = List.of("Name", "Age", "City");

		// Parse the CSV content and headers into a list of maps
		List<Map<String, String>> tableData = parse(header, content);

		// Print the contents of the tableData list
		for (Map<String, String> row : tableData) {
		    System.out.println(row);
		}
		}
    
    @GetMapping("/transactions")
    public List<Transaction> getTransactions() throws IOException {
        List<Transaction> transactions = new ArrayList<>();

        // Open CSV file
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\imenb\\OneDrive\\Desktop\\csv.csv"));

        // Read header
        String header = reader.readLine();

        // Read each line of the CSV file
        String line;
        while ((line = reader.readLine()) != null)
        {
            String[] data = line.split(";");

            // Map CSV data to Transaction object
            Transaction transaction = new Transaction();
            transaction.setId(Integer.parseInt(data[0].replaceAll("^\"|\"$", "")));
            LocalDate date = LocalDate.of(Integer.parseInt(data[4]), Integer.parseInt(data[3]), Integer.parseInt(data[2]));
            transaction.setDateef(date);
            transaction.setCredit(Double.parseDouble(data[5]));
            transaction.setDebit(Double.parseDouble(data[6].replaceAll("^\"|\"$", "")));
            transactions.add(transaction);
        }

        reader.close();

        return transactions;
    }*/
}
