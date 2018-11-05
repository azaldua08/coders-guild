package com.magenic.gamify;

import java.util.Arrays;
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
	
    /*You are given N counters, initially set to 0, and you have two possible operations on them:

increase(X) − counter X is increased by 1,
max counter − all counters are set to the maximum value of any counter.
A non-empty array A of M integers is given. This array represents consecutive operations:

if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
if A[K] = N + 1 then operation K is max counter.
For example, given integer N = 5 and array A such that:

    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4
the values of the counters after each consecutive operation will be:

    (0, 0, 1, 0, 0)
    (0, 0, 1, 1, 0)
    (0, 0, 1, 2, 0)
    (2, 2, 2, 2, 2)
    (3, 2, 2, 2, 2)
    (3, 2, 2, 3, 2)
    (3, 2, 2, 4, 2)
The goal is to calculate the value of every counter after all operations.

Write a function:

class Solution { public int[] solution(int N, int[] A); }

that, given an integer N and a non-empty array A consisting of M integers, returns a sequence of integers representing the values of the counters.

Result array should be returned as an array of integers.

For example, given:

    A[0] = 3
    A[1] = 4
    A[2] = 4
    A[3] = 6
    A[4] = 1
    A[5] = 4
    A[6] = 4
the function should return [3, 2, 2, 4, 2], as explained above.

Write an efficient algorithm for the following assumptions:

N and M are integers within the range [1..100,000];
each element of array A is an integer within the range [1..N + 1].*/
    
    public int[] solution5(int N, int[] A) {
        // write your code in Java SE 8
        
        // init N counters as a map
        Map<Integer,Integer> counters = new HashMap<Integer,Integer>();
        for (int i = 1; i <= N; i++) {
            counters.put(i,0);
        }
        int count = 0, maxCount = 0;
        //iterate array A of M length
        for (int j = 0; j < A.length; j++) {
            // if A[K] = X where X is between 1 and N
            if (A[j] >= 1 && A[j] <= N) {
             count = counters.get(A[j]) +1;
             counters.put(A[j], count);
             if (count > maxCount) {
                 maxCount = count;
             }
            }
            // A[K] is N + 1
            else if (A[j] > N) {
                for (int k = 1; k <= N; k++) {
                    counters.put(k, maxCount);
                }               
            }
        }
        //https://stackoverflow.com/questions/31394715/convert-integer-to-int-array
        Integer [] values = counters.values().toArray(new Integer[counters.size()]);
        int [] result = Arrays.stream(values).mapToInt(Integer::intValue).toArray();
        return result;
    }
    
    // better sol
    public int[] solution6(int N, int[] A) {
        // write your code in Java SE 8
       
       // init N counters as an array
       int [] counters = new int [N];
       for (int i = 0; i < N; i++) {
           counters[i] = 0;
       }
       int count = 0, maxCount = 0;
       //iterate array A of M length
       for (int j = 0; j < A.length; j++) {
           // if A[K] = X where X is between 1 and N
           if (A[j] >= 1 && A[j] <= N) {
            count = counters[A[j] - 1] +1;
            counters[A[j]-1] = count;
            if (count > maxCount) {
                maxCount = count;
            }
           }
           // A[K] is N + 1
           else if (A[j] > N) {
               for (int k = 1; k <= N; k++) {
                   counters[k-1] = maxCount;
               }               
           }
       }
      
       return counters;
   }
    
    /*A non-empty array A consisting of N integers is given. The consecutive elements of array A represent consecutive cars on a road.

Array A contains only 0s and/or 1s:

0 represents a car traveling east,
1 represents a car traveling west.
The goal is to count passing cars. We say that a pair of cars (P, Q), where 0 ≤ P < Q < N, is passing when P is traveling to the east and Q is traveling to the west.

For example, consider array A such that:

  A[0] = 0
  A[1] = 1
  A[2] = 0
  A[3] = 1
  A[4] = 1
We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A of N integers, returns the number of pairs of passing cars.

The function should return −1 if the number of pairs of passing cars exceeds 1,000,000,000.

For example, given:

  A[0] = 0
  A[1] = 1
  A[2] = 0
  A[3] = 1
  A[4] = 1
the function should return 5, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer that can have one of the following values: 0, 1.*/
    
    public int solution7(int[] A) {
        // write your code in Java SE 8
        int pairs = 0;
        for (int p = 0; p < A.length-1; p++) {
            if (A[p] == 0) {
                for (int q = p+1; q < A.length; q++) {
                    if (A[q] == 1) pairs++;
                }
            }
        }
        if (pairs > 1000000000) return -1;
        
        return pairs;
    }
    
    /*A DNA sequence can be represented as a string consisting of the letters A, C, G and T, which correspond to the types of successive nucleotides in the sequence. Each nucleotide has an impact factor, which is an integer. Nucleotides of types A, C, G and T have impact factors of 1, 2, 3 and 4, respectively. You are going to answer several queries of the form: What is the minimal impact factor of nucleotides contained in a particular part of the given DNA sequence?

The DNA sequence is given as a non-empty string S = S[0]S[1]...S[N-1] consisting of N characters. There are M queries, which are given in non-empty arrays P and Q, each consisting of M integers. The K-th query (0 ≤ K < M) requires you to find the minimal impact factor of nucleotides contained in the DNA sequence between positions P[K] and Q[K] (inclusive).

For example, consider string S = CAGCCTA and arrays P, Q such that:

    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6
The answers to these M = 3 queries are as follows:

The part of the DNA between positions 2 and 4 contains nucleotides G and C (twice), whose impact factors are 3 and 2 respectively, so the answer is 2.
The part between positions 5 and 5 contains a single nucleotide T, whose impact factor is 4, so the answer is 4.
The part between positions 0 and 6 (the whole string) contains all nucleotides, in particular nucleotide A whose impact factor is 1, so the answer is 1.
Write a function:

class Solution { public int[] solution(String S, int[] P, int[] Q); }

that, given a non-empty string S consisting of N characters and two non-empty arrays P and Q consisting of M integers, returns an array consisting of M integers specifying the consecutive answers to all queries.

Result array should be returned as an array of integers.

For example, given the string S = CAGCCTA and arrays P, Q such that:

    P[0] = 2    Q[0] = 4
    P[1] = 5    Q[1] = 5
    P[2] = 0    Q[2] = 6
the function should return the values [2, 4, 1], as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
M is an integer within the range [1..50,000];
each element of arrays P, Q is an integer within the range [0..N − 1];
P[K] ≤ Q[K], where 0 ≤ K < M;
string S consists only of upper-case English letters A, C, G, T.*/
    
    public int[] solution8(String S, int[] P, int[] Q) {
        // write your code in Java SE 8
        Map<String, Integer> nucleoTides = new HashMap<String, Integer>();
        nucleoTides.put("A", 1);
        nucleoTides.put("C", 2);
        nucleoTides.put("G", 3);
        nucleoTides.put("T", 4);
        
        int[] result = new int[P.length];
        
        for (int i = 0; i < P.length; i++) {
            result[i] = minImpactFactor(S, P[i], Q[i], nucleoTides);       
        }
        return result;
    }
    
    public int minImpactFactor(String S, int P, int Q, Map<String, Integer> nucleoTides) {
        int impactFactor = 0, minImpactFactor = 0;
        minImpactFactor = nucleoTides.get(String.valueOf(S.charAt(P)));

        for (int j = P+1; j <= Q; j++) {
            impactFactor = nucleoTides.get(String.valueOf(S.charAt(j)));
            if (impactFactor < minImpactFactor) minImpactFactor = impactFactor;
        }
        return minImpactFactor;
    }
    
    /*A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains at least two elements). The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).

For example, array A such that:

    A[0] = 4
    A[1] = 2
    A[2] = 2
    A[3] = 5
    A[4] = 1
    A[5] = 5
    A[6] = 8
contains the following example slices:

slice (1, 2), whose average is (2 + 2) / 2 = 2;
slice (3, 4), whose average is (5 + 1) / 2 = 3;
slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
The goal is to find the starting position of a slice whose average is minimal.

Write a function:

class Solution { public int solution(int[] A); }

that, given a non-empty array A consisting of N integers, returns the starting position of the slice with the minimal average. If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.

For example, given array A such that:

    A[0] = 4
    A[1] = 2
    A[2] = 2
    A[3] = 5
    A[4] = 1
    A[5] = 5
    A[6] = 8
the function should return 1, as explained above.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [2..100,000];
each element of array A is an integer within the range [−10,000..10,000].*/
    
    public int solution9(int[] A) {
        // write your code in Java SE 8
        double minAverage = average(0,1,A);
        double average = 0;
        int minIndex = 0;
        
        for (int i = 0; i < A.length-1; i++) {
            for (int j = i+1; j < A.length; j++) {
                average = average(i,j,A);
                if (average < minAverage) {
                    minAverage = average;
                    minIndex = i;
                }
            }        
        }
        return minIndex;
    }
    
    public double average(int P, int Q, int[] A) {
        int sum = 0;
       // System.out.println("***P=" + P);
        for (int k=P; k <=Q; k++) {
            sum+=A[k];
        }
        int bel = Q-P+1;
       // System.out.println("sum=" + sum);
       // System.out.println("bel=" + bel);
        double average = sum/(double)(Q-P+1);

        //System.out.println("average=" + average);
        return average;
    }

}
