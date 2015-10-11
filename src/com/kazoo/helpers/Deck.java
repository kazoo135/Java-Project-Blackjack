package com.kazoo.helpers;

public class Deck {

	private int[] rank = { 1, 2, 3, 4, 5, 6, 7, 8, 9 , 10, 11, 12, 13 }; 
	private String[] suit = { "heart", "diamond", "club", "spade"};
	private Card[] deck;
	private int numCards;
	
	public Deck(){
		 deck = new Card[52]; 
		 numCards = 52;
	}
	
	public Deck(int numberOfCards){
		numCards = numberOfCards;
		deck = new Card[numCards];
	}
	
	public int getNumCards(){
		return numCards;
	}
	
	public int getDeckLength(){
		return deck.length;
	}
	
	public Card[]  makeDeck( ){
		int count = 0; 
		
		while(count < deck.length ) {
		
			// fill the deck
			for(int i = 0; i < suit.length; i++){
				for(int j = 0 ; j < rank.length; j++){
					deck[count] = new Card( suit[i], rank[j]);
					count++; 
					
				}
			}
		}
			return deck; 
	}// makeDeck

}// Deck
