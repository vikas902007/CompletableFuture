package com.varteta.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CompletableFutureSupplier {
	
	static String filePath = "C:\\PC\\workspace\\Java Tutorials\\Completable Future Demo\\File\\holder.csv";
	
	
	
	public static void main(String[] args) {
		
		CompletableFuture<List<String[]>> future = CompletableFuture.supplyAsync(new Supplier<List<String[]>>() {
		    @Override
		    public List<String[]> get() {
		    	
		    	List<String[]> dataList = new ArrayList<>();
		        try {
		        	File file = new File(filePath); 
		            
		            dataList = CSVUtil.readCSV(file,10);
		            
		        }  catch (IOException e) {
					e.printStackTrace();
				}
		        System.out.println("I'll run in a separate thread than the main thread.");
				return dataList;
		    }
		});
		
	
			try {
				List<String[]> dataList = new ArrayList<>();
				dataList = future.get();
				for(String[] arr : dataList)
				{
					for(String str : arr)
					{
						System.out.print(str+",");
					}
					System.out.println();
				}
				
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

	}

}
