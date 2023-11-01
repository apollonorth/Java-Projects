/*
Programmer: Apollo Schaefer
Description: This program takes a .txt file containing a 10x10 matrix and outputs another text file containing
			 the properties of relations (symmetric, antisymmetric, reflexive, antireflexive) and whether the input matrix fits them.
			 
Requirements:This program requires a .txt file stored in the same directory as the java program containing
			 only a binary 10x10 matrix (only 0s and 1s).
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class PropertiesOfRelations {
	final static int MATRIX_SIZE = 10;
	public static void main(String[] args) {
		//Initialize and declare variables to be used
		char[][] matrix = new char[MATRIX_SIZE][MATRIX_SIZE];
		Scanner kb = new Scanner(System.in);
		String line;
		char[] split = null;
		
		//Grab file name from user and append / (for input file to be in same directory of the program)
		System.out.print("Give the file name of a text file containing a 10x10 binary matrix (file should be in the same directory as this program and end in .txt): ");
		String fileNameIn = kb.nextLine();
		String fileNameOut = fileNameIn.replace(".txt", "_output") + ".txt";
		kb.close();
		
		//Create try with resources with a BufferedReader to read the matrix and BufferedWriter to write output file
		try (
			BufferedReader fileInput = new BufferedReader(new FileReader(new File(fileNameIn)));
			BufferedWriter fileOutput = new BufferedWriter(new FileWriter(new File(fileNameOut)));
			) {
			for(int row = 0; row < MATRIX_SIZE; row++) {
				//Read the line as a string and convert to char array
				line = fileInput.readLine();
				line = line.replaceAll(" ", "");
				split = line.toCharArray();
				//System.out.println(split);
				//Initialize the char array
				for(int elm = 0; elm < MATRIX_SIZE; elm++) {
					matrix[row][elm] = split[elm];
				}
			}
			//Write header to output file
			fileOutput.write("File input: " + fileNameIn);
			fileOutput.write("\n-----------------------");
			//Reflexive/Antireflexive calls and writes to file
			String reflexive = reflexive(matrix);
			fileOutput.write("\nReflexive - " + reflexive);
			//Reflexive and antireflexive are mutually exclusive on non-empty matrices, no need to call 
			//the method if we already know it is reflexive
			if(reflexive.equals("yes"))
				fileOutput.write("\nAntireflexive - no");
			else
				fileOutput.write("\nAntireflexive - " + antiReflexive(matrix));
			//Symmetric and antisymmetric calls and writes to file
			fileOutput.write("\nSymmetric - " + symmetric(matrix));
			fileOutput.write("\nAntisymmetric - " + antiSymmetric(matrix));
		} 
		//Catch exception when file is not able to be found
		catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
		//Catch exception when there is an issue with input/output
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String antiSymmetric(char[][] matrix) {
		//Iterate through matrix looking for a 1, check the symmetrical value for a 1
		for(int row = 0; row < MATRIX_SIZE; row++) {
			for(int col = 0; col < MATRIX_SIZE; col++) {
				//If a one is found and there is a one at its symmetrical position, and col != row, return no
				if(matrix[row][col] == '1' && matrix [col][row] == '1' && col != row) {
					return "no";
				}
			}
		}
		//If matrix is traversed without finding any symmetry, return yes
		return "yes";
	}

	private static String symmetric(char[][] matrix) {
		//Iterate through matrix looking for a 1, check the symmetrical value for a 1
		for(int row = 0; row < MATRIX_SIZE; row++) {
			for(int col = 0; col < MATRIX_SIZE; col++) {
				//If a one is found and there is no one at its symmetrical position, return no
				if(matrix[row][col] == '1' && matrix [col][row] != '1') {
					return "no";
				}
			}
		}
		//If matrix is traversed without finding any asymmetry, return yes
		return "yes";
	}

	private static String antiReflexive(char[][] matrix) {
		//Iterate through matrix values where (a,a), or rather (i=i)
		//Return no if that value is ever 1
		for(int i = 0; i < MATRIX_SIZE; i++) {
			if(matrix[i][i] == '1')
				return "no";
		}
		//Return yes if all values where i=i are 0
		return "yes";
	}

	private static String reflexive(char[][] matrix) {
		//Iterate through matrix values where (a,a), or rather (i=i)
		//Return no if that value is ever not 1
		for(int i = 0; i < MATRIX_SIZE; i++) {
			if(matrix[i][i] != '1')
				return "no";
		}
		//Return yes if all values where i=i are 1
		return "yes";
	}
		
}
	

