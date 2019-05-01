//Cody Ryan HW8 Submission
// Dijkstra's algorithm
// Write the code for the method "dijkstra"

import java.util.ArrayList;

public class Dijkstra {
	final static double I = Double.POSITIVE_INFINITY;
	public static void main(String[] args) {
		double[][] W = {{0,7,4,6,1},
					 	{I,0,I,I,I},
					 	{I,2,0,5,I},
					 	{I,3,I,0,I},
					 	{I,I,I,1,0}};
		
		ArrayList<String> F = new ArrayList<String>();
		dijkstra(W.length, W, F);
		outputEdges(F);
	}
	
	public static void dijkstra(int n, double[][] W, ArrayList<String> F) {
		
		int i;
		int vnear = 0;
		String e;
		int[] touch = new int[n]; //length-5 array
		double[] length = new double[n]; //length-5 array
		for(i = 2; i <= n-1; i++) {
			touch[i] = 1;
			length[i] = W[1][i];
		}
		for(int repeat = 0; repeat < n; repeat++) {
			double min = I; //Set min to INFINITY every time through the repeat loop
			for(i = 2; i <= n-1; i++) {
				if(0 <= length[i] && length[i] < min) {
					min = length[i];
					vnear = i;
				}
			}
			e = String.format("(%1$s, %2$s)", touch[vnear], vnear);
			F.add(e);
			for(i = 2; i <= n-1; i++) {
				if(length[vnear] + W[vnear][i] < length[i]) {
					length[i] = length[vnear] + W[vnear][i];
					touch[i] = vnear;
				}
			}
			length[vnear] = -1;
		}
	}
	
	private static void outputEdges(ArrayList<String> l) {
		for(int i = 1; i < l.size()-1; i++)
			System.out.print(l.get(i) + ", ");
		System.out.println(l.get(l.size() - 1));
	}
}