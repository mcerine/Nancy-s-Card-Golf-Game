//-------------------------------------------------------
// Assignment 4
// Written by: Cerine Madi 40097761
//For COMP 248 Section S - Winter 2019
//-------------------------------------------------------
import java.util.Random; // importing Random

public class DeckAndDiscard {
 
	private char[] deck; // private character array for the deck
	private char[] discardPile; // private character array for the discard pile
	private int nextDiscard; // private integer used as an index in the discard pile array
	private int nextDeck; // private integer used as an index in the deck array
	private int cardsLeft; // private integer which keeps track of how many cards are left in the deck
	
	public DeckAndDiscard() { // default constructor
		this.deck = new char[] {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', '?', '?'};
		// initialized the deck array to 54 cards in a deck
		this.discardPile=new char[54];
		// initialized the discard pile array for it to be able to contain a maximum of the 54 cards from the deck
		this.nextDiscard=0;
		// initialized the discard pile array index to 0
		this.nextDeck=0;
		//initialized the deck array index to zero
		this.cardsLeft=54;
		// initialized the number of cards left before the start of the game to 54
		shuffle(); // used the shuffle method described later in the code
	}
	
	public char pickACard() { // method to return the next card in the deck array
		int nextD=this.nextDeck; // temporary integer to store which card has to be picked
		this.nextDeck++; // incrementing the index of the next card that is going to be picked in the deck array
		this.cardsLeft--; // decrementing the number of card left in the deck
		return deck[nextD]; // returns the card in the deck array which is next to be picked
	} 
	
	public char pickADiscard() { // method to return the next card in the discard pile to be picked
		//int nextDisc2=this.nextDiscard;
		return discardPile[(this.nextDiscard)-1]; // returns the card in the discard pile array which is next to be picked
		//return discardPile[(nextDisc2-1)];
	}
	
	public void discard(char addedDiscard) { // method to add to the discard pile array a card which a card (character) as a parameter
		//int nextDisc=this.nextDiscard;
		++this.nextDiscard; // incrementing the index of the discard pile array
		discardPile[(this.nextDiscard)-1]=addedDiscard; // adding the card parameter to the previous index in the discard pile array
	}
	
	public void shuffle(){ // method to shuffle the cards in the deck
		Random rnd= new Random(); // creating a random 
		int doShuffle=0; // integer which updates the number of shuffles
		do {
		int rndShuffle1= rnd.nextInt(54); // creates a random number between 0 and 53
		int rndShuffle2= rnd.nextInt(54); // creates a random number between 0 and 53
		char shuffled; // temporary space for the first character which is going to be switched
		shuffled=this.deck[rndShuffle1]; // stores in the temporary character the card in the deck array at the place of the first random number
		this.deck[rndShuffle1]=this.deck[rndShuffle2];  // replaces the character at the place of the first random number by the one at the place of the second random number
		this.deck[rndShuffle2]=shuffled; // replaces the character at the place of the second number by the one in the temporary character which used to be the one at the place of the first random number
		// two cards are therefore switched
		doShuffle++; // the number of shuffles is incremented
		}
		while(doShuffle<1000); // do-while loop to shuffle cards 1000 times
	}
	
	public void displayDeck(){
		for(int i=53; i>(54-cardsLeft-1); i--) {
			System.out.print(deck[i]+" "); // prints out a card in the deck at a certain place in the array with a space
		} // loop to display cards left in the deck
	}
	
	public void displayDiscardPile(){
		for(int i=0; i<nextDiscard; i++) {
			System.out.print(discardPile[i]+" "); // prints out a card in the discard pile array at a certain place followed by a space
		} // loop to display the cards in the discard pile
	}
	
	public void displayLastDiscard() {
		System.out.println(discardPile[(this.nextDiscard-1)]);
	} // method to display the last card of the discard pile array
	
	
}
