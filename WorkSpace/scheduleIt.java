// The method scheduleIt is a greedy algorithm for scheduling jobs.

import java.util.Scanner;

public class ScheduleGreedy {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("How many jobs would you like to schedule?");
		int size = keyboard.nextInt();
		int[] jobs = new int[size];
		for(int i = 0; i < size; i++) {
			System.out.println("Please enter the time for job " + (i+1) + ".");
			jobs[i] = keyboard.nextInt();
		}
		keyboard.close();
		
		System.out.print("[");
		printArray(scheduleIt(jobs));
		System.out.println("]");
	}

	public static int[] scheduleIt(int[] jobs)
	{
		

	}
	
	private static void printArray(int[] arr) {
		for(int i = 0; i < arr.length-1; i++)
			System.out.print((arr[i]+1) + ", ");
		System.out.print(arr[arr.length-1]+1);
	}
}