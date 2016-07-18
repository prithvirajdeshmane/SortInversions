/**
 * @author: Prithviraj Deshmane
 * 
 * Sort Inversions: Count the number of inversions needed for sorting a large input. 
 * 
 * This problem is solved by piggybacking on the Merge Sort technique to achieve 
 * a complexity of O(n*log(n)) as opposed to the O(n^2) achieved through Brute Force.
 */

import java.io.File;
import java.util.Scanner;

public class Inversions {
	
	private static int inputSize = 100000;
	private static long inversionCount = 0;

	public static void main(String[] args) {
		File input = new File("input.txt");
		int[] inputArray = new int[inputSize];
		inputArray = readInput(input);
		//displayArray(inputArray);
		
		inputArray = mergeSort(inputArray);
		//System.out.println("Sorted: ");
		//displayArray(inputArray);
		//int count = countInversions(inputArray);
		System.out.println("Inversions: " + inversionCount);
	}
	
	private static int[] mergeSort(int[] input) {
		int[] result = new int[input.length];
		
		if(input.length == 1) return input;
		
		if(input.length == 2) {
			return swap(input);
		}
		
		//split and make recursive calls
		int split = (input.length+1)/2;
		
		int[] leftPartition = new int[split];
		for (int i = 0; i < split; i++) {
			leftPartition[i] = input[i];			
		}
		
		int[] rightPartition = new int[input.length - split];
		for (int i = 0, j = split; j <input.length; i++, j++) {
			rightPartition[i] = input[j];
		}
		
		leftPartition = mergeSort(leftPartition);
		rightPartition = mergeSort(rightPartition);
		
		//merge in sorted order
		result = mergeArrays(leftPartition, rightPartition);
		return result;
	}
	
	private static int[] mergeArrays(int[] arr1, int[] arr2) {
		int len = arr1.length + arr2.length;  
		int[] result = new int[len];
		
		int i = 0, j = 0;
		
		for (int k = 0; k < len; k++) {
			if(arr1[i] < arr2[j]) {
				result[k] = arr1[i];
				i++;
			} else {
				int inv = (arr1.length - i);
				inversionCount += inv;
				result[k] = arr2[j];
				j++;
			}
			if(i == arr1.length) {
				result = appendArray(k+1, result, j, arr2);
				break;
			}
			if(j == arr2.length) {
				result = appendArray(k+1, result, i, arr1);
				break;
			}
		}
		
		return result;
	}
	
	private static int[] appendArray (int resultIndex, int[] result, 
			int arrayIndex, int[] array) {
		for (int k = resultIndex; k < result.length; k++) {
			result[k] = array[arrayIndex];
			arrayIndex++;
		}
		return result;
	}
	
	private static int[] swap(int[] input) {
		if(input[0] > input [1]) {
			int temp = input[0];
			input[0] = input[1];
			input[1] = temp;
			inversionCount++;
			//displayArray(input);
		}
		return input;		
	}
	
	private static int[] readInput(File inputFile) {
		int[] inputArray = new int[inputSize];
		
		try {
			Scanner sc = new Scanner(inputFile);
			
			for (int i = 0; i < inputSize; i++) {
				inputArray[i] = Integer.parseInt(sc.nextLine());
			}
			
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return inputArray;
	}
	
	private static void displayArray(int[] arr) {
		System.out.println();
		for (int i = 0; i < arr.length; i++) {
			if(i == 0 ) System.out.print("[");
			System.out.print(arr[i]);
			if( !(i == arr.length-1)) {
				System.out.print(", ");
			} else {
				System.out.print("]");
			}
		}
	}

}
