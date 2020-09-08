/* Word Handler 
 * v 1.0 
 * 2020-09-07*/

package gamePack;

import java.util.ArrayList;
import java.util.Random;

class WordHandler {

	private static Random 	randomWord = new Random();

	private static String[] possibleWords = { "LION", "MONKEY", "DONKEY", "HORSE", "TOAD", "LAMA" };   		/* Möjliga ord som ska gissas */
	
	private static char[] 	wordDisplay;																	/* För att skriva ut "dolda"/rätt gissade bokstäver */
	
	private static String 	chosenWord;																		/* Ord som ska gissas */

	private static ArrayList<Character>		guessedLetters = new ArrayList<>();								/* Gissade bokstäver som ej förekommer i ordet */
	
	/* Väljer slumpvis ut ord som ska gissas 
	 * 
	 * chosenWord tilldelas ordet som slumpas fram med en random som tar antalet indexplatser i possibleWords som argument
	 * 
	 * string array possibleWords innehåller ord som kan väljas*/
	public static void setWord() {
		
		chosenWord = possibleWords[randomWord.nextInt(possibleWords.length)];
		setDisplay(chosenWord);

	}

	public static String getWord() {

		return chosenWord;
	}

	/* Initierar char array som får samma antal index som längden på chosenWord
	 * 
	 * For-loopen körs samma antal gånger som det finns indexplatser i wordDisplay. Varje loop tilldelar aktuell plats en underscore
	 * som kommer printas i spelet och symbolisera ej "upptäckt" bokstav i ordet*/
	public static void setDisplay(String chosenWord) {
		
		wordDisplay = new char[chosenWord.length()];

		for (int i = 0; i < wordDisplay.length; i++) {
			wordDisplay[i] = '_';
		}

	}

	/* Skriver ut det "dolda ordet" i konsollen 
	 * 
	 * Samma princip som metoden ovan men här skrivs istället innehållet i varje indexplats ut i konsollen*/
	public static void printDisplay() {
		
		System.out.println("\n");
		
		for (int i = 0; i < wordDisplay.length; i++) {
			System.out.print(" " + wordDisplay[i]);
		}
		
		System.out.println("\n");
	}

	/* Skriver ut alla felaktigt gissade bokstäver 
	 * 
	 * Även här samma princip som ovan förutom att här kör for-loopen lika många gånger som antalet element i arraylisten guessedLetters.
	 * Arraylist har andra metoder för att hämta storleken på den och innehållet lagrat i olika index*/
	public static void printGuessed() {
		System.out.print("\nGUESSED LETTERS: ");

		for (int i = 0; i < guessedLetters.size(); i++) {
			System.out.print(" " + guessedLetters.get(i));
		}
	}

	/* Om spelaren gissar ordet 
	 * 
	 * If testar om chosenWord ej matchar(fel gissning) spelarens gissning som skickas som argument till metoden.
	 * Om fel adderas två till variabel som håller antal felaktiga gissningar
	 * 
	 * Else(rätt svar) tilldelar bokstäverna från det korrekta ordet i char-array som används för att skriva ut 
	 * ordet i konsollen. Returnerar true(rätt gissning) eller false(fel)*/
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

	/*Om spelaren gissar en bokstav. Metod returnerar true om alla bokstäver korrekt gissade false om fortfarande finns bokstäver kvar att gissa
	 *
	 * 1.		Körs om string.indexOf returnerar annat värde en -1. -1 betyder att inget index matchar gissad bokstav, annars
	 *	   		hade positivt värde returnerats
	 *
	 *				indexForDisplay står för indexplats i wordDisplay(char-array som används för att skriva ut dolda/rätt gissade bokstäver)
	 *				tilldelas först värdet av första indexplatsen där nya gissade bokstaven förekommer i ordet
	 *
	 *				while körs tills indexFD ej längre innehåller ett positivt värde, vilket indexOf kommer tilldela om den ej hittar någon förekomst
	 *				av gissad bokstav i något index den söker igenom. wordDisplay tilldelas nya bokstaven på rätt platser.
	 *				indexFd får tillsist nytt värde från indexOf metoden. Ett string eller char argument i indexOf betyder sök efter denna char/substring
	 *				från början till slutet av stringen som kör metoden. Ett string argument tillsammans med ett int argument betyder börja sök från index med värde av int
	 *
	 *
	 * 2.		Testar om spelaren har gissat alla bokstäver i ordet. Körs lika många gånger som längden på korrekt ord. Loopen går igenom 
	 *			indexplatserna i wordDisplay och testar om det fortfarande finns någon som innehåller en underscore. Om ej finns fler underscore
	 *			är alla bokstäver korrekt gissade*/
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
