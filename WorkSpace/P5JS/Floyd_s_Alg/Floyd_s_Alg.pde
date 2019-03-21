final static int I = Integer.MAX_VALUE;

final int[][] W = {{0, 1, I, 1, 5},
              {9, 0, 3, 2, I},
              {I, I, 0, 4, I},
              {I, I, 2, 0, 3},
              {3, I, I, I, 0}};
int[][] D = new int[5][5];

void setup() {
  size(500, 500);
  noLoop();
  rectMode(CORNER);
}

void draw() {
  floyd(5, W, D);
}

// Floyd's algorithm that computes the lengths of the shortest paths.
// Implement this method. Use the pseudocode to help you.
public void floyd(int n, final int[][] W, int[][] D){
  int i, j, k;
  D = W;
  for(k = 0; k < n; k++ ) {
    for(i = 0; i < n; i++) {
      for(j = 0; j < n; j++) {
        if(D[i][k] == I || D[k][j] == I) {
          D[i][j] = D[i][j];
        }
        else {
          D[i][j] = min(D[i][j], D[i][k] + D[k][j]);
        }
      }
    }
    System.out.println();
    printArr(5, D);
    displayArr(5, D); 
  }
}
  
// Helper method that prints an array.
private void printArr(int n, int[][] arr) {
  for(int i = 0; i < n; i++) {
    System.out.print("[");
    for(int j = 0; j < n; j++) {
      if(arr[i][j] >= I)
        System.out.print("I" + " ");
      else
        System.out.print(arr[i][j] + " ");
    }
    System.out.println("]");
    
  }
}

void displayArr(int n, int[][] arr) {
  clear();
  for(int i = 0; i < n; i++) {
    for(int j = 0; j < n; j++) {
      
        push();
        translate(i*50, j*50);
        //if(arr[i][j] == I) {
        //  fill(0,0);
        //}
        //else {
        fill(arr[i][j]*arr[i][j]*arr[i][j]);
        //}
        rect(i*50,j*50, 50, 50);
        //text(arr[i][j],i,95);
        pop();
    }
  }
}
