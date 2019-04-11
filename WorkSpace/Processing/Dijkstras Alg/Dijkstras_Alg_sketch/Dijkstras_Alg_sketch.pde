// Dijkstra's algorithm
// Write the code for the method "dijkstra"

class Dijkstra {
  final double I = Double.POSITIVE_INFINITY;
  void main(String[] args) {
    double[][] W = {{0,7,4,6,1},
             {I,0,I,I,I},
             {I,2,0,5,I},
             {I,3,I,0,I},
             {I,I,I,1,0}};
    
    ArrayList<String> F = new ArrayList<String>();
    dijkstra(W.length, W, F);
    outputEdges(F);
  }
  
void dijkstra(int n, double[][] W, ArrayList<String> F) {
    int i, vnear;
    PVector edge;
    int[] touch = new int[n];
    double[] length = new double[n];
    
    for(int i = 2; i <= n; i++) {
      touch[i] = 1;
      length[i] = W[1][i];
    }
    
    for(int i = 1; i < n; i++) {
      min = I;
      for(i = 2; i <= n; i++) {
        if(0 <= length[i] < min) {
          min = length[i];
          vnear = i;
        }
      }
  }
  
  
  
void outputEdges(ArrayList<String> l) {
    for(int i = 0; i < l.size() - 1; i++)
      System.out.print(l.get(i) + ", ");
    System.out.println(l.get(l.size() - 1));
  }
}
