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
public class AutoFormat {
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
			htmlDoc.append("<!DOCTYPE html>\n<html dir=\"ltr\" lang=\"en\">\n<head>\n");
			htmlDoc.append("\t<meta charset=\"utf-8\">");
			htmlDoc.append("\n\t<title>Wolf Creek Dam : 2019-" + fileNum + "</title>");
			//Link to external css located in subdirectory "css"
			htmlDoc.append("\n\t<link rel=\"stylesheet\" href=\"css/wolfcreek.css\">");
			//Will add to the next two lines in the future
			htmlDoc.append("\n\t<style></style>\n\t<script></script>");
			//Comments
			htmlDoc.append("\n\n\t<!-- Class: COSC 231.10 -->");
			htmlDoc.append("\n\t<!-- Name: Apollo Schaefer -->");
			htmlDoc.append("\n\t<!-- EID: 02197111 -->");
			htmlDoc.append("\n\t<!-- Lab: 04 (Part 2) -->");
			htmlDoc.append("\n\t<!-- Date: 2023-10-07 SAT T 23:59:00 Z-04:00 -->");
			htmlDoc.append("\n\n\t<!-- Requires: 12 html files labeled 2019_NN.html where NN is a number 01-12, and one file index.html. -->");
			htmlDoc.append("\n\t<!-- also requires a subdirectory \"css\" containing the wolfcreek.css file -->");
			htmlDoc.append("\n\n\t<!-- Description: This file displays a table for a given month of data recordings from Wolf Creek Dam, and allows navigation to other months of data and a site home page -->\n");
			htmlDoc.append("\n</head>\n<body>\n\t<div id=\"top\"></div>");
			//Navigation menu
			htmlDoc.append("\n\t<!-- Navigation menu to access other tables/index page -->");
			htmlDoc.append("\r\n"
					+ "	<div id=\"navmenu_div\" class=\"navbarouter\">\r\n"
					+ "		<button id=\"nav_button\">Hide Menu</button> \r\n"
					+ "		<div id=\"navbar\" class=\"navbar\">\r\n"
					+ "			<div class=\"nav_list\"><p>Maps &amp; Charts:</p></div>\r\n"
					+ "			<div class=\"nav_list\">	\r\n"
					+ "				<a href=\"index.html\">Main</a>\r\n"
					+ "			</div>	\r\n"
					+ "			<div class=\"nav_list\"><p>Hourly Readings by Month:</p></div>\r\n"
					+ "			<div class=\"nav_list\">\r\n"
					+ "				<a href=\"2019_01.html\">2019-01</a>\r\n"
					+ "				<a href=\"2019_02.html\">2019-02</a>\r\n"
					+ "				<a href=\"2019_03.html\">2019-03</a>\r\n"
					+ "				<a href=\"2019_04.html\">2019-04</a>\r\n"
					+ "				<a href=\"2019_05.html\">2019-05</a>\r\n"
					+ "				<a href=\"2019_06.html\">2019-06</a>\r\n"
					+ "			</div>	\r\n"
					+ "			<div class=\"nav_list\">	\r\n"
					+ "				<a href=\"2019_07.html\">2019-07</a>\r\n"
					+ "				<a href=\"2019_08.html\">2019-08</a>\r\n"
					+ "				<a href=\"2019_09.html\">2019-09</a>\r\n"
					+ "				<a href=\"2019_10.html\">2019-10</a>\r\n"
					+ "				<a href=\"2019_11.html\">2019-11</a>\r\n"
					+ "				<a href=\"2019_12.html\">2019-12</a>\r\n"
					+ "			</div> <!-- END	<div id=\"nav_list\"> -->\r\n"
					+ "		</div> <!-- END of <div id=\"navbar\" class=\"navbar\"> -->\r\n"
					+ "	</div> <!-- END <div id=\"navmenu_div\"> -->");
			//H1 header with two lines
			htmlDoc.append("<!-- Two lines of header seperated by br tag -->");
			htmlDoc.append("\n\t<h1 class=\"major_format\">\n\t\tHourly observations for 2019-" + fileNum + "\n\t\t<br>\n\t\t(Wolf Creek Dam, Russell, KY, USA)\n\t</h1>");
			//Horizontal rules with elevation threshold input box within
			htmlDoc.append("\n\t<!-- Box for inputting elevation threshold to be highlighted -->\n\t<hr>");
			htmlDoc.append("\n\t<div class=\"major_format\">\r\n"
					+ "		<p>Update Elevation Highlight Threshold:</p>\r\n"
					+ "		<input id=\"newdanger\" type=\"number\" >\r\n"
					+ "		<p>Upstream Elevation readings across all 12 pages of hourly readings are highlighted if at or above this threshold value.</p>\r\n"
					+ "		<p>Value should update threshold in some charts on the charts page too.</p>\r\n"
					+ "		<p>Permitted values: [650, ]</p>\r\n"
					+ "		<button id=\"btn_reset_danger\">Reset</button> (Reset Threshold to 720&apos;)\r\n"
					+ "	</div> \r\n"
					+ "	");
			htmlDoc.append("<hr>");
			//Button for hiding/showing table on page
			htmlDoc.append("\n\t<!-- Button to hide or show the table for the current page -->");
			htmlDoc.append("\n\t<button class=\"major_format\" id=\"showhide_button_" + fileNum + "\">Hide Table</button>");
			//Div containing h2 tag and table begin
			htmlDoc.append("\n\t<!-- Begin div containing the table -->");
			htmlDoc.append("\n\t<div id=\"month_" + fileNum + "\" class=\"month_rows\">");
			htmlDoc.append("\n\t\t<h2 class=\"major_format\">Gage Readings for 2019-" + fileNum + ":</h2>");
			//Table formatting/headings
			htmlDoc.append("\n\t\t<table class=\"hrly_readings\">\n\t\t\t<thread>\n\t\t\t\t<tr>");
			htmlDoc.append("\n\t\t\t\t\t<th>\n\t\t\t\t\t\tGAGE TIME\n\t\t\t\t\t\t<br>\n\t\t\t\t\t\tUTC (ISO-8601):\n\t\t\t\t\t</th>");
			htmlDoc.append("\n\t\t\t\t\t<th>\n\t\t\t\t\t\tELEVATION\n\t\t\t\t\t\t<br>\n\t\t\t\t\t\tUPSTREAM:\n\t\t\t\t\t</th>");
			htmlDoc.append("\n\t\t\t\t\t<th>\n\t\t\t\t\t\tELEVATION\n\t\t\t\t\t\t<br>\n\t\t\t\t\t\tDOWNSTREAM:\n\t\t\t\t\t</th>");
			htmlDoc.append("\n\t\t\t\t\t<th>\n\t\t\t\t\t\tDISCHARGE\n\t\t\t\t\t\t<br>\n\t\t\t\t\t\tHOURLY AVG.:\n\t\t\t\t\t</th>");
			htmlDoc.append("\n\t\t\t\t</tr>\n\t\t\t</thread>\n\t\t\t<!-- Begin Table -->\n\t\t\t<tbody>");
			//While there is more input, scan row of data and append to html file
			while(scan.hasNextLine()) {
				htmlDoc.append("\n\t\t\t\t<tr>");
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
						htmlDoc.append("\n\t\t\t\t\t<td>" + currentToken + "</td>");
					}
					//In case of less than i tokens
					else
						htmlDoc.append("\n\t\t\t\t\t<td></td>");
				}
				htmlDoc.append("\n\t\t\t\t</tr>");
			}
			htmlDoc.append("\n\t\t\t</tbody>\n\t\t</table>\n\t</div>\n\t<!-- END <div id=\"month_" + fileNum + "\" class=\"month_rows\"> -->");
			//Go to top button contained in a div with class "major_format"
			htmlDoc.append("\n\t<!-- Button to take use back to the top of the page -->");
			htmlDoc.append("\n\t<div class=\"major_format\">\n\t\t<button>\n\t\t\t<a href=\"#top\">Go To Top</a>");
			htmlDoc.append("\n\t\t</button>\n\t</div>");
			//Div for copyright tag
			htmlDoc.append("\n\t<hr>");
			htmlDoc.append("\n\t<!-- Copyright -->");
			htmlDoc.append("\n\t<div id=\"copyright\">");
			htmlDoc.append("\n\t\t<p class=\"major_format\">&copy; 2023 - Apollo Schaefer</p>\n\t</div>");
			//Close body and html tags to finish document
			htmlDoc.append("\n</body>\n</html>");
			//HTML file should be fully ready now.
			//Replace "input" with "output" and ".csv" with ".html" to obtain the correct output path for html files
			String htmlFilePath = "output/2019_" + fileNum + ".html";
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
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
