package com.codingexercise.algorithm;

/**
 * For any given N x M matrix with integers, MaxSum provides maximum possible sum by moving upto eight positions.
 * <ol>
 * <li> This class assumes that the input will always contain integers</li>
 * </ol>
 * 
 * @author Praveen Kulkarni
 *
 */
public class MaxSum {
	
	int X, Y; // row and columns
	int[][] costs ;//= new int[X][Y];
	
	public MaxSum(int[][] costs, int X, int Y) {
		 this.costs = costs;
		 this.X=X;
		 this.Y=Y;
	 }

	 public int getMaxSumPath() {
		 // return if costs isnt initialized.
		 if(this.costs == null || this.costs.length== 0) return 0;
		 
		 int maxSumPath[][] = new int[X][Y];
		 
		 initMaxSumTable(maxSumPath);
		 
		 setUpMaxSumTable(maxSumPath);
		 
		 // loop thru rest and find the maximum 
		 getMaxSumPath(maxSumPath);
		 // last element has the max sum
		 return maxSumPath[X-1][Y-1];
	 }

	private void getMaxSumPath(int[][] maxSumPath) {
		for(int r = 1;r <= X-1; r++) {
			 for(int c = 1; c<= Y-1; c++) {
				 if(r<X-1&& c<Y-1) 
					 maxSumPath[r][c] = getMaxFromEightPos(maxSumPath, r, c) + this.costs[r][c];
				 else  
					 maxSumPath[r][c] = getMaxFromThreePos(maxSumPath, r, c) + this.costs[r][c]; 
			 }
		 }
	}

	private void setUpMaxSumTable(int[][] maxSumPath) {
		// copy first row to maxSumPath
		 copyFirstRow(maxSumPath);
		// copy first column
		 copyFirstColumn(maxSumPath);
	}

	private void initMaxSumTable(int[][] maxSumPath) {
		for(int i=0;i<X;i++) {
			 for(int j=0;j<Y;j++) {
				 maxSumPath[i][j]= 0;
			 }
		 }
		maxSumPath[0][0] = this.costs[0][0];
	}

	private void copyFirstColumn(int[][] maxSumPath) {
		for(int j=1;j<=X-1; j++) {
			 maxSumPath[j][0] = maxSumPath[j-1][0] + this.costs[j][0];
		 }
	}

	private void copyFirstRow(int[][] maxSumPath) {
		for(int i=1;i<=Y-1;i++) {
			 maxSumPath[0][i] = maxSumPath[0][i-1] + this.costs[0][i];
		 }
	}

	private int getMaxFromThreePos(int[][] maxSumPath, int r, int c) {
		return Math.max(maxSumPath[r-1][c], 
		 			Math.max(maxSumPath[r-1][c-1], 
			 				maxSumPath[r][c-1]));
	}

	private int getMaxFromEightPos(int[][] maxSumPath, int r, int c) {
		return Math.max(maxSumPath[r-1][c], Math.max(maxSumPath[r-1][c-1],  
				 					Math.max(maxSumPath[r][c-1], Math.max(this.costs[r-1][c+1],
				 							Math.max(this.costs[r][c+1], Math.max(this.costs[r+1][c+1],	
				 									Math.max(this.costs[r+1][c], this.costs[r-1][c+1])))))));
	}
}
