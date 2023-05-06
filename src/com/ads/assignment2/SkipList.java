package com.ads.assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkipList {
	
	//list of the heads of all the levels
	List<ListNode> headList = new ArrayList<>();

	
	/*
	 * Used to initialize the skip list by creating level zero 
	 * min_value and max_value is more like -infinity and +infinity
	 */
	public void initSkipList() {
		ListNode leftNode = createNewNode(Integer.MIN_VALUE);
		headList.add(leftNode);
	}
	
	
	
	/*
	 * Generates 0 or 1 randomly to simulate the coin flip for the cascading to the next levels
	 */
	private int randomizer() {
		Random rand = new Random();
		return rand.nextInt(2);
	}
	
	
	
	/*
	 * Returns the number of levels that are already available to cascade upwards
	 * more like the count of levels
	 */
	private int maxLevels() {
		int count = -1;
		for(ListNode i:headList) {
			count++;
		}
		return count;
	}
	
	
	
	/*
	 * Used to create a new node if the element is given, it uses the constructor
	 */
	private ListNode createNewNode(int ele) {
		ListNode newNode = new ListNode(ele);
		return newNode;
	}
	
	
	
	/*
	 * This is same as creating level zero
	 * But the level 1's down pointer must point to level 0
	 * for which the level 0's ptr is passed as argument to create a upper level
	 */
	private void createNewEmptyLevel(ListNode ptr) {
		ListNode leftNode = createNewNode(Integer.MIN_VALUE);
		ListNode rightNode = createNewNode(Integer.MAX_VALUE);
		leftNode.down = ptr;
		leftNode.right = rightNode;
		headList.add(leftNode);
	}
	
	
	
	/*
	 * This method is to insert the element to the upper levels from 0 onwards 
	 * level is passed as a parameter to do this
	 * returns the inserted node after insertion
	 */
	private ListNode insertElementIntoNthLevel(int ele, int level) {
			ListNode ptr = headList.get(level);
			ListNode newNode = createNewNode(ele);				
			//first insertion
			if(ptr.right == null) {
				ptr.right = newNode;
			}
			else {
				//insertion in ascending order
				ListNode p = ptr;
				ListNode q = ptr;
						
				while(q!=null) {
					if(p == null || p.element > ele) {
						q.right = newNode;
						newNode.right = p;
						break;
					}else if(p.element < ele) {
						q = p;
						p = p.right;
					}
				}
			}
			return newNode;
		}

	
	

	/*
	 * This is used after inserting into the zeroth level
	 * It is used to cascade the element to upper levels based on the randomizer's value
	 * More of a coin toss
	 */
	private void cascadeNLevels(ListNode zeroLvlNode) {
		int level = 1;
		while(randomizer()!=0) {
			ListNode newNode = insertElementIntoNthLevel(zeroLvlNode.element,level);
			newNode.down = zeroLvlNode;
			zeroLvlNode = newNode;
			int prevLevel = level;
			level++;
			if(level>maxLevels()) {
				createNewEmptyLevel(headList.get(prevLevel));
			}
		}	
	}

	
	//-------------------------------------------------------
	//---------------Implementation-------------------------
	//-------------------------------------------------------
	
	
	/*
	 * Insert method
	 * Used to insert the element at the level 0 in ascending order
	 * And then a function call is made to cascade based on randomization
	 */
	public boolean insertElement(int ele) {
		
		ListNode ptr = headList.get(0);
		ListNode newNode = createNewNode(ele);
		//insertion at 0th level
		//first insertion
		if(ptr.right == null) {
			ptr.right = newNode;
			createNewEmptyLevel(ptr);
		}
		else {
			//insertion in ascending order
			ListNode p = ptr;
			ListNode q = ptr;
					
			while(q!=null) {
				if(p == null || p.element > ele) {
					q.right = newNode;
					newNode.right = p;
					break;
				}else if(p.element < ele) {
					q = p;
					p = p.right;
				}
			}
		}	
		cascadeNLevels(newNode);
		return true;
	}
	
	
	/*
	 * search the item from the skip list from the max level downwards to level 0
	 * same as the algorithm in the syllabus
	 */

	public boolean searchElement(int ele) {
		ListNode ptr = headList.get(maxLevels());
		
		while(ptr != null) {
			if(ptr.right.element == ele) {
				return true;
			}
			else if(ptr.right.element > ele ) {
				ptr = ptr.down;
			}
			else if(ptr.right.element < ele ) {
				ptr = ptr.right;
			}
		}
		return false;
		
	}
	
	
	//display the skiplist using the header array to traverse all the levels
	public void displaySkipList() {
		for(int i = maxLevels(); i >= 0 ; i--) {
			ListNode ptr = headList.get(i);
			System.out.print("LEVEL["+i+"] -Infinity--> ");
			ptr = ptr.right;
			while(ptr.right!=null) {
				System.out.print(ptr.element+" --> ");
				ptr = ptr.right;
			}
			System.out.print("+Infinity");
			System.out.println();
		}
	
	}
}
