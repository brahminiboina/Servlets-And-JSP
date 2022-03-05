package com.wicore.testcases;

import java.util.Scanner;

public class TestCase1 {
	public static void main(String[] args) {
		int[] arr;
		int num,temp;
		int count =0; 
		int r=0;
		int k=3;
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter the size of the array:");
		num = scn.nextInt();
		arr = new int[num];
		int len=arr.length;
		System.out.println("Enter "+num+" elements");
		for(int i=0;i<len;i++) {
			//arr[i] = scn.nextInt();
			temp = arr[i];
			while (arr[i] !=1) {
				if (arr[i]%2==0) { 
				r=arr[i]/2;
				  arr[i] = r;
				  count++;
			}else {
				arr[i] = arr[i] + k ;
				if(arr[i] != temp ) {
					while(arr[i] !=1) {
						if (arr[i]%2==0 && arr[i] !=1) {
						  r=arr[i]/2;
						  arr[i] = r;
						  count++;
					  
						}
					}
				}else {
					arr[i] = arr[i] + (k+2);
				}
			}
			}
			
		}
		System.out.println("count: "+count);
		
	}

}
