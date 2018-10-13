/*****************************************
 * 
 * @filename HandValue.java
 * 
 * @project JavaAssessed_Cribbage
 * 
 * @brief 	the Java project for COMP90041
 * 
 * @date 	12 Oct. 2018
 * 
 * @author 	Ziyi Xiong ziyi@outlook.com.au
 * 			Peter Schachte schachte@unimelb.edu.au
 *
 *****************************************/

public class HandValue {

// Attributes
	/** the list of cards */
	private static Card[] cards;
	/** all possible combinations of cards */
	private static Card[][] cardComb;
	
// Main Method 
	public static void main(String[] args) {
		cards = new Card[5];
		initCardList(args);
		cardComb = Combinations.combinations(cards);
		int points = 0;
		for (Card[] comb : cardComb) {
			points += EvaluateCards.evaluate(comb);
		}
		System.out.println(points);
	}
	
// Methods
	/** initialize the cards array */
	private static void initCardList(String[] args) {
		for (int i = 0; i<5; i++) {
			try {
				Card c = new Card(args[i]);
				cards[i] = c;
			} catch (IllegalArgumentException e) {
				System.out.println(e);
				System.exit(-1);
			}
		}
		cards[4].setStartCard(true);
	}
	
	
}
