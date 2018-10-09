package com.magenic.gamify;

import com.magenic.gamify.model.Employee;
import com.magenic.gamify.model.Skill;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		Employee employee = new Employee();
//		employee.setId(new Long(7333));
//		employee.setName("Amiel");
//		employee.setUsername("scarface08x");
//		employee.setJobClass("Swordsman");
//		employee.setGuild("Knights of the Round Table");
//		employee.setLevel(40);
//		employee.setExperience(100);
//		employee.setStatus("Active");
//		
//		Employee employee2 = new Employee();
//		employee2.setId(new Long(1234));
//		employee2.setName("John");
//		employee2.setUsername("rudemagician12");
//		employee2.setJobClass("Mage");
//		employee2.setGuild("Wizards Guild");
//		employee2.setLevel(35);
//		employee2.setExperience(80);
//		employee2.setStatus("Active");
//		
//		System.out.println(employee.toString());
//		
//		Skill java = new Skill();
//		java.setId(new Long(1));
//		java.setName("Java");
//		java.setLevel(3);
//		java.setEmployee(employee);
//		
//		Skill java2 = new Skill();
//		java2.setId(new Long(2));
//		java2.setName("Java");
//		java2.setLevel(3);
//		java2.setEmployee(employee2);
//		
//		System.out.println(java.toString());
//		System.out.println(java2.toString());
//		
//		System.out.println(java.equals(java2));
		// filter("amiel","","Knight");
		// System.out.println(maxMirror(new int[] { 1, 2, 3, 8, 9, 3, 2, 1 }));
		//System.out.println(maxSpan(new int[] { 2, 4, 2, 1, 8, 5, 2 }));
		 //fix34(new int[] {3, 2, 3, 2, 4, 4 });
		
	}

//	public static void filter(String name, String guild, String jobClass) {
//		String [] argNames = {"name", "guild", "jobClass"};
//		String [] args = {name, guild, jobClass};
//		//System.out.println(args[0]);
//		String queryStr = "select e from Employee e";
//		for(int i = 0; i < args.length; i++) {
//			if(args[i].length() > 0) {
//				if(!queryStr.contains("where")) {
//					queryStr = queryStr.concat(" where ");
//				}
//				else {
//					queryStr = queryStr.concat(" and ");
//				}
//				queryStr = queryStr.concat("e." + argNames[i] + " = :" + "args" + i);
//			}
//		}
//		System.out.println(queryStr);
//	}

	public static int maxMirror(int[] nums) {
		for (int window = nums.length; window > 0; window--) {
			for (int pos = 0; pos <= nums.length - window; pos++) {

				int[] segment = new int[window];
				for (int innerpos = 0; innerpos < window; innerpos++) {
					segment[innerpos] = nums[pos + innerpos];
				}

				segment = reverse(segment);
				if (contains(nums, segment)) {
					return window;
				}
			}
		}
		return 0;
	}

	private static int[] reverse(int[] nums) {
		int[] rtn = new int[nums.length];
		for (int pos = 0; pos < nums.length; pos++) {
			rtn[nums.length - pos - 1] = nums[pos];
		}
		return rtn;
	}

	private static boolean contains(int[] nums, int[] segment) {
		for (int i = 0; i <= nums.length - segment.length; i++) {
			boolean matches = true;
			for (int j = 0; j < segment.length; j++) {
				if (nums[i + j] != segment[j]) {
					matches = false;
					break;
				}
			}
			if (matches)
				return true;
		}
		return false;
	}

	public static int maxSpan(int[] nums) {
		int tryLen = nums.length;
		int maxSpan = nums.length == 0 ? 0 : 1;
		// try longest to shortest
		for (int i = tryLen; i > 0; i--) {
			int span = findSpan(nums, i);
			if (span > maxSpan)
				return span;
		}
		return maxSpan;
	}

	public static int findSpan(int[] nums, int tryLen) {
		for (int i = 0; i <= nums.length - tryLen; i++) {
			if (nums[i] == nums[i + tryLen - 1])
				return tryLen;
		}
		return 1;
	}

	public static int[] fix34(int[] nums) {
		 int curr3 = -1, curr4 = -1, i = 0, j = 0;
		  while(i < nums.length && j < nums.length) {
		  
		    if(nums[i] == 3 && curr3  < 0) {
		      curr3 = i;
		    }
		    else if (curr3 < 0 && i < nums.length)i++;
		    if(nums[j] == 4 && curr4 < 0) {
		      curr4 = j;
		    }
		    else if (curr4 < 0 && j < nums.length) j++;
		    if(curr3 >= 0 && curr4 >= 0) {
		      int temp = nums[curr3 + 1];
		      nums[curr3+1] = nums[curr4];
		      nums[curr4] = temp;
		      curr3 = -1;
		      i++;
		      curr4 = -1;
		      j++;
		    }
		    
		  }
		  
		// Did I do it correctly? Lets check.
			for(int curIdx=0;curIdx<nums.length;curIdx++){
			    System.out.print(nums[curIdx] + "  ");
			}

		  return nums;
	}
	
	public static int [] fix34v2(int [] arr) {
		int lastIdx = arr.length-1;
		int curIdx= 0 ;
		while(true){ //I know when to exit. Trust me!!!
		    // Where art thou my 3. I need to replace the index after you.
		    while(curIdx<arr.length && arr[curIdx]!=3){
		        curIdx++;
		    }
		    curIdx++;

		    // Gotcha Mr 4. You need to lead 3 safely.
		    while(lastIdx>0 && arr[lastIdx]!=4){
		        lastIdx--;
		    }

		    // As I said, trust me and I will help you exit
		    if(curIdx>=arr.length || lastIdx<=0){
		        break;
		    }

		    // Swap Time to rearrange Mr 4's after 3.
		    int temp = arr[curIdx];
		    arr[curIdx] = arr[lastIdx];
		    arr[lastIdx] = temp;
		}

		// Did I do it correctly? Lets check.
		for(curIdx=0;curIdx<arr.length;curIdx++){
		    System.out.print(arr[curIdx] + "  ");
		}
		return arr;
	}
}
