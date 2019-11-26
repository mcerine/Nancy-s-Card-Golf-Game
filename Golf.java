//-------------------------------------------------------
// Assignment 4
// Written by: Cerine Madi 40097761
//For COMP 248 Section S - Winter 2019
//-------------------------------------------------------
import java.util.Scanner; // importing the Scanner

public class Golf {
	public static void main(String[] args) {
		
		Scanner keyboard=new Scanner(System.in); // creating the scanner named keyboard
		Player[] players= new Player[2]; // creating the array of 2 players
		DeckAndDiscard deckAndDisc= new DeckAndDiscard(); // creating the deckAndDiscard object and naming it deckAndDisc
		boolean endgame = false; // boolean value that is false if the game is not finished and that will be true once the game is finished 
		String finished=""; // String that will later have the name of the player which turned over all their cards first
		int sumPts1, sumPts2; // integers that will have the sum of points of each player
		
		
		System.out.println("*****_____*****_____*****_____*****_____*****_____*****"); // Welcoming message
		System.out.println("          Welcome To Cherry's Card Golf Game");
		System.out.println("*****_____*****_____*****_____*****_____*****_____*****");
		
		System.out.println("\nTo win this game you need some luck with the cards and a bit of strategy.");
		System.out.println("Just like the outdoor game of golf, the card game knows as Golf has a goal of keeping the score as low as possible.");
		System.out.println("\nHere are the point values for each card:");
		System.out.println("Ace\t\t|1\n2 to 10 \t|Face value\nJack, Queen\t|10\nKing\t\t|0\nJoker\t\t|-5\nThree identical\t|Zeroes out the value of the card\ncards in row,\ncolumn or\ndiagonal");
		System.out.println("\nOkay.. Let's start the game! May the best golfer win!!!\n");
		
		System.out.println("What is the name of the 1st player:");
		players[0] = new Player(keyboard.next()); // creates a new player at the index 0 of the players array using the name entered by the user as a parameter for the player object
		System.out.println("What is the name of the 2nd player:");
		players[1] = new Player(keyboard.next()); // creates a new player at the index 1 of the players array using the name entered by the user as a parameter for the player object
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				players[0].setTo(i, j, deckAndDisc.pickACard());
			}
		} // for loops to give to the first player a card at each locating of the player's board
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				players[1].setTo(i, j, deckAndDisc.pickACard());
			}
		} // for loops to give to the second player a card at each location of the player's board
		
		System.out.println("\n"+players[0].getName() + " time to decide which 2 cards you want to turn over");
		System.out.println("\nWhich card do you want to flip (row col) ");
		players[0].turn(keyboard.nextInt(), keyboard.nextInt()); // a card of the first player's choice is flipped over
		System.out.println("Which card do you want to flip (row col) ");
		players[0].turn(keyboard.nextInt(), keyboard.nextInt()); // a card of the first player's choice is flipped over
		
		System.out.println("\n"+players[1].getName() + " time to decide which 2 cards you want to turn over");
		System.out.println("\nWhich card do you want to flip (row col) ");
		players[1].turn(keyboard.nextInt(), keyboard.nextInt());// a card of the second player's choice is flipped over
		System.out.println("Which card do you want to flip (row col) ");
		players[1].turn(keyboard.nextInt(), keyboard.nextInt());// a card of the second player's choice is flipped over
		
		deckAndDisc.discard(deckAndDisc.pickACard()); // adds a card from the deck to the discard pile 
		
		// BEGINNING OF TURNS
		do {
		System.out.print("\nDiscard pile has ");
		deckAndDisc.displayLastDiscard(); // prints out what the last card in the discard pile is
		System.out.println("");
		
		System.out.println(players[0].getName() + "'s turn:\n-------------------------");
		System.out.println("Here is your board.");
	
		players[0].displayBoard(); // method to display the board
		System.out.println("Do you want the card on the discard pile(0) or a new card(1) ");
		int choiceTakeCard = keyboard.nextInt(); // integer to save and evaluate the choice if the player wants to pick a new card or to take the last one in the discard pile
		
		if(choiceTakeCard==0) { // if the player wants to take the last card on the discard pile
			System.out.println(players[0].getName() + ", do you want to replace a flipped card(0) or flip a new card(1) ");
			int choiceDisc = keyboard.nextInt(); // integer to save and evaluate the choice if the player wants to replace a flipped card or flip a new card
				if(choiceDisc==0) { // if the player wants to replace a flipped card
					int flipped1, flipped2; // integers to save which card (row and column) the player wants to replace
					boolean isFlipped=true; // boolean to evaluate if the card  is already flipped
					do {
					System.out.println("Which flipped card do you want to replace (row col)?");
					flipped1=keyboard.nextInt(); // row of the card the player wants to replace
					flipped2=keyboard.nextInt(); // column of the card the player wants to replace
						if (players[0].isTurned(flipped1, flipped2)==false) { // if the card is not already flipped the boolean flipped becomes false and an error message is printed out
							isFlipped=false;
							System.out.println("This card is not flipped.");
						}
					}
					while (isFlipped==false); // do-while loop to keep asking which flipped card the player wants to replace if the card is not already flipped
					char discarding = players[0].cardAt(flipped1, flipped2); // temporary character that saves the card in the board which is going to be discarded
					players[0].setTo(flipped1, flipped2, deckAndDisc.pickADiscard() ); // sets the character of the place of the card which is going to be discarded to the last discarded card
					deckAndDisc.discard(discarding); // discards the card which was in the board
				}
				else if(choiceDisc==1) { // if the player wants to flip a new card
					int flip1, flip2; //integers to save which new card (row and column) the player wants to flip
					boolean isFlipped=false; // boolean to evaluate if the new card is already flipped or not
					do {
					System.out.println("Which non-flipped card do you want to flip (row col)?");
					flip1=keyboard.nextInt(); // row of the card the player wants to flip
					flip2=keyboard.nextInt(); // column of the card the player wants to flip
						if(players[0].isTurned(flip1, flip2)==false) { // if the card is not flipped the boolean value is kept false and the card is turned
							isFlipped=false;
							players[0].turn(flip1, flip2);
						}
						else { // if the card is already flipped the boolean value is updated to true and an error message is printed out
							System.out.println("Card is already flipped");
							isFlipped=true;
						}
					}
					while (isFlipped == true); // do-while loop to keep asking which new card the player wants to flip is the card selected is already flipped
					char discarding = players[0].cardAt(flip1, flip2); // temporary character for the card which is going to be discarded
					players[0].setTo(flip1, flip2, deckAndDisc.pickADiscard()); // setting the card of the place that will be discarded to the last card on the discard pile
					deckAndDisc.discard(discarding); // discards the card which was in the board
				}
		} // if choiceTakeCard=0
		else if(choiceTakeCard==1) { // if the player wants to take a new card from the deck
			char playingCard = deckAndDisc.pickACard(); // picks a new card from the deck and saves it in a character value
			System.out.println("The card you are playing is " + playingCard); // prints out the card that is being played
			System.out.println("Replace a card(0) or toss it(1)?");
			int choiceNewCard =  keyboard.nextInt(); // integer to save and evaluate if the player wants to replace a card or toss the card which was picked
			if (choiceNewCard==0) { // if the player wants to replace a card
				System.out.println(players[0].getName() + ", do you want to replace a flipped card(0) or flip a new card(1)");
				int choiceReplacing = keyboard.nextInt(); // integer which saves and evaluates if the player wants to replace a flipped card or to flip a new card
				if (choiceReplacing==0) { // if the player wants to replace a flipped card
					int flipped1, flipped2; // integers to save which card (row and column) the player wants to replace
					boolean isFlipped=true; // boolean to evaluate if the card  is already flipped
					do {
					System.out.println("Which flipped card do you want to replace (row col)?");
					flipped1=keyboard.nextInt(); // row of the card the player wants to replace
					flipped2=keyboard.nextInt(); // column of the card the player wants to replace
					if (players[0].isTurned(flipped1, flipped2)==false) { // if the card is not already flipped the boolean flipped becomes false and an error message is printed out
						isFlipped=false;
						System.out.println("This card is not flipped.");
					}
					}
					while (isFlipped==false); // do-while loop to keep asking which flipped card the player wants to replace if the card is not already flipped
					char replacing = players[0].cardAt(flipped1, flipped2); // temporary character that saves the card in the board which is going to be discarded
					players[0].setTo(flipped1, flipped2, playingCard);  // sets the character of the place of the card which is going to be discarded to the last discarded card
					deckAndDisc.discard(replacing); // discards the card which was in the board
				}
				else if (choiceReplacing==1) { // if the player wants to flip a new card
					int flip1, flip2; //integers to save which new card (row and column) the player wants to flip
					boolean isFlipped=false; // boolean to evaluate if the new card is already flipped or not
					do {
					System.out.println("Which non-flipped card do you want to flip (row col)?");
					flip1=keyboard.nextInt(); // row of the card the player wants to flip
					flip2=keyboard.nextInt(); // column of the card the player wants to flip
						if(players[0].isTurned(flip1, flip2)==false) { // if the card is not flipped the boolean value is kept false and the card is turned
							isFlipped=false;
							players[0].turn(flip1, flip2);
						}
						else { // if the card is already flipped the boolean value is updated to true and an error message is printed out
							isFlipped=true;
							System.out.println("Card is already flipped");
						}
					}
					while (isFlipped== true); // do-while loop to keep asking which new card the player wants to flip is the card selected is already flipped				

					players[0].displayBoard(); // method to display the player's board
					System.out.println("Replace this card(0) or toss it(1)?");
					int choiceFlipping = keyboard.nextInt(); // integer to save and evaluate the player's choice if they want to replace the card or toss it
					if(choiceFlipping==0) { // if the player wants to replace the card
					char flipped = players[0].cardAt(flip1, flip2); // temporary character that saves the card in the board which is going to be discarded
					players[0].setTo(flip1, flip2, playingCard); // replacing the place in the board by the card from the deck
					deckAndDisc.discard(flipped); // discarding the card which was in the temporary character
					}
					else if(choiceFlipping==1) { // if the player wants to toss the card
						deckAndDisc.discard(playingCard); // adding the card to the discard pile
					}
				} // if choiceReplacing=1
			} // if choiceNewCard=0
			else if (choiceNewCard==1) { // if the player wants to toss the card
				deckAndDisc.discard(playingCard); // adding the card to the discard Pile
			} // if choiceNewCard=1
			} // if choiceTakeCard=1
		if(players[0].allTurned() == true) { // evaluates if all the cards in the player's board are turned
			finished = players[0].getName(); // the name of the player which turned all their cards first is saved
			endgame = true; // the boolean for ending the game is set to true
			break; // the loop to keep playing the turns is broken
		}
		 // END PLAYER 1
		System.out.print("\nDiscard pile has ");
		deckAndDisc.displayLastDiscard();  // displays the last card in the discard pile
		System.out.println("");
		
		System.out.println(players[1].getName() + "'s turn:\n-------------------------");
		System.out.println("Here is your board.");
	
		players[1].displayBoard(); // method to display the player's board
		System.out.println("Do you want the card on the discard pile(0) or a new card(1) ");
		int choiceTakeCard2 = keyboard.nextInt(); // integer to save and evaluate the choice if the player wants to pick a new card or to take the last one in the discard pile
		
		if(choiceTakeCard2==0) { // if the player wants to take the last card on the discard pile
			System.out.println(players[1].getName() + ", do you want to replace a flipped card(0) or flip a new card(1) ");
			int choiceDisc = keyboard.nextInt();  // integer to save and evaluate the choice if the player wants to replace a flipped card or flip a new card
				if(choiceDisc==0) { // if the player wants to replace a flipped card
					int flipped1, flipped2; // integers to save which card (row and column) the player wants to replace
					boolean isFlipped=true; // boolean to evaluate if the card  is already flipped
					do {
					System.out.println("Which flipped card do you want to replace (row col)?");
					flipped1=keyboard.nextInt(); // row of the card the player wants to replace
					flipped2=keyboard.nextInt(); // column of the card the player wants to replace
					if (players[1].isTurned(flipped1, flipped2)==false) { // if the card is not already flipped the boolean flipped becomes false and an error message is printed out
						isFlipped=false;
						System.out.println("This card is not flipped.");
					}
					}
					while (isFlipped==false); // do-while loop to keep asking which flipped card the player wants to replace if the card is not already flipped
					char discarding = players[1].cardAt(flipped1, flipped2); // temporary character that saves the card in the board which is going to be discarded
					players[1].setTo(flipped1, flipped2, deckAndDisc.pickADiscard()); // sets the character of the place of the card which is going to be discarded to the last discarded card
					deckAndDisc.discard(discarding); // discards the card which was in the board
				}
				else if(choiceDisc==1) { // if the player wants to flip a new card
					int flip1, flip2; //integers to save which new card (row and column) the player wants to flip
					boolean isFlipped=false; // boolean to evaluate if the new card is already flipped or not
					do {
					System.out.println("Which non-flipped card do you want to flip (row col)?");
					flip1=keyboard.nextInt();  // row of the card the player wants to flip
					flip2=keyboard.nextInt();  // column of the card the player wants to flip
						if(players[1].isTurned(flip1, flip2)==false) {  // if the card is not flipped the boolean value is kept false and the card is turned
							isFlipped=false;
							players[1].turn(flip1, flip2);
						}
						else { // if the card is already flipped the boolean value is updated to true and an error message is printed out
							isFlipped=true;
							System.out.println("Card is already flipped");
						}
					}
					while (isFlipped== true); // do-while loop to keep asking which new card the player wants to flip is the card selected is already flipped
					char discarding = players[1].cardAt(flip1, flip2); // temporary character for the card which is going to be discarded
					players[1].setTo(flip1, flip2, deckAndDisc.pickADiscard()); // setting the card of the place that will be discarded to the last card on the discard pile
					deckAndDisc.discard(discarding); // discards the card which was in the board
				}
		} // if choiceTakeCard=0
		else if(choiceTakeCard2==1) { // if the player wants to take a new card from the deck
			char playingCard = deckAndDisc.pickACard(); // picks a new card from the deck and saves it in a character value
			System.out.println("The card you are playing is " + playingCard);  // prints out the card that is being played
			System.out.println("Replace a card(0) or toss it(1)?");
			int choiceNewCard =  keyboard.nextInt(); // integer to save and evaluate if the player wants to replace a card or toss the card which was picked
			if (choiceNewCard==0) { // if the player wants to replace a card
				System.out.println(players[1].getName() + ", do you want to replace a flipped card(0) or flip a new card(1)");
				int choiceReplacing = keyboard.nextInt(); // integer which saves and evaluates if the player wants to replace a flipped card or to flip a new card
				if (choiceReplacing==0) { // if the player wants to replace a flipped card
					int flipped1, flipped2; // integers to save which card (row and column) the player wants to replace
					boolean isFlipped=true; // boolean to evaluate if the card  is already flipped
					do {
					System.out.println("Which flipped card do you want to replace (row col)?");
					flipped1=keyboard.nextInt(); // row of the card the player wants to replace
					flipped2=keyboard.nextInt(); // column of the card the player wants to replace
						if (players[1].isTurned(flipped1, flipped2)==false) { // if the card is not already flipped the boolean flipped becomes false and an error message is printed out
							isFlipped=false;
							System.out.println("This card is not flipped.");
						}
					}
					while (isFlipped==false); // do-while loop to keep asking which flipped card the player wants to replace if the card is not already flipped
					char replacing = players[1].cardAt(flipped1, flipped2); // temporary character that saves the card in the board which is going to be discarded
					players[1].setTo(flipped1, flipped2, playingCard); // sets the character of the place of the card which is going to be discarded to the last discarded card
					deckAndDisc.discard(replacing); // discards the card which was in the board
				}
				else if (choiceReplacing==1) { // if the player wants to flip a new card
					int flip1, flip2; //integers to save which new card (row and column) the player wants to flip
					boolean isFlipped=false; // boolean to evaluate if the new card is already flipped or not
					do {
					System.out.println("Which non-flipped card do you want to flip (row col)?");
					flip1=keyboard.nextInt(); // row of the card the player wants to flip
					flip2=keyboard.nextInt(); // column of the card the player wants to flip
						if(players[1].isTurned(flip1, flip2)==false) { // if the card is not flipped the boolean value is kept false and the card is turned
							isFlipped=false;
							players[1].turn(flip1, flip2);
						}
						else { // if the card is already flipped the boolean value is updated to true and an error message is printed out
							isFlipped=true;
							System.out.println("Card is already flipped");
						}
					}
					while (isFlipped== true); // do-while loop to keep asking which new card the player wants to flip is the card selected is already flipped			

					players[1].displayBoard(); // method to display the player's board
					System.out.println("Replace this card(0) or toss it(1)?");
					int choiceFlipping = keyboard.nextInt(); // integer to save and evaluate the player's choice if they want to replace the card or toss it
					if(choiceFlipping==0) { // if the player wants to replace the card
					char flipped = players[1].cardAt(flip1, flip2);  // temporary character that saves the card in the board which is going to be discarded
					players[1].setTo(flip1, flip2, playingCard); // replacing the place in the board by the card from the deck
					deckAndDisc.discard(flipped); // discarding the card which was in the temporary character
					}
					else if(choiceFlipping==1) { // if the player wants to toss the card
						deckAndDisc.discard(playingCard); // adding the card to the discard pile
					}
				} // if choiceReplacing=1
			} // if choiceNewCard=0
			else if (choiceNewCard==1) { // if the player wants to toss the card
				deckAndDisc.discard(playingCard); // adding the card to the discard Pile
			} // if choiceNewCard=1
			} // if choiceTakeCard=1
		if(players[1].allTurned() == true) { // evaluates if all the cards in the player's board are turned
			finished = players[1].getName(); // the name of the player which turned all their cards first is saved
			endgame = true; // the boolean for ending the game is set to true
			break; // the loop to keep playing the turns is broken
		}
		// END PLAYER 2
		}
		while(endgame==false); // do-while loop which keeps going for players' turns as long as no player has turned all their cards over
		// END OF TURNS
		
		System.out.println("\n" + finished + " has turned over all cards."); // prints out which player has turned all their cards first
		System.out.println("Time to calculate points! Here are your boards with all cards turned over");
		
		System.out.println(players[0].getName() + "\t\t\t" + players[1].getName());
		System.out.println("----------------------------------------------------------------");
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
					System.out.print(players[0].cardAt(i, j) + "  ");
			}
			System.out.print("                ");
			for (int k=0; k<3; k++) {
				System.out.print(players[1].cardAt(i, k) + "  ");
			}
			System.out.print("\n");
		} // for loops to print out the players' boards with all the cards turned over. Prints out the first player's and the second player's rows on the same line
		
		sumPts1=players[0].calculatePts(); // calculating the first player's points
		sumPts2=players[1].calculatePts(); // calculating the second player's points
		
		System.out.println("Final results:");
		System.out.println(players[0].getName() + " scored " + sumPts1); // printing out the first player's points
		System.out.println(players[1].getName() + " scored " + sumPts2); // printing out the second player's points
		
		if (sumPts1>sumPts2) {
			System.out.println("CONGRATULATIONS!!!! The winner is " + players[1].getName()); // prints out a message if the winner is the first player
		}
		else if(sumPts1<sumPts2) {
			System.out.println("CONGRATULATIONS!!!! The winner is " + players[0].getName()); // prints out a message if the winner is the second player
		}
		else if(sumPts1==sumPts2) {
			System.out.println("The result is a tie!!!"); // prints out a message if it is a tie
		}
		
		System.out.println("*****_____*****_____*****_____*****_____*****_____*****"); // Ending message
		System.out.println("     Thank You For Playing Cherry's Card Golf Game");
		System.out.println("*****_____*****_____*****_____*****_____*****_____*****");
		
		
	keyboard.close();	// closing the scanner
	}
}
