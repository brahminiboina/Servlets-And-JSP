package com.wicore.testcases;

import java.util.Scanner;

public class TestCase2 {
	public static void main(String[] args) {
		int[] arr ; 
		int num = 0,temp,r;
		int count = 0;
		int k= 3;
		int count1 =0;

        int smallest1=0;
        int arrayodd = 1;
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the size of the array:");	
		num = scn.nextInt();//arr = new int[num];
		arr = new int[num];
		//int len=arr.length;
		//int smallest = arr[0];
		int arraySum = 0;
		System.out.println("Enter "+num+" elements");
		 for (int i = 0; i < arr.length ; i ++)
	        {
	            /* If current element is smaller than
	               update smallest */
					arr[i] = scn.nextInt();
			
	            if (arr[i] % 2==0) {
	            	
	                smallest1 = arr[i];
	                while(smallest1 > 1) {
	                	
	                	smallest1=smallest1/2;
	                	if(smallest1%2 != 0 && smallest1!=1) {
	                		
	                		smallest1=arrayodd+2;
	                		smallest1=smallest1/2;
	                		count++;
	                	}
	                	count++;
	                }
	            }else {
	            	
	            	arrayodd=arrayodd+2;
	            	smallest1 = arr[i]+arrayodd;
	                while(smallest1>1) {
	                	
	                	smallest1=smallest1/2;
	                	count++;
	                }
	                arrayodd=1;
	            }
	            /*find array sum */
	            //arraySum += arr[i];
	        }
	        //arraySum=count1+count;
	        /* Print min operation required */
	        System.out.println("Minimum Operation = " +
	        		count );
	    }
}

