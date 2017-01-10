package com.ezitapps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddFloridaTaxesToZip {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String floridaZips = "florida-zip-codes.txt";
		String floridaCounty = "florida-county-taxes.txt";
		String outputFileName = "florida-zip-taxes.csv";
		
		// Writer
		File file = new File(outputFileName);
		
		if (!file.exists()){
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		System.out.println("JC I started!\n");
		
		
		// Two Readers
		Scanner z = new Scanner(new File(floridaZips));
		ArrayList<String> listZips = new ArrayList<String>();
		while (z.hasNextLine()){
		    listZips.add(z.nextLine());
		}
		
		Scanner c = new Scanner(new File(floridaCounty));
		ArrayList<String> listCounty = new ArrayList<String>();
		while (c.hasNextLine()){
		    listCounty.add(c.nextLine());
		}
		c.close();
		z.close();
		
		
		
		
		for (int x = 0 ; x < listZips.size() ; x++){
			//System.out.println(listZips.get(x));
			String [] zipArray = listZips.get(x).split("\t", -1);
			
			//System.out.println(Arrays.toString(zipArray));
			
			for (int y = 0 ; y < listCounty.size() ; y++){
				String [] countyArray = listCounty.get(y).split("\t", -1);
				
				// System.out.println(Arrays.toString(countyArray));
				
				
				if (zipArray[2].equals(countyArray[0])){
					//System.out.println("It was true");
					listZips.set(x , listZips.get(x) + "\t" + countyArray[1] + "\t" + "FL");
				}
				
			}
			
		}
		
		// Here we are going to change the header names so they match the output. 
		listZips.set(0 , "Zip/Postcode" + "\t" + "City" + "\t" + "Tax Name" + "\t" +"Rate %" + "\t" + "State Code");
		
		
		for (int x = 0 ; x < listZips.size() ; x++){
			System.out.println(listZips.get(x));
			bw.write(listZips.get(x).replaceAll("\t", ","));
			bw.newLine();
		}
		
		
		System.out.println(listZips.get(0));
		
		
		// Ending
		
		bw.close();
		
		System.out.println("JC I ended!\n" + listZips.size() + " " + listCounty.size());

	}

}
