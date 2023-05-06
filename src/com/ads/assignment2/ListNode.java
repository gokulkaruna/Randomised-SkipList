package com.ads.assignment2;

public class ListNode {
	
	public int element;
	public ListNode right;  //right pointer inference
	public ListNode down; //down pointer inference
	
	//constructor to create a new node
	public ListNode(int element) {
		this.element = element;
		this.right = null;
		this.down = null;
	}

}
