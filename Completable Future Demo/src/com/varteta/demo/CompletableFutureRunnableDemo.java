package com.varteta.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CompletableFutureRunnableDemo {
	
	static String filePath = "C:\\PC\\workspace\\Java Tutorials\\Completable Future Demo\\File\\holder.csv";
	
	public static void main(String[] args) {
		
		Executor executor = Executors.newFixedThreadPool(10);
		
		CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
		    @Override
		    public void run() {
		        try {
		        	File file = new File(filePath); 
		            List<String[]> dataList = new ArrayList<>();
		            dataList.add(new String[] {"Id","Name","Work"});
		        	for(Integer i=0;i<1000;i++)
		        	{
		        		dataList.add(new String[]{i.toString(),"Varteta Technologies", "Varteta Learning Platform Channel"});
		        	}
		        	System.out.println("list size ="+dataList.size());
		        	CSVUtil.writeCSV(file,dataList);
		        	
		        }  catch (IOException e) {
					e.printStackTrace();
				}
		        System.out.println("I'll run in a separate thread than the main thread.");
		    }
		},executor);
		
	
			try {
				future.get();
				System.out.println("Now task is done.");
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}

	}

}
