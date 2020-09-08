/* Game Methods 
 * v 1.0 
 * 2020-09-07*/

package gamePack;

import java.util.Scanner;

public class GameMethods {

	static Scanner reader = new Scanner(System.in);

	public static String 	guessWord;													/* Inmatat gissat ord */

	public static char 		guessLetter;												/* Inmatad gissad bokstav */

	public static boolean 	awaitingIn = true;											/* För invänta korrekt inmatad input */

	public static String 	possibleLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";				/* Bokstäver som programmet tillåter */

	public static int 		wrongAnswers = 0;											/* Antal felaktiga gissningar */

	public static boolean 	notFirstRound = false;										/* False första "rundan" av spelet */
	
	
	
	/* Tar emot bokstav som input från spelaren
	 * 
	 * while 	körs tills input har matats in korrekt
	 * 
	 * try 		testar att "plocka ut" den char som är lagrad i första indexplatsen i inmatad string med text
	 * 			om detta misslyckas(spelaren matade in tom rad med text) körs catch som meddelar om detta. 
	 * 			"Utplockad" char omvandlas till versal för lättare jämförelse i annan metod. Värdet i char omvandlas
	 * 			och lagras i string för lättare jämförelse i if-satsen som följer. När jag gjorde det med metoderna som finns
	 * 			i Character klassen så funkade det men syntaxen var svårläst så jag valde att använda string istället
	 * 
	 * if		jämför stringen med inmatad bokstav med string som innehåller alla tillåtna bokstäver. Om tillåten bokstav
	 * 			bryts while-loopen och inmatad char returneras. Om ej tillåten bokstav ombes spelaren att göra nytt försök
	 *  */
	public static char inputLetter() {

		awaitingIn = true;
		String tempContainer;

		while (awaitingIn) {

			System.out.print("\nEnter letter to guess: ");

			try {
				guessLetter = reader.nextLine().charAt(0);
				guessLetter = Character.toUpperCase(guessLetter);
				tempContainer = String.valueOf(guessLetter);

				if (possibleLetters.contains(tempContainer)) {

					awaitingIn = false;

				} else {
					System.out.print("\nInput must be a letter between A-Z! Try again");
				}

			} catch (StringIndexOutOfBoundsException e) {

				System.out.print("\nInput can't be blank! Try again");
			}
		}
		return guessLetter;

	}

	
	/* Tar emot hel string som input från spelaren
	 * 
	 * Omvandlar ordet till versaler för enklare jämförelse med det korrekta ordet
	 * 
	 * Returnerar sedan ordet*/
	public static String inputWord() {
		
		System.out.print("\nEnter word to guess: ");
		
		guessWord = reader.nextLine();
		guessWord = guessWord.toUpperCase();
		
		return guessWord;
	}
	
	
	/* Skriver ut den "hängda gubben" 
	 * 
	 * Switchen testar hur många felaktiga svar spelaren givit och skriver ut mer av hängningen ju mer felaktiga svar 
	 * 
	 * If satsen skriver ut ett antal radbrytningar som en enkel lösning för att rensa konsollen. Detta görs ej första omgången så välkomst-text och instruktioner
	 * hamnar där istället*/
	public static void hangedManPrint() {

		if(notFirstRound) {
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}
		
		notFirstRound = true;
		
		switch(wrongAnswers) {
		case 0:
			System.out.println(" __________________"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|__________________|");
			break;
		case 1:
			System.out.println(" __________________"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|     |            |"
					+ "\n|   -¨^¨-          |"
					+ "\n|__________________|");
			break;
		case 2:
			System.out.println(" __________________"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|   -¨^¨-          |"
					+ "\n|__________________|");
			break;
		case 3:
			System.out.println(" __________________"
					+ "\n|                  |"
					+ "\n|                  |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|   -¨^¨-          |"
					+ "\n|__________________|");
			break;
		case 4:
			System.out.println(" __________________"
					+ "\n|      ______      |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|   -¨^¨-          |"
					+ "\n|__________________|");
			break;
		case 5:
			System.out.println(" __________________"
					+ "\n|      ______      |"
					+ "\n|     |      |     |"
					+ "\n|     |      O     |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|   -¨^¨-          |"
					+ "\n|__________________|");
			break;
		case 6:
			System.out.println(" __________________"
					+ "\n|      ______      |"
					+ "\n|     |      |     |"
					+ "\n|     |      O     |"
					+ "\n|     |     /      |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|   -¨^¨-          |"
					+ "\n|__________________|");
			break;
		case 7:
			System.out.println(" __________________"
					+ "\n|      ______      |"
					+ "\n|     |      |     |"
					+ "\n|     |      O     |"
					+ "\n|     |     /|     |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|   -¨^¨-          |"
					+ "\n|__________________|");
			break;
		case 8:
			System.out.println(" __________________"
					+ "\n|      ______      |"
					+ "\n|     |      |     |"
					+ "\n|     |      O     |"
					+ "\n|     |     /|\\    |"
					+ "\n|     |            |"
					+ "\n|     |            |"
					+ "\n|   -¨^¨-          |"
					+ "\n|__________________|");
			break;
		case 9:
			System.out.println(" __________________"
					+ "\n|      ______      |"
					+ "\n|     |      |     |"
					+ "\n|     |      O     |"
					+ "\n|     |     /|\\    |"
					+ "\n|     |     /      |"
					+ "\n|     |            |"
					+ "\n|   -¨^¨-          |"
					+ "\n|__________________|");
			break;
		case 10:
			System.out.println(" __________________"
					+ "\n|      ______      |"
					+ "\n|     |      |     |       GAME OVER"
					+ "\n|     |      O     |       GAME OVER"
					+ "\n|     |     /|\\    |       GAME OVER"
					+ "\n|     |     / \\    |       GAME OVER"
					+ "\n|     |            |       GAME OVER"
					+ "\n|   -¨^¨-          |"
					+ "\n|__________________|");
			
			break;
		case 11: 
			System.out.println(" __________________"
					+ "\n|      ______      |"
					+ "\n|     |      |     |       GAME OVER"
					+ "\n|     |      O     |       GAME OVER"
					+ "\n|     |     /|\\    |       GAME OVER"
					+ "\n|     |     / \\    |       GAME OVER"
					+ "\n|     |            |       GAME OVER"
					+ "\n|   -¨^¨-          |"
					+ "\n|__________________|");
			break;
		
		}
		
		
	}

}
