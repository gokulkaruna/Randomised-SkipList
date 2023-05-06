package com.ads.assignment2;

import java.util.Scanner;

public class MainRunner {
	
	public static void main(String[] args) {
		
		int choice;
		int ele;
		Scanner sc = new Scanner(System.in);
		SkipList sList = new SkipList();
		
		//input array with 20 elements
		int inputArray[] = {81, 50, 24, 30, 95, 61, 11, 37, 45, 22, 87, 53, 78, 82, 5, 15, 56, 44, 64, 49};
		
		
		
		System.out.println("--------------------------------");
		System.out.println("Randomised SkipList");
		System.out.println("--------------------------------");
		
		
		//init skiplist before starting and inserting elements from input array
		sList.initSkipList();
		
		System.out.println("Inserting the elements from the input array");
		for(int i=0 ; i<20; i++) {
			if(sList.insertElement(inputArray[i])) 
				System.out.println("Element "+inputArray[i]+" inserted successfully\n");
			else
				System.out.println("Element insertion failed\n");
		}
		
		while(true) {
			System.out.println("Enter your choice:\n1. Search Element\n2. Display Skiplist\n3. Exit\n");
			choice = sc.nextInt();
			switch(choice) {
				case 1:	System.out.println("Enter the element to be searched: ");
						ele = sc.nextInt();
						if(sList.searchElement(ele)) 
							System.out.println("Element "+ele+" found successfully\n");
						else
							System.out.println("Element "+ele+" not found\n");
						break;
						
				case 2: System.out.println("Displaying the skiplist\n");
						sList.displaySkipList();
						break;
						
				case 3: System.out.println("Exitting the loop...."); 
						System.exit(0);
				
				default: System.out.println("Please enter the correct option : ) \n");
						
			}
		}

	}

}
