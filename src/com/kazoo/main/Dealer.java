package com.kazoo.main;
import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;
import com.kazoo.helpers.*;

public class Dealer {
		private Deck deck;
		private Card[] deck1;
		private ArrayList<String> dealerHand = new ArrayList<String>();
		
		private int numDecks;
		private int totalCards;
		private int totalDealt = 0; 
		private int dealerPoints;
		private int pointsPerHand = 0; 
	
/**
 * no args constructor that gives the dealer a deck of 52 cards
 */
public Dealer(){
	deck = new Deck();
	deck1= deck.makeDeck();
	totalCards = 52;
	dealerPoints = 0; 
}

/**
 * Allows user to define number of decks being used in the game
 * @param numberOfDecks - the number of decks that the dealer will
 * have to play with in the game of Blackjack
 */
public Dealer(int numberOfDecks){
	numDecks = numberOfDecks;
	if(numDecks > 1){
		totalCards = 52 * numDecks;
		 deck = new Deck(totalCards);
		 deck1 = deck.makeDeck();
	}
	
	dealerPoints = 0; 
}

public int getNumCards(){
	return totalCards; 
}
public int getNumbDecks(){
	return totalCards/52; 
}

// set and get dealerPoints refers to the dealers over all points 
// not points per hand which starts at line 162
public void setPoints(int points){
	dealerPoints += points;
}
public int getDealerPoints(){
	return dealerPoints;
}

public void setDealerHand(String hand, int cardsDealt){
	String[] tokens = new String[hand.length()];
	for(int i = 0; i < cardsDealt; i++){
		tokens = hand.split("\n");
		dealerHand.add(tokens[i]);
	}
}

public ArrayList<String> getDealerHand(){
	return dealerHand;
}

public void shuffledeck(){
	
	Random rand = new Random();
 
	Card[] tempdeck1 = new Card[deck1.length];
	
	for (int i = 0; i < deck1.length; i++) {
		int randIndex = rand.nextInt(51) + 1; 
		tempdeck1[i] = deck1[randIndex];
		deck1[i] = tempdeck1[i];
	}
	
}

  /**
   * This method passes cards from the deck to 
   * a players hand. It then converts the cards in the hand 
   * to a a string obj and returns it. 
   * @param numCards - number of cards to deal
   * @return - the cards, a hand, as a string
   */
public String deal(int numCards, int ace ){
	String suit = " ";
	int rank = 0; 
	String faceCard = "";
	String result = "";
	Card[] hand = new Card[numCards];
	// make the hand of cards
	for(int i = 0; i < numCards ; i++ ){

		hand[i] = deck1[totalDealt + i] ;
	}
	
	// show the hand
	for(int i = 0; i < hand.length; i++){
		suit = hand[i].getSuit();
		rank = hand[i].getRank();
		
		if(rank == 1){
			faceCard = "Ace";
			result += faceCard + " of  " + suit + "s" + "\n"; 
		}else if(rank == 11){
			faceCard = "Jack";
			hand[i].setRank(10);
			result += faceCard + " of  " + suit + "s" + "\n"; 
		}else if(rank == 12){
			faceCard = "Queen";
			hand[i].setRank(10);
			result += faceCard + " of  " + suit + "s" + "\n"; 
		}else if(rank == 13){
			faceCard = "King";
			hand[i].setRank(10);
			result += faceCard + " of  " + suit + "s" + "\n"; 
		}else{
			result += rank + " of " + suit + "s" + "\n"; 
		}
		
	} 
	
	// keep tally on number cards that have been dealt
	totalDealt += hand.length;
	
	// sum points for each individual hand
	calcPointsPerHand(hand, ace);
	
	return result; 
		
}// deal


// This does not return the total directly because
// I did not have access in main to the Card[]
private void calcPointsPerHand(Card[] cards, int aceVal){
	
	int total = 0; 
	int theRank; 

	for (int i = 0; i < cards.length; i++) {
		
		theRank = cards[i].getRank();
		
		if(aceVal == 11 && theRank == 1){
			theRank = aceVal;
		}
		
		total += theRank;
	}
	
	setPointsPerHand(total);
}// calcPointPerHand


	public void setPointsPerHand(int total){
		pointsPerHand = total;
	}
	
	public int getPointsPerHand(){
		return pointsPerHand;
	}
	

} // Dealer
