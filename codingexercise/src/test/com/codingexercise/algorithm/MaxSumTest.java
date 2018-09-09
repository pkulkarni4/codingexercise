package test.com.codingexercise.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.codingexercise.algorithm.MaxSum;

public class MaxSumTest {

	@Test
	void maxSumTest_whenGivenMatrixIsEmpty_Expect0() {
		MaxSum max = new MaxSum(null, 0, 0);
		int maxSum = max.getMaxSumPath();
		assertEquals(maxSum, 0);
		
	}
	@Test
	void maxSumTest_whenGivenMatrixHasNoNegativeNumbers() {
		int[][] costs = { {1,2,3}, {4,5,6}, {7, 8, 9}};
		MaxSum max = new MaxSum(costs, costs.length, costs[0].length);
		int maxSum = max.getMaxSumPath();
		assertEquals(maxSum, 31);
	}
	
	@Test
	void maxSumTest_whenGivenMatrixHasNegativeNumbers() {
		int[][] costs2 = { {4, 8, 100, -1000}, 
				           {70, -10, 2000, 70}, 
				           {-5, -21, -6, 8},
				           {10000, -20, 15, 21}
				         };
		MaxSum max2 = new MaxSum(costs2, costs2.length, costs2[0].length);
		int maxSum= max2.getMaxSumPath();
		assertEquals(maxSum, 10085);
	}

}
