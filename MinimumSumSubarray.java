/*Runtime: O(N) 
Apollo Schaefer
Description: This program takes integers as input and stores them into an ArrayList which is passed to the minSumSubarray method.
This method uses a slight alteration of Kadane's algorithm to find and return the array that forms the smallest sub subarray.
*/
import java.util.ArrayList;
import java.util.Scanner;
public class Problem1 {
	public static void main(String[] args) {
		//Get the start time so I can subtract it from the end time to find run time
		long startTime = System.currentTimeMillis();
		//Create Scanner object for keyboard and prompt user for integers
		Scanner kb = new Scanner(System.in);
		ArrayList<Integer> intArrList = new ArrayList<>();
		System.out.print("Enter integers, followed by *end* to indicate the end of the list: ");
		//While there is still input, enter loop
		while(kb.hasNext()) {
			//in the case of an int, add to list
			if(kb.hasNextInt()) {
				intArrList.add(kb.nextInt());
			}
			//keyword *end* indicates end of list of integers and exits the loop
			else if(kb.next().equalsIgnoreCase("end")) {
				break;
			}
			//If input is neither of those, invalid input
			else {
				System.out.println("Invalid input.");
			}
		}
		//Call method, pass ArrayList<Integer>, return a Integer[] into variable smallestSub
		Integer[] smallestSub = minSumSubarray(intArrList);
		//Output results
		System.out.println("Contiguous subarray with smallest sum: ");
		for(Integer elm: smallestSub)
			System.out.print(elm + " ");
		//Get time at end of program and output the difference between end and start time(run time)
		long endTime = System.currentTimeMillis();
		System.out.println();
		System.out.println(endTime - startTime + "ms");
	}

	public static Integer[] minSumSubarray(ArrayList<Integer> intArrList) {
		//Declare necessary variables:
		int smallestSum = Integer.MAX_VALUE;
		int currentSum = 0;
		int start = 0;
		int temp = 0;
		int end= 0;

		//Loop
		for(int i = 0; i < intArrList.size(); i++) {
			//Add next value in line to the currentSum
			currentSum += intArrList.get(i);
			/*Compare current subarray sum and smallest subarray sum
			If current subarray is smaller, smallestSum is updated to match, and current index is marked as the end of the smallest subarray
			the start variable is updated to the most previous beginning of a subarray (one index greater than the most recent index to set
			the currentSum greater than 0, or 0 if subarray has not been reset yet).
			 */
			if(currentSum < smallestSum) {
				smallestSum = currentSum;
				end = i;
				start = temp;
			}
			/*If currentSum is > 0, adding elements are no longer beneficial to finding smallest subarray, start a new 
			subarray by setting current to 0.
			Set temp to where next subarray begins, will be the potential start for smallest subarray*/
			if(currentSum > 0) {
				currentSum = 0;
				temp = i + 1;
			}
		}
		//return the list from start-end index correctly in Integer[] form
		return intArrList.subList(start, end + 1).toArray(new Integer[0]);
	}
}
