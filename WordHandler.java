/* Word Handler 
 * v 1.0 
 * 2020-09-07*/

package gamePack;

import java.util.ArrayList;
import java.util.Random;

class WordHandler {

	private static Random 	randomWord = new Random();

	private static String[] possibleWords = { "LION", "MONKEY", "DONKEY", "HORSE", "TOAD", "LAMA" };   		/* M�jliga ord som ska gissas */
	
	private static char[] 	wordDisplay;																	/* F�r att skriva ut "dolda"/r�tt gissade bokst�ver */
	
	private static String 	chosenWord;																		/* Ord som ska gissas */

	private static ArrayList<Character>		guessedLetters = new ArrayList<>();								/* Gissade bokst�ver som ej f�rekommer i ordet */
	
	/* V�ljer slumpvis ut ord som ska gissas 
	 * 
	 * chosenWord tilldelas ordet som slumpas fram med en random som tar antalet indexplatser i possibleWords som argument
	 * 
	 * string array possibleWords inneh�ller ord som kan v�ljas*/
	public static void setWord() {
		
		chosenWord = possibleWords[randomWord.nextInt(possibleWords.length)];
		setDisplay(chosenWord);

	}

	public static String getWord() {

		return chosenWord;
	}

	/* Initierar char array som f�r samma antal index som l�ngden p� chosenWord
	 * 
	 * For-loopen k�rs samma antal g�nger som det finns indexplatser i wordDisplay. Varje loop tilldelar aktuell plats en underscore
	 * som kommer printas i spelet och symbolisera ej "uppt�ckt" bokstav i ordet*/
	public static void setDisplay(String chosenWord) {
		
		wordDisplay = new char[chosenWord.length()];

		for (int i = 0; i < wordDisplay.length; i++) {
			wordDisplay[i] = '_';
		}

	}

	/* Skriver ut det "dolda ordet" i konsollen 
	 * 
	 * Samma princip som metoden ovan men h�r skrivs ist�llet inneh�llet i varje indexplats ut i konsollen*/
	public static void printDisplay() {
		
		System.out.println("\n");
		
		for (int i = 0; i < wordDisplay.length; i++) {
			System.out.print(" " + wordDisplay[i]);
		}
		
		System.out.println("\n");
	}

	/* Skriver ut alla felaktigt gissade bokst�ver 
	 * 
	 * �ven h�r samma princip som ovan f�rutom att h�r k�r for-loopen lika m�nga g�nger som antalet element i arraylisten guessedLetters.
	 * Arraylist har andra metoder f�r att h�mta storleken p� den och inneh�llet lagrat i olika index*/
	public static void printGuessed() {
		System.out.print("\nGUESSED LETTERS: ");

		for (int i = 0; i < guessedLetters.size(); i++) {
			System.out.print(" " + guessedLetters.get(i));
		}
	}

	/* Om spelaren gissar ordet 
	 * 
	 * If testar om chosenWord ej matchar(fel gissning) spelarens gissning som skickas som argument till metoden.
	 * Om fel adderas tv� till variabel som h�ller antal felaktiga gissningar
	 * 
	 * Else(r�tt svar) tilldelar bokst�verna fr�n det korrekta ordet i char-array som anv�nds f�r att skriva ut 
	 * ordet i konsollen. Returnerar true(r�tt gissning) eller false(fel)*/
	public boolean guessWord(String playerWord) {

		if(!chosenWord.contentEquals(playerWord)) {
			GameMethods.wrongAnswers += 2;
		}else {
			for (int i = 0; i < wordDisplay.length; i++) {
				wordDisplay[i] = chosenWord.charAt(i);
			}
		}
		return chosenWord.contentEquals(playerWord);

	}

	/*Om spelaren gissar en bokstav. Metod returnerar true om alla bokst�ver korrekt gissade false om fortfarande finns bokst�ver kvar att gissa
	 *
	 * 1.		K�rs om string.indexOf returnerar annat v�rde en -1. -1 betyder att inget index matchar gissad bokstav, annars
	 *	   		hade positivt v�rde returnerats
	 *
	 *				indexForDisplay st�r f�r indexplats i wordDisplay(char-array som anv�nds f�r att skriva ut dolda/r�tt gissade bokst�ver)
	 *				tilldelas f�rst v�rdet av f�rsta indexplatsen d�r nya gissade bokstaven f�rekommer i ordet
	 *
	 *				while k�rs tills indexFD ej l�ngre inneh�ller ett positivt v�rde, vilket indexOf kommer tilldela om den ej hittar n�gon f�rekomst
	 *				av gissad bokstav i n�got index den s�ker igenom. wordDisplay tilldelas nya bokstaven p� r�tt platser.
	 *				indexFd f�r tillsist nytt v�rde fr�n indexOf metoden. Ett string eller char argument i indexOf betyder s�k efter denna char/substring
	 *				fr�n b�rjan till slutet av stringen som k�r metoden. Ett string argument tillsammans med ett int argument betyder b�rja s�k fr�n index med v�rde av int
	 *
	 *
	 * 2.		Testar om spelaren har gissat alla bokst�ver i ordet. K�rs lika m�nga g�nger som l�ngden p� korrekt ord. Loopen g�r igenom 
	 *			indexplatserna i wordDisplay och testar om det fortfarande finns n�gon som inneh�ller en underscore. Om ej finns fler underscore
	 *			�r alla bokst�ver korrekt gissade*/
	public boolean guessLetter(char playerLetter) {
		
			if (!guessedLetters.contains(playerLetter)) { /* Om ny gissad bokstav ej redan finns bland gissade */

				if (chosenWord.indexOf(playerLetter) != -1) { /* 1. Se ovan */

					int indexForDisplay = chosenWord.indexOf(playerLetter);

					while (indexForDisplay >= 0) {
						wordDisplay[indexForDisplay] = playerLetter;
						indexForDisplay = chosenWord.indexOf(playerLetter, indexForDisplay + 1);
					}
				} else { /* Gissad finns ej i ordet. Antal fel svar + 1 */
					GameMethods.wrongAnswers++;
					guessedLetters.add(playerLetter);
				}

			} else {
				System.out.println("\nLetter " + playerLetter + " have already been guessed!\n"
						+ "Press any key to continue");
				try
		        {
		            System.in.read();
		        }  
		        catch(Exception e)
		        {}  
			} 
		
		for (int i = 0; i < chosenWord.length(); i++) {								/* 2. Se ovan */

			if (wordDisplay[i] == '_') { 
				break;

			} else if (wordDisplay[i] != '_' && i == chosenWord.length() - 1) {  
				return true;

			} else { 

			}
		}

		return false;

	}
}
