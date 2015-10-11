package com.kazoo.helpers;

public class Card {
	
	private int rank;
	private String suit;
	
	public Card(String s, int r){
		suit = s;
		rank = r; 
	}
	
	public String getSuit(){
		return suit;
	}
	
	public int getRank(){
		return rank;
	}
	
	public void setSuit(String s){
		suit = s;
		
	}
	
	public void setRank(int r){
		rank = r;
		
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("The rank is: " + rank + "\n");
		sb.append("The suit is : " + suit);
		
		return sb.toString();
		
	}


} //Card
