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

public class CompletableFutureCompose {

	static String filePath = "C:\\PC\\workspace\\Java Tutorials\\Completable Future Demo\\File\\holder.csv";
	
	static Executor executor = Executors.newFixedThreadPool(10);
	
	public static void main(String[] args) {
		
		CompletableFuture<Set<Storer>> finalFuture = future1Method().thenComposeAsync(result -> future2Method(result),executor);
		
		finalFuture.thenAccept(set->{
			for(Storer storer : set)
			{
				System.out.println("id="+storer.getId()+" Name="+storer.getName()+" Work="+storer.getWork());
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
	
	public static CompletableFuture<Set<Storer>> future2Method(List<String[]> dataList)
	{
		CompletableFuture<Set<Storer>> future = CompletableFuture.supplyAsync(()-> {
	        
	        Set<Storer> dataSet = removeDuplictae(dataList);
	        
	        System.out.println("I'll run in a separate thread than the main thread.");
			return dataSet;
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
