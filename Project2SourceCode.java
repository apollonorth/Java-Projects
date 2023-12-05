//Programmer: Apollo Schaefer

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Project2SourceCode {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); //

        try (
        BufferedReader airportCodes = new BufferedReader(new FileReader("airport_codes.txt"));
        BufferedReader distanceMatrix = new BufferedReader(new FileReader("distance_matrix.txt")))
        {

        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
