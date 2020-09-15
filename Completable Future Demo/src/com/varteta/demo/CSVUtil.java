package com.varteta.demo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CSVUtil {
	
	
	public static void writeCSV(File file,List<String[]> data ) throws IOException
	{
		FileWriter outputfile = new FileWriter(file);
		
		CSVWriter writer = new CSVWriter(outputfile); 
		
		if(!data.isEmpty())
		{
			System.out.println("Data size ="+data.size());
			writer.writeAll(data);
			System.out.println("Data writing is done.");
			writer.close();
		}
		else
		{
			System.out.println("Data List is blank");
			
		}
	}
	
	public static List<String[]> readCSV(File file,int noOfLines) throws IOException
	{
		List<String[]> dataList = new ArrayList<>();
		FileReader inputFile = new FileReader(file);
		
		CSVReader reader = new CSVReader(inputFile); 
		String[] nextRecord;
		for(int i=0;i<=noOfLines;i++)
		{
			nextRecord = reader.readNext();
			dataList.add(nextRecord);
		}
		return dataList;
	}

}
