package com.kazoo.main;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;


public class BlackJackDriver {

	public static void main(String[] args) {
		Dealer dealer1;
		Player player1;
		String aHand, strInput, playerName, winLoseDraw; 
		ArrayList<String> showHand = new ArrayList<String>();
		int aceValue, numDecks, totalPerHit;
		int currPlayerScore = 0; int currDealerScore = 0; 
		int numCardsToDeal = 2; 
		double doubInput, amt, gainedOrLost;

		
		
		
		// Input and formating objectsgain
		Scanner scan = new Scanner(System.in);
		NumberFormat nft = NumberFormat.getCurrencyInstance();
		
		// get required info from player and instantiate the player obj.
		System.out.println("Please enter your playerName:");
		playerName = scan.nextLine();
		
		System.out.println("Enter the amount you have to work with");
		amt = scan.nextDouble();
		player1 = new Player(playerName, amt);
		
		System.out.println("Would you like aces to equal 1 point or 11?");
		System.out.println("Please enter number 1 or number 11");
		aceValue = scan.nextInt();
		if(aceValue != 1 && aceValue != 11 ){
			System.out.println("Invalid intInput. Try again");
			System.out.println("Please enter number 1 or number 11");
			aceValue = scan.nextInt();			
		}
		

		System.out.println("Thank you " + player1.getName());
		System.out.println("You'll have " + nft.format(player1.getDollarsToBet()) + " to bet with.");
		
		// set up the dealer 
		
		System.out.println("At our casino you can choose how many decks you'd like the dealer to use when playing Blackjack(21)");
		System.out.println("Please enter a number between 1 and 5 so we know how many decks you'd like to play with");
		numDecks = scan.nextInt();
		dealer1 = new Dealer(numDecks);
		System.out.println("Thank You " + player1.getName() + " we have informed the dealer.");
		System.out.println();
		
		System.out.println("Hello, I'm your dealer today. ");
		System.out.println("We are playing with: " + dealer1.getNumbDecks() + " decks of cards");
		System.out.println("You've elected aces to equal: " + aceValue);

		
		// Dealer takes a bet
		
		System.out.println("Before I shuffle and deal you'll need to place a bet:");
		System.out.println("So how much will it be for this round?");
		System.out.println("Remember you only have this much to work with: " + nft.format(player1.getDollarsToBet()));
		System.out.println("Enter a whole dollar amount to bet for this round.");
		doubInput = scan.nextDouble();
		
		if( doubInput >  ( player1.getDollarsToBet() - doubInput) ){
			System.out.println("Not enough in your bank");
			System.out.println("Try again!");
			System.out.println("Remember you only have this much to work with: " + 
			nft.format(player1.getDollarsRemaing() ));
			doubInput = scan.nextDouble();
			}
		
		player1.setBet(doubInput);
		System.out.println("You've decided to bet : " + nft.format(player1.getBet()));
		player1.setDollarsRemaining();
		
		// Dealer shuffles the deck and deals to player
		dealer1.shuffledeck();
		aHand = dealer1.deal(numCardsToDeal, aceValue);
		System.out.println("Your hand contains these cards: " );
		System.out.println(aHand);
		
		player1.setPlayerHand(aHand, numCardsToDeal);
		System.out.println("The number of cards in your hand is: " + player1.getPlayerHand().size());
		
		totalPerHit = dealer1.getPointsPerHand();
		player1.setPoints(totalPerHit);
		System.out.println("Your score for this hand is : " + totalPerHit);
		currPlayerScore = player1.getPoints();
		System.out.println("Your total current points are: " + currPlayerScore);
			if(currPlayerScore == 21){
				System.out.println("Blackjack!");
				System.out.println("Let's see how I do.");
			}else if(currPlayerScore > 21){
				System.out.println("Bust! - You Lose");
				// set win = false
				System.out.println("Let's see how I do");
			}
		
		
		// This is where dealer will deal him/herself a hand - One face up, the other in the hole
		System.out.println();
		System.out.println(" Now I deal my hand");
		aHand = "";
		aHand = dealer1.deal(numCardsToDeal, aceValue);
		System.out.println("This is the dealer's hand: " );
		dealer1.setDealerHand(aHand, numCardsToDeal);
		System.out.println("The number of cards in my hand: " + dealer1.getDealerHand().size());
		showHand = dealer1.getDealerHand();
		System.out.println("I do have a card in the hole but you can see this one: ");
		System.out.println(showHand.get(1));
		totalPerHit = dealer1.getPointsPerHand();
		dealer1.setPoints(totalPerHit);
		

		System.out.println();
		
		// PLayer decides to take more cards or stand
		System.out.println("Would you like to stand or take more cards? (stand or hit)");
		scan.nextLine();
		strInput = scan.nextLine();
		strInput = strInput.toLowerCase();
		
		if(strInput.equals("stand")){
			showHand = dealer1.getDealerHand();
			System.out.println("Here is the hole card along with my face card " );
				for(String card : showHand){
					System.out.println(card);
				}
			currDealerScore = dealer1.getDealerPoints();
			System.out.println("My total so far is: " + currDealerScore);
			
				if(currDealerScore == 21){
					System.out.println("You lose!");
				}else if( (currDealerScore < 21) && ( currDealerScore >= 17) ){
					// dealer score is > 17 < 21
					System.out.println("Dealer elects to stand with " + currDealerScore);
				}else{
				// dealer1 has to take on more cards	
				while(currDealerScore < 17 ){
					aHand = "";
					aHand = dealer1.deal(1, aceValue);
					dealer1.setDealerHand(aHand, 1);
					showHand = dealer1.getDealerHand();
					System.out.println("Dealer hand: ");
						for( String card : showHand){
							System.out.println(card);
						}
					totalPerHit = dealer1.getPointsPerHand();
					dealer1.setPoints(totalPerHit);
					currDealerScore = dealer1.getDealerPoints();
					System.out.println("My total so far is: " + currDealerScore);
						if(currDealerScore == 21){
							System.out.println("Blackjack - I win!");
						}else if (currDealerScore > 21){
							System.out.println("Bust! You win");
						}else if( currDealerScore > 17){
							System.out.println("Dealer stands");
						}
					}// while
				}
		}else if(strInput.equals("hit")){
				// player1 wants and gets more cards
				while( currPlayerScore < 21 && strInput.equals("hit")){
				System.out.println("You have elected to take another card");
				System.out.println("Here you go..");
				player1.setPlayerHand(aHand, 1);
				totalPerHit = dealer1.getPointsPerHand();
				player1.setPoints(totalPerHit);
				currPlayerScore = player1.getPoints();
				showHand = player1.getPlayerHand();
					for( String card : showHand){
						System.out.println(card);
					}
				
				if(currPlayerScore == 21){
					System.out.println("Blackjack You Win!");
					strInput = "quit";
				}else if(currPlayerScore > 21){
					System.out.println("Bust! - You Lose!");
					strInput = "quit";
				}else{
					System.out.println("Would you like another? ");
					System.out.println("Enter 'stand' or 'hit'");
					strInput = scan.nextLine();
				}
			}//while		
		}else{
			System.out.println("invalid input!");		
		}// outer if/else
		
		// find out who won this game
		gainedOrLost = player1.getBet() * 2;
		if( currPlayerScore == 21  && currDealerScore == 21){
			//No change - player1 is given back the amount they bet
			winLoseDraw = "draw";
			player1.resetDollarsTOBet(winLoseDraw, gainedOrLost);						
			}else if( (currPlayerScore <= 21 ) && (currDealerScore < currPlayerScore || currDealerScore > 21)){
			// player wins - winnings are added to player's dollarsToBet var 
			winLoseDraw = "win";
			player1.resetDollarsTOBet(winLoseDraw, gainedOrLost);
		}else if( (currDealerScore <= 21) && ( currPlayerScore < currDealerScore || currPlayerScore > 21 )){
			// Dealer wins -Player loses - deduct amt of bet from dollarsToBet
			winLoseDraw = "lose";
			player1.resetDollarsTOBet(winLoseDraw, gainedOrLost);			
		}else if( currPlayerScore > 21 && currDealerScore <= 21){
			// player loses - deduct amt bet from dollarsToBet
		}else{
			// player and dealer have same score - 
			winLoseDraw = "draw";
			// player1 is given back the amount they bet
			player1.resetDollarsTOBet(winLoseDraw, gainedOrLost);			
		}
		scan.close();
		} //main
	} // BlackJackDriver
