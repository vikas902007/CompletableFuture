package com.varteta.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.varteta.model.Storer;

public class CompletableFutureThenApply {

	static String filePath = "C:\\PC\\workspace\\Java Tutorials\\Completable Future Demo\\File\\holder.csv";
	
	public static void main(String[] args) {
		
		Executor executor = Executors.newFixedThreadPool(10);
		
		CompletableFuture<Set<Storer>> future = CompletableFuture.supplyAsync(()-> {
		    	
		    	List<String[]> dataList = new ArrayList<>();
		        try {
		        	File file = new File(filePath); 
		            
		            dataList = CSVUtil.readCSV(file,10);
		            
		        }  catch (IOException e) {
					e.printStackTrace();
				}
		        System.out.println("I'll run in a separate thread than the main thread.");
				return dataList;
		    
		},executor).thenApplyAsync(data ->{
			return removeDuplictae(data);
		},executor);
		
		try {
			Set<Storer> set = future.get();
			
			for(Storer storer : set)
			{
				System.out.println("id="+storer.getId()+" Name="+storer.getName()+" Work="+storer.getWork());
			}
			
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
			
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
