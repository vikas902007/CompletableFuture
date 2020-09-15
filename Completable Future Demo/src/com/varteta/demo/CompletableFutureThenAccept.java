package com.varteta.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.varteta.model.Storer;

public class CompletableFutureThenAccept {
	
static String filePath = "C:\\PC\\workspace\\Java Tutorials\\Completable Future Demo\\File\\holder.csv";
	
	public static void main(String[] args) {
		try
		{
			CompletableFuture<Void> future = CompletableFuture.supplyAsync(()-> {
		    	List<String[]> dataList = new ArrayList<>();
		        try {
		        	File file = new File(filePath); 
		            
		            dataList = CSVUtil.readCSV(file,10);
		            
		        }  catch (IOException e) {
					e.printStackTrace();
				}
		        System.out.println("I'll run in a separate thread than the main thread.");
				return dataList;
		    
		}).thenApply(data ->{
			return removeDuplictae(data);
		}).thenAccept(set->{
			for(Storer storer : set)
			{
				System.out.println("id="+storer.getId()+" Name="+storer.getName()+" Work="+storer.getWork());
			}
		});
		
			future.get();
		
		}
		catch(Exception e)
		{
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
