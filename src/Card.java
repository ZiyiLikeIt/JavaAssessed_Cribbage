/*****************************************
 * 
 * @filename Card.java
 * 
 * @project JavaAssessed_Cribbage
 * 
 * @brief 	The class of cards
 * 
 * @date 	12 Oct. 2018
 * 
 * @author 	Ziyi Xiong ziyi@outlook.com.au
 *
 *****************************************/

public class Card {

// Attributes
	private char cardRank; // the rank of card
	private char cardSuit; // the suit of card
	private int faceValue; // value for 15s calculate
	private boolean isStartCard; // the flag of start card

// Constructor
	Card(String sCard) throws IllegalArgumentException {
		this.cardRank = sCard.charAt(0);
		this.cardSuit = sCard.charAt(1);
		if ("CDHS".indexOf(cardSuit) < 0)
			throw new IllegalArgumentException("Invalid Card Suit");
		this.faceValue = Character.getNumericValue(cardRank);
		if (faceValue < 2 || faceValue > 9) {
			switch (cardRank) {
				case 'A':
					this.faceValue = 1;
					break;
				case 'T':
				case 'J':
				case 'Q':
				case 'K':
					this.faceValue = 10;
					break;
				default:
					this.faceValue = 0;
					throw new IllegalArgumentException("Invalid Card Rank");
			}
		}
		this.isStartCard = false; // need to be updated using getter
	}
	
// getter and setter
	/** the flag of start card */
	public boolean isStartCard() {
		return isStartCard;
	}

	/** set the flag of start card */
	public void setStartCard(boolean isStartCard) {
		this.isStartCard = isStartCard;
	}

	public char getCardRank() {
		return cardRank;
	}

	public char getCardSuit() {
		return cardSuit;
	}

	public int getFaceValue() {
		return faceValue;
	}
}
