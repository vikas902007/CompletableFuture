package com.varteta.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.varteta.model.Storer;

public class CompletableFutureCombine {

	static String filePath = "C:\\PC\\workspace\\Java Tutorials\\Completable Future Demo\\File\\holder.csv";
	static String filePath2 = "C:\\PC\\workspace\\Java Tutorials\\Completable Future Demo\\File\\holder2.csv";
	
	static Executor executor = Executors.newFixedThreadPool(10);
	
	public static void main(String[] args) {
		
		CompletableFuture<List<String[]>> finalFuture = future1Method().thenCombineAsync(future2Method(),
				(result1,result2)->{
					result1.addAll(result2);
					return result1;
				}
				,executor);
		
		finalFuture.thenAccept(dataList->{
			for(String[] arr : dataList)
			{
				for(String str : arr)
				{
					System.out.print(str+",");
				}
				System.out.println();
			}
		});
	}
	
	public static CompletableFuture<List<String[]>> future1Method()
	{
		CompletableFuture<List<String[]>> future = CompletableFuture.supplyAsync(()-> {
	    	
	    	List<String[]> dataList = new ArrayList<>();
	        try {
	        	File file = new File(filePath); 
	            
	            dataList = CSVUtil.readCSV(file,10);
	            
	        }  catch (IOException e) {
				e.printStackTrace();
			}
	        System.out.println("I'll run in a separate thread than the main thread.");
			return dataList;
		},executor);
		
		return future;
	}
	
	public static CompletableFuture<List<String[]>> future2Method()
	{
			CompletableFuture<List<String[]>> future = CompletableFuture.supplyAsync(()-> {
	    	
	    	List<String[]> dataList = new ArrayList<>();
	        try {
	        	File file = new File(filePath2); 
	            
	            dataList = CSVUtil.readCSV(file,10);
	            
	        }  catch (IOException e) {
				e.printStackTrace();
			}
	        System.out.println("I'll run in a separate thread than the main thread.");
			return dataList;
		},executor);
		
		return future;
	}
	
	public static Set<Storer> removeDuplictae(List<String[]> dataList)
	{
			Set<Storer> dataSet = new HashSet<>();
			//dataList = future.get();
			for(String[] arr : dataList)
			{
				Storer storer = new Storer(arr[0],arr[1],arr[2]);
				dataSet.add(storer);
			}
			
			return dataSet;
	}
}
