//-------------------------------------------------------
// Assignment 4
// Written by: Cerine Madi 40097761
//For COMP 248 Section S - Winter 2019
//-------------------------------------------------------
/**
 * 
 * @author madic
 *
 */
public class Player {

	private String name; // private string which stores the name of a player
	private char[][] board; // private character 2D array which is used at the player's board
	private boolean[][] turned; // private boolean 2D array which is used to know if the card in the player's board is turned
	
	public Player() { // default constructor
		this.board = new char[3][3]; // the board 2D array is initialized to be a 3x3 board
		this.turned = new boolean[3][3]; //  the turned 2D array is initialized to be a 3x3 board 
		for (int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				this.turned[i][j]=false;
			}
		} // for loops initializes all the values of the turned 2D array to false
	}
	
	public void displayBoard() { // method to display the player's board
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if(this.turned[i][j]==true) { // evaluates if the card at that place in the board is turned. If it is turned it will print out the card followed by a space.
					System.out.print(this.board[i][j] + "  ");
				}
				else { // If it is no turned it will print out "* ".
					System.out.print("*  ");
				}
			}
			System.out.print("\n"); // skipping a line once a row is finished to be displayed
		} //  loops to go through the board
	}
	
	public Player(String name){ // constructor with the name as a parameter
		this.name = name; // initializing the attribute name to the parameter name
		this.board = new char[3][3]; // initializing the board to be a 3x3 2D array
		this.turned = new boolean[3][3]; // initializing the turned board to be a 3x3x 2D array
	}

	public String getName() {return name;} // Accessor method for the attribute name


	public void setName(String name) {this.name = name;} // Mutator method for the attribute name
	
	public char cardAt(int r, int c) { // method which returns the card at a row r and a column c, with integers r and c as parameters
		return this.board[r][c];
	}
	
	public boolean flip(int r, int c) { // method which flips a card, prints out the card and returns a boolean
		if (this.turned[r][c]==true) {
			return false;
		} // if the card is turned the method will return false
		else {
			this.turned[r][c]=true;
			System.out.println(this.board[r][c]);
			return true;
		} // if the card is not turned, it will be turned, the card will be printed out and the method will return true
	}
	
	public void setTo(int r, int c, char card) { // method to set a card (which is a parameter) at a certain place in the board 2D array. the row r and column c where the card will be set are parameters
		this.board[r][c]=card;
	}
	
	public boolean isTurned(int r, int c) { // method to evaluate if a card at the row r and column c in the board 2D array is turned and returns a boolean ( r and c are parameters)
		if(this.turned[r][c]==true) { // if the card is turned the method will return true
			return true;
		}
		else { // if the card is not turned the method will return false
			return false;
		}
	}
	
	public void turn(int r, int c) {  // method to turn a card at a row r and column c (r and c are parameters) 
		if(this.turned[r][c]==true) { // if a card is turned, the card is flipped to not be turned in the turned 2D array
			this.turned[r][c]=false;
		}
		else { // if a card it not turned, the card is flipped to be turned in the turned 2D array
			this.turned[r][c]=true;
		}
	}
	
	public boolean allTurned() { // method to evaluate if all cards are turned in a board 2D array and returns a boolean
		boolean turnVerify=false; // boolean value which is updated at each card evaluated
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(this.turned[i][j]==true) { // if the card is turned the boolean value is true
					turnVerify=true;
				}
				else { // if one card is not turned the boolean value is false and the loop is broken
					turnVerify=false;
					break;
				}
			} // for loop to go through the columns of the turned 2D array
		if (turnVerify==false) // if the boolean is false the loop is broken
				break;
		} // for loop to go through the rows of the turned 2D array
		return turnVerify; // returns the boolean value
	}
	
	 public int calculatePts() { // method to calculate the number of points and which returns and integer value of the sum of the cards' points
		int points=0; // the sum of all the points is initialized to 0
	
		for(int r=0; r<3; r++) { // for loop to go through all the rows of the board 2D array
			for (int c=0; c<3; c++) { // for loop to go through all the columns of the board 2D array
		if((r==0 && this.board[r][c]==this.board[r+1][c] && this.board[r][c]==this.board[r+2][c])|| (r==1 && this.board[r][c]==this.board[r-1][c] && this.board[r][c]==this.board[r+1][c])|| (r==2 && this.board[r][c]==this.board[r-1][c] && this.board[r][c]==this.board[r-2][c]) || (c==1 && this.board[r][c-1]==this.board[r][c] && this.board[r][c]==this.board[r][c+1]) || (c==2 && this.board[r][c]==this.board[r][c-1] && this.board[r][c]==this.board[r][c-2]) || (c==0 && this.board[r][c]==this.board[r][c+1] && this.board[r][c]==this.board[r][c+2]) || (r==0 && c==0 && this.board[r][c]==this.board[r+1][c+1] && this.board[r][c]==this.board[r+2][c+2]) || (r==1 && c==1 && this.board[r-1][c-1]==this.board[r][c] && this.board[r][c]==this.board[r+1][c+1]) || (r==1 && c==1 && this.board[r][c]==this.board[r-1][c+1] && this.board[r][c]==this.board[r+1][c-1]) || (r==2 && c==2 && this.board[r][c]==this.board[r-1][c-1] && this.board[r][c]==this.board[r-2][c-2]) || (r==2 && c==0 && this.board[r][c]==this.board[r-1][c+1] && this.board[r][c]==this.board[r-2][c+2]) || (r==0 && c==2 && this.board[r][c]==this.board[r+1][c-1] && this.board[r][c]==this.board[r+2][c-2]) ) {
			points+=0;
		} // if the point at the row r and the column c is part of three identical card in a row, a diagonal or a column, the card will have 0 points 
		// the above if statement evaluates every possibility for a point (r,c) being part of an identical row, column or diagonal
		else { // if the point (r,c) is not part of an identical row, column or diagonal, the card will have its original point value stated by the rules
		switch (this.board[r][c])  // switch statement which adds to the sum of the points the number of points attributed to a type of card
		{
			case 'A':
				points+=1;
				break;
			case '2':
				points+=2;
				break;
			case '3':
				points+=3;
				break;
			case '4':
				points+=4;
				break;
			case '5':
				points+=5;
				break;	
			case '6':
				points+=6;
				break;
			case '7':
				points+=7;
				break;
			case '8':
				points+=8;
				break;
			case '9':
				points+=9;
				break;
			case 'T':
				points+=10;
				break;
			case 'J':
				points+=10;
				break;
			case 'Q':
				points+=10;
				break;
			case 'K':
				points+=0;
				break;
			case '?':
				points-=5;
				break;	
		} //switch
	} //else
	} //2nd for
	} // 1st for
		return points; // returning the sum of the points of a player's board
	}	
	 
	
	
}
