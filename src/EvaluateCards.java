/*****************************************
 * 
 * @filename EvaluateCards.java
 * 
 * @project JavaAssessed_Cribbage
 * 
 * @brief 	the utility class contains evaluation method
 * 
 * @date 	12 Oct. 2018
 * 
 * @author 	Ziyi Xiong ziyi@outlook.com.au
 *
 *****************************************/
import java.util.Arrays;

public final class EvaluateCards {

// Methods
	/** Start to evaluate the combination
	 * @param comb the combination need to be evaluated
	 * @return final points of this combination
	 */
	public static int evaluate(Card[] comb) {
		int points = 0;
		if (comb.length <= 1)
			return 0;
		points += check15s(comb);
		switch (comb.length) {
			case 2:
				points += checkPairs(comb);
				break;
			case 5:
				points += checkRuns(comb);
				points += checkFlushes(comb);
				points += checkOFHN(comb);
				break;
			default:
				break;
		}
		return points;
	}
	
	/** check the 15s in given cards, work for every combinations
	 * @param comb the combination need to be evaluated
	 * @return points of 15s in this combination
	 */
	private static int check15s(Card[] comb) {
		int sum = 0;
		for (Card c : comb) {
			sum += c.getFaceValue();
		}
		return (sum == 15)?2:0;
	}
	
	/** check the Pairs in given cards, only work for 2 cards
	 * @param comb the combination need to be evaluated
	 * @return points of Pairs in this combination
	 */
	private static int checkPairs(Card[] comb) {
		return (comb[0].getCardRank() == comb[1].getCardRank())?2:0;
	}
	
	/** check the Runs in given cards, only work for 5 cards
	 * @param comb the combination need to be evaluated
	 * @return points of Runs in this combination
	 */
	private static int checkRuns(Card[] comb) {
		String[] rankMatrix = {"","","",""};
		for (Card c : comb) {
			char currRank = c.getCardRank();
			rankMatrix[0] += currRank;
			rankMatrix[1] += getNextRank(currRank);
			rankMatrix[2] += getNextRank(getNextRank(currRank));
			rankMatrix[3] += getNextRank(getNextRank(getNextRank(currRank)));
		}
		
		int threeCardsRun = 0;
		int fourCardsRun = 0;
		for (char ch : rankMatrix[0].toCharArray()) {
			int chInRow2 = getOccurrenceOf(rankMatrix[1], ch);
			if (chInRow2 > 0) { // found in row 2
				int chInRow3 = getOccurrenceOf(rankMatrix[2], ch);
				if (chInRow3 > 0) { // found in row 3
					int chInRow4 = getOccurrenceOf(rankMatrix[3], ch);
					if (chInRow4 > 0) { // found in row 4, Run of 4 cards found
						if (getOccurrenceOf(rankMatrix[0], getNextRank(ch)) > 0)
							return 5; // Run of 5 cards found
						else
							fourCardsRun += (chInRow2 * chInRow3 * chInRow4);
					} else //Run of 3 cards found
						threeCardsRun += (chInRow2 * chInRow3);
				}
			}
		}
		return (fourCardsRun > 0)?(fourCardsRun*4):(threeCardsRun*3);
	}
	
	/** find out the next rank of the given rank
	 * @param cardRank current rank
	 * @return next rank
	 */
	private static char getNextRank(char cardRank) {
		if (cardRank >= 50 && cardRank <=56)
			return (char)(cardRank+1);
		char nextRank = '0';
		switch (cardRank) {
		case 'A':
			nextRank = '2';
			break;
		case '9':
			nextRank = 'T';
			break;
		case 'T':
			nextRank = 'J';
			break;
		case 'J':
			nextRank = 'Q';
			break;
		case 'Q':
			nextRank = 'K';
			break;
		default:
			break;
		}
		return nextRank;
	}
	/** count the occurences of a character in the string
	 * @param str combination need to be evaluated
	 * @param ch the character to be found
	 * @return points of Flushes in this combination
	 */
	private static int getOccurrenceOf(String str, char ch) {
		return (str.length() - str.replace(""+ch, "").length());
	}
	
	/** check the Flushes in given cards, only work for 5 cards
	 * @param comb the combination need to be evaluated
	 * @return points of Flushes in this combination
	 */
	private static int checkFlushes(Card[] comb) {
		char firstSuit = comb[0].getCardSuit();
		Card[] cardInHand = Arrays.copyOfRange(comb, 0, 4);
		for (Card c : cardInHand) {
			if (c.getCardSuit() != firstSuit) {
				return 0;
			}
		}
		if (comb[4].getCardSuit() == firstSuit)
			return 5;
		else 
			return 4;
	}
	
	/** check the "one for his nob" in given cards, only work for 5 cards
	 * @param comb the combination need to be evaluated
	 * @return points of "one for his nob" in this combination
	 */
	private static int checkOFHN(Card[] comb) {
		for (Card c : comb) {
			if((comb[4].getCardRank() != 'J') && 
					(c.getCardRank() == 'J') && 
					(c.getCardSuit() == comb[4].getCardSuit())) {
				return 1;
			}
		}
		return 0;
	}
	

	
}
