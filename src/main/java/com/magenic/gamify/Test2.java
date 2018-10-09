package com.magenic.gamify;

import java.util.HashMap;
import java.util.Map;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(647));

	}
	
	public static int solution (int n) {
		String binaryN = Integer.toBinaryString(n);
		System.out.println(binaryN);
		int maxLength = 0;
		int length = 0;
		int i = binaryN.indexOf("1");
		int j = 0;
		j = i + 1;
		while (i >= 0 && i < binaryN.length() - 1) {
			if (binaryN.charAt(i) == '1') {
				while (binaryN.charAt(j) != '1' && j < binaryN.length() - 1) {
					length++;
					j++;
				}
				if (length > maxLength && binaryN.charAt(j) == '1') {
					maxLength = length;
					
				}
				length = 0;
				i = j;
				if (j < binaryN.length() - 1) {
					j = j +1;
				}
			}
		}
		
		return maxLength ;
	}
	
	/*A non-empty array A consisting of N integers is given. Array A represents numbers on a tape.

Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].

The difference between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|

In other words, it is the absolute difference between the sum of the first part and the sum of the second part.

For example, consider array A such that:

  A[0] = 3
  A[1] = 1
  A[2] = 2
  A[3] = 4
  A[4] = 3
We can split this tape in four places:

P = 1, difference = |3 − 10| = 7 
P = 2, difference = |4 − 9| = 5 
P = 3, difference = |6 − 7| = 1 
P = 4, difference = |10 − 3| = 7 
Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A of N integers, returns the minimal difference that can be achieved.

For example, given:

  A[0] = 3
  A[1] = 1
  A[2] = 2
  A[3] = 4
  A[4] = 3
the function should return 1, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [2..100,000];
each element of array A is an integer within the range [−1,000..1,000].*/
	public static int solution2(int[] A) {
        // write your code in Java SE 8
        int right = 0;
        int left = 1;
        int diff = 0;
        
        int minDiff = diff(right, left, A);
        
        for (int i = 1; i < A.length-1; i++) {
            diff= diff(right + i, left + i, A);
            //System.out.println("diff: " + diff);
            if (diff < minDiff) {
                minDiff = diff;
            }
        }
        return minDiff;
    }
    
    public static int diff (int right, int left, int[] A) {
        // add until right
        int sum1=0, sum2=0 ;
        for (int i = 0; i <= right; i++) {
            sum1+= A[i];
            //System.out.println("sum1: " + sum1);
        }
        
        //add from left
        for (int j = left; j < A.length; j++) {
            sum2+= A[j];
            //System.out.println("sum2: " + sum2);
        }
        //System.out.println("sum1-sum2 = " + (sum1-sum2));
        return Math.abs(sum1-sum2);
    }
    
    /*A non-empty array A consisting of N integers is given.

A permutation is a sequence containing each element from 1 to N once, and only once.

For example, array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
    A[3] = 2
is a permutation, but array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
is not a permutation, because value 2 is missing.

The goal is to check whether array A is a permutation.

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A, returns 1 if array A is a permutation and 0 if it is not.

For example, given array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
    A[3] = 2
the function should return 1.

Given array A such that:

    A[0] = 4
    A[1] = 1
    A[2] = 3
the function should return 0.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [1..1,000,000,000].
*/
    
    public int solution3(int[] A) {
        // write your code in Java SE 8
        Map<Integer, Integer> elems = new HashMap<Integer, Integer>();
        // put the elements in a map
        for (int i = 0; i < A.length; i++) {
            // check if elem already in map, if yes return 0
            if (elems.get(A[i]) != null) {
                return 0;
            }
            elems.put(A[i],1);
        }
        
        // assuming non of the elems repeated, search if there is a missing elem
        //return 0 if there is something missing
        for (int j = 1; j <= A.length; j++ ) {
            if (elems.get(j) == null) {
                return 0;
            }
        }
        return 1;
    }
    
    /*A small frog wants to get to the other side of a river. The frog is initially located on one bank of the river (position 0) and wants to get to the opposite bank (position X+1). Leaves fall from a tree onto the surface of the river.

You are given an array A consisting of N integers representing the falling leaves. A[K] represents the position where one leaf falls at time K, measured in seconds.

The goal is to find the earliest time when the frog can jump to the other side of the river. The frog can cross only when leaves appear at every position across the river from 1 to X (that is, we want to find the earliest moment when all the positions from 1 to X are covered by leaves). You may assume that the speed of the current in the river is negligibly small, i.e. the leaves do not change their positions once they fall in the river.

For example, you are given integer X = 5 and array A such that:

  A[0] = 1
  A[1] = 3
  A[2] = 1
  A[3] = 4
  A[4] = 2
  A[5] = 3
  A[6] = 5
  A[7] = 4
In second 6, a leaf falls into position 5. This is the earliest time when leaves appear in every position across the river.

Write a function:

class Solution { public int solution(int X, int[] A); }

that, given a non-empty array A consisting of N integers and integer X, returns the earliest time when the frog can jump to the other side of the river.

If the frog is never able to jump to the other side of the river, the function should return −1.

For example, given X = 5 and array A such that:

  A[0] = 1
  A[1] = 3
  A[2] = 1
  A[3] = 4
  A[4] = 2
  A[5] = 3
  A[6] = 5
  A[7] = 4
the function should return 6, as explained above.

Write an efficient algorithm for the following assumptions:

N and X are integers within the range [1..100,000];
each element of array A is an integer within the range [1..X].*/
    
    public int solution4(int X, int[] A) {
        // write your code in Java SE 8
        Map<Integer, Integer> leaves = new HashMap<Integer, Integer>();
        int totalLeaves = 0;
        //init map with leave positions with zero leaves
        for (int i = 1; i <= X; i++) {
            leaves.put(i, 0);
        }
        
        //check array
        for (int j = 0; j < A.length; j++) {

                
            
            if (leaves.get(A[j]) != 1) {
                // put leaf in position, will always only put 1
                leaves.put(A[j],1);
                // count leaves put so far
                totalLeaves += leaves.get(A[j]);
            }
            
            // check if all positions have been filled
            if (totalLeaves == leaves.size()) {
                return j;
            }
        }
        return -1;
    }
	

}
