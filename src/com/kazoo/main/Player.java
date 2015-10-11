package com.kazoo.main;

import java.text.NumberFormat;
import java.util.ArrayList;


public class Player {
	
	String name;
	private double dollarsToBet;
	private double bet = 0.00;
	private double dollarsRemaining; 
	private int playerPoints;
	private ArrayList<String> playerHand = new ArrayList<String>(); 
	
	public Player(){
		dollarsToBet = 0.00;
		playerPoints = 0;
	}
	public Player(String n, double amt){
		name = n;
		dollarsToBet = amt;
		playerPoints = 0; 
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setDollarsToBet(Double dollarsToBet) {
		this.dollarsToBet = dollarsToBet;
	}
	public Double getDollarsToBet() {
		return dollarsToBet;
	}

	public void setPoints(int points){
		playerPoints += points;
	}
	
	public int getPoints(){
		return playerPoints; 
	}
	
	public void setBet(double aBet){
		bet = aBet;
	}
	
	public double getBet(){
		return bet; 
	}
	

	public void setDollarsRemaining(){
		dollarsRemaining = dollarsToBet - bet; 	 	
	}

	public double getDollarsRemaing(){
		return dollarsRemaining; 
	}
	
	// calculates gains and loses of player depending on
	// outcome of game
	public void resetDollarsTOBet(String flag, double amountReturned){
		
		switch(flag){
		case "win":
			dollarsToBet = dollarsRemaining + bet + amountReturned;
			break;
		case "lose":
			dollarsToBet = dollarsRemaining - amountReturned;
			break;
		case "draw":
			dollarsToBet = dollarsRemaining + bet; 
			break;
		default:
			System.out.println(" Invalid amounts ");
		}
		
	}
		
	public void setPlayerHand(String hand, int cardsDealt){
		String[] tokens = new String[hand.length()];
		for(int i = 0; i < cardsDealt; i++){
			tokens = hand.split("\n");
			playerHand.add(tokens[i]);
		}	
	
	}	
	
	public ArrayList<String> getPlayerHand(){
		return playerHand;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		NumberFormat nft = NumberFormat.getCurrencyInstance();
		sb.append(name + "\n");
		sb.append("You have a total to bet of " + nft.format(dollarsToBet) + "\n");
		sb.append("Your remaining amount of dollars is:  " + nft.format(dollarsRemaining)  + "\n");
		
		return sb.toString();
	}
	
	

} // Player 
