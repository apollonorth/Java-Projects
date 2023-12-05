/*
Programmer: Apollo Schaefer

Description: This program takes a .txt file with a number of airport codes/locations, as well as a matrix with rows and column 
equal to the number of airport codes, and finds the shortest path between two airports. This program uses Dijkstra's algorithm.

Requirements: Requires a file airport_codes.txt in the same directory as this java program containing a line of 
airport codes/locations. Also requires a file distance_matrix.txt that contains a 2d matrix with values indicating the length
in miles between airports. Rows and columns should be equal to number of airport codes in airport_codes.txt. 1000000 represents
no direct path, 0 represents distance from an airport/location to itself.
*/
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Project2SourceCode {
	private static List<List<Integer>> graph = new ArrayList<>(); //Adjacency list using ArrayList class
	private static List<String> airportCodeList = new ArrayList<>(); //List for airport codes
	private static int numOfAirports;

	public static void main(String[] args) {
		String start = "";
		String end = "";
        try ( //Try with resources to auto-close stream and catch IO exceptions
        Scanner airportCodes = new Scanner(new File("airport_codes.txt"));
        Scanner distanceMatrix = new Scanner(new File("distance_matrix.txt"));
        Scanner kb = new Scanner(System.in))
        {
        	while(airportCodes.hasNext()) { //Read airport codes into list
        		airportCodeList.add(airportCodes.next());
        	}
       
        	numOfAirports = airportCodeList.size();
        	int row = 0;
        	
        	//While file has more data and we have not read the correct number of rows yet, loop
        	while (distanceMatrix.hasNextLine() && row < numOfAirports) { 
        		//Encountered error due to blank lines, this check will skip the loop iteration if that is the case
        		String line = distanceMatrix.nextLine();
        		if(line.isEmpty())
        			continue;
        		
        		//Create array of distances from the row, split by space
        		String[] distancesOfRow = line.split(" "); 
        		//Add new list in graph to store values
        		graph.add(new ArrayList<Integer>());
        		
        		//Loop through this row of distances (each column) and add to graph list for corresponding row
        		for(int col = 0; col < distancesOfRow.length; col++) {
        			int distance = Integer.parseInt(distancesOfRow[col]);
        			graph.get(row).add(distance);
        		}
        		row++;
        	}
        	
            System.out.print("Start: ");
            start = kb.next();
            System.out.print("End: ");
            end = kb.next();
            //put in Uppercase
            start = start.toUpperCase();
            end = end.toUpperCase();
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        //Check for incorrect input
        if(!airportCodeList.contains(start) || !airportCodeList.contains(end)) {
        	System.out.println("One or more airport codes are incorrect.");
        	return;
        }
        
        //Call Dijkstra that returns an ArrayList of Strings, with index 0 being the distance and the following indices the airport path
        ArrayList<String> output = dijkstras(start, end);
        //call print method
        printShortestPath(output, start, end);
       
      
        //Extra Credit Matrix
       
        //Use similar code but slightly adjusted
        //Create 2d matrix to store in
        int[][] distMatrix = new int[numOfAirports][numOfAirports];
        
        //loop through each airport and store the shortest paths to each other airport into above 2d matrix
        for(int i = 0; i < numOfAirports; i++) {
        	distMatrix[i] = dijkstrasEC(i);
        }
        
        //call print method
        printDistMatrix(distMatrix); 
    }
	
	public static ArrayList<String> dijkstras(String start, String end) {
		//Create output ArrayList, translate String start and end into corresponding vertices
	    ArrayList<String> output = new ArrayList<>();
	    int startIndex = airportCodeList.indexOf(start);
	    int endIndex = airportCodeList.indexOf(end);

	    //Create array of distances and array of previous airports to track path
	    int[] distances = new int[numOfAirports];
	    int[] prev = new int[numOfAirports];
	    //Initially vertices are set to max distance, no previous airport
	    Arrays.fill(distances, Integer.MAX_VALUE);
	    Arrays.fill(prev, -1);
	    //Start airport is 0 miles from itself
	    distances[startIndex] = 0;

	    // PriorityQueue is necessary for the order of Dijkstra's. Regular Queue would not choose the next shortest path
	    PriorityQueue<Integer> unvisitedQ = new PriorityQueue<>();
	    unvisitedQ.add(startIndex);

	    while (!unvisitedQ.isEmpty()) {
	    	//set current and dequeue from queue
	        int current = unvisitedQ.poll();

	        //Loop through adjacent vertices
	        for (int i = 0; i < graph.get(current).size(); i++) {
	            int edgeDistance = graph.get(current).get(i);
	            //Vertex is adjacent if value is greater than 0 (indicating vertex is the start vertex), and less than 1000000 (Indicating no direct route)
	            if (edgeDistance > 0 && edgeDistance < 1000000) {
	            	//Calculate distance to current from start, through current
	                int altPathDist = distances[current] + edgeDistance;
	                //If this path is shorter, update distances at current index, as well as path
	                if (altPathDist < distances[i]) {
	                    distances[i] = altPathDist;
	                    prev[i] = current;
	                    //Update the vertex in PriorityQueue with new distance by removing and adding back
	                    unvisitedQ.remove(i);
	                    unvisitedQ.add(i);
	                }
	            }
	        }
	    }

	    //Check if there is a path to the end, indicated by value still being Integer.MAX_VALUE
	    //Our input will never have this occur, but good edge case to check for
	    if (distances[endIndex] == Integer.MAX_VALUE) {
	    	//-1 indicates no path
	        output.add("-1");
	        return output;
	    }

	    //Create full path, store in List of Integers, representing the index of the airport
	    List<Integer> path = new ArrayList<>();
	    //Backtrack from endIndex using the prev array
	    for (int airport = endIndex; airport != -1; airport = prev[airport]) {
	        path.add(airport);
	    }

	    // Add distance in String version, whatever is in the distances array at the endIndex is the shortest dist
	    output.add(0, "" + distances[endIndex]);
	    //Add the pathing(currently backwards) to the output ArrayList<String> 
	    for (int index : path) {
	        output.add(airportCodeList.get(index));
	    }
	    //Return 
	    return output;
	}	
	
	public static int[] dijkstrasEC(int startIndex) {
	    //Create array of distances
	    int[] distances = new int[numOfAirports];
	   
	    //Initially vertices are set to max distance, no previous airport
	    Arrays.fill(distances, Integer.MAX_VALUE);
	 
	    //Start airport is 0 miles from itself
	    distances[startIndex] = 0;

	    // PriorityQueue is necessary for the order of Dijkstra's. Regular Queue would not choose the next shortest path
	    PriorityQueue<Integer> unvisitedQ = new PriorityQueue<>();
	    unvisitedQ.add(startIndex);

	    while (!unvisitedQ.isEmpty()) {
	    	//set current and dequeue from queue
	        int current = unvisitedQ.poll();

	        //Loop through adjacent vertices
	        for (int i = 0; i < graph.get(current).size(); i++) {
	            int edgeDistance = graph.get(current).get(i);
	            //Vertex is adjacent if value is greater than 0 (indicating vertex is the start vertex), and less than 1000000 (Indicating no direct route)
	            if (edgeDistance > 0 && edgeDistance < 1000000) {
	            	//Calculate distance to current from start, through current
	            	int altPathDist = distances[current] + edgeDistance;
	            	//If this path is shorter, update distances at current index
	            	if (altPathDist < distances[i]) {
	            		distances[i] = altPathDist;
	            		//Update the vertex in PriorityQueue with new distance by removing and adding back
	            		unvisitedQ.remove(i);
	            		unvisitedQ.add(i);
	            	}
	            }
	        }
	    }

	    //Return 
	    return distances;
	}	

	public static void printShortestPath(ArrayList<String> output, String start, String end) {
		System.out.println("The shortest distance between " + start + " and " + end + " is " + output.get(0) + " miles."); 
		System.out.println("The shortest route between " + start + " and " + end + " is");
		//Path was stored in output backwards, so print is from end to start
		for(int i = output.size() - 1; i > 0; i--) {
			//We only want arrows to separate airports, not to exist in the beginning or end of the line
			if(i != output.size() - 1)
				System.out.print(" -> ");
			System.out.print(output.get(i));
		}
	}

	public static void printDistMatrix(int[][] distMatrix) {
		System.out.println("\n\n\nShortest Distance Matrix: \n");
		//Print airport codes
		System.out.print("    ");
		for(int i = 0; i < numOfAirports; i++) {
			System.out.print(airportCodeList.get(i) + "  ");
		}

		//Traverse matrix and print each row as airport code, followed by values
		for(int row = 0; row < numOfAirports; row++) {
			System.out.print("\n" + airportCodeList.get(row) + " ");
			for(int col = 0; col < numOfAirports; col++) {
				//Format the table so numbers fit in the correct row/column
				System.out.printf("%4d ", distMatrix[row][col]);
			}
		}
	}
}
