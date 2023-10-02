/*
 * Class: COSC 231.10
 * Name: Apollo North Schaefer
 *EID: E02197111
 *Lab: 04 (Part 01)
 *Date: 2023-09-30 SAT T 23:59:00 Z-04:00
 *
 *Requires: 12 .csv files located in an "input" directory, where the "input" directory is located inside the same directory as the program. Also requires a similary placed
 *"output" directory, where the 12 html output files will be placed. The .csv files must be named "wolf_creek_hourly_2019_*file num 01-12*.csv"
 * 
 * Description: This java program takes input from 12 .csv excel files and automatically outputs an html file for each one that has the table and table information formatted.
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
public class AutoFormatData {
	public static void main(String[] args) {
		//create variable to hold file path and number
		String filePath = "";
		String fileNum = "'";
		//Loop 1-12 to create the filepath and filenum strings for each file and call the convert method with that path and number (as strings)
		for(int i = 1; i <= 12; i++) {
			if(i < 10)
				fileNum = "0" + i;
			else
				fileNum = "" + i;
			filePath = "input/wolf_creek_hourly_2019_" + fileNum + ".csv";
			convert(filePath, fileNum);
		}
	}

	public static void convert(String csvFilePath, String fileNum) {
		//Try with resources for reading from .csv files
		try (Scanner scan = new Scanner(new File(csvFilePath));) {
			//Create stringbuilder instance and append the beginning of html document
			StringBuilder htmlDoc = new StringBuilder();
			htmlDoc.append("<!DOCTYPE html>\n<html dir=\"ltr\" lang=\"en\">\n\t<head>\n");
			htmlDoc.append("\t\t<meta charset=\"utf-8\">");
			htmlDoc.append("\n\t\t<title>Wolf Creek Dam : 2019-" + fileNum + "</title>");
			htmlDoc.append("\n\t</head>\n\t<body>");
			htmlDoc.append("\n\t\t<div id=\"month_" + fileNum + "\" class=\"month_rows\">");
			htmlDoc.append("\n\t\t\t<h2 class=\"major format\">Gage Readings for 2019-" + fileNum + ":</h2>");
			htmlDoc.append("\n\t\t\t<table class=\"hrly_readings\">\n\t\t\t\t<thread>\n\t\t\t\t\t<tr>");
			htmlDoc.append("\n\t\t\t\t\t\t<th>\n\t\t\t\t\t\t\tGAGE TIME\n\t\t\t\t\t\t\t<br>\n\t\t\t\t\t\t\tUTC (ISO-8601):\n\t\t\t\t\t\t</th>");
			htmlDoc.append("\n\t\t\t\t\t\t<th>\n\t\t\t\t\t\t\tELEVATION\n\t\t\t\t\t\t\t<br>\n\t\t\t\t\t\t\tUPSTREAM:\n\t\t\t\t\t\t</th>");
			htmlDoc.append("\n\t\t\t\t\t\t<th>\n\t\t\t\t\t\t\tELEVATION\n\t\t\t\t\t\t\t<br>\n\t\t\t\t\t\t\tDOWNSTREAM:\n\t\t\t\t\t\t</th>");
			htmlDoc.append("\n\t\t\t\t\t\t<th>\n\t\t\t\t\t\t\tDISCHARGE\n\t\t\t\t\t\t\t<br>\n\t\t\t\t\t\t\tHOURLY AVG.:\n\t\t\t\t\t\t</th>");
			htmlDoc.append("\n\t\t\t\t\t</tr>\n\t\t\t\t</thread>\n\t\t\t\t<tbody>");
			//While there is more input, scan row of data and append to html file
			while(scan.hasNextLine()) {
				htmlDoc.append("\n\t\t\t\t\t<tr>");
				//Split the data with " and , delimiters
				String[] tokens = scan.nextLine().split("\",\"");
				//Replace the extra quotation mark in first and last values, ensure that those tokens exists to avoid ArrayOutOfBounds
				if(tokens.length > 0) 
					tokens[0] = tokens[0].replace("\"", "");
				if(tokens.length > 3) 
					tokens[3] = tokens[3].replace("\"", "");
				for(int i = 0; i < 4; i++) {
					//if there are more tokens in the string array, add data value to table
					if(i < tokens.length) {
						String currentToken = tokens[i];
						htmlDoc.append("\n\t\t\t\t\t\t<td>" + currentToken + "</td>");
					}
					//In case of less than i tokens
					else
						htmlDoc.append("\n\t\t\t\t\t\t<td></td>");
				}
				htmlDoc.append("\n\t\t\t\t\t</tr>");
			}
			htmlDoc.append("\n\t\t\t\t</tbody>\n\t\t\t</table>\n\t\t</div>\n\t\t<!-- END <div id=\"month_" + fileNum + "\" class=\"month_rows\"> -->");
			htmlDoc.append("\n\t</body>\n</html>");
			//HTML file should be fully ready now.
			//Replace "input" with "output" and ".csv" with ".html" to obtain the correct output path for html files
			String htmlFilePath = csvFilePath.replace("input", "output").replace(".csv", ".html");
			//Try with resources for writing the html files
			try(PrintWriter writer = new PrintWriter(new File(htmlFilePath))) {
				writer.write(htmlDoc.toString());
			}
			//catch IOExceptions and output their stack trace
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		//catch exception for file not found, output stack trace
		catch(FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
