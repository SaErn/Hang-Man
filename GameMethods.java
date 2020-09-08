/* Game Methods 
 * v 1.0 
 * 2020-09-07*/

package gamePack;

import java.util.Scanner;

public class GameMethods {

	static Scanner reader = new Scanner(System.in);

	public static String 	guessWord;													/* Inmatat gissat ord */

	public static char 		guessLetter;												/* Inmatad gissad bokstav */

	public static boolean 	awaitingIn = true;											/* F�r inv�nta korrekt inmatad input */

	public static String 	possibleLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";				/* Bokst�ver som programmet till�ter */

	public static int 		wrongAnswers = 0;											/* Antal felaktiga gissningar */

	public static boolean 	notFirstRound = false;										/* False f�rsta "rundan" av spelet */
	
	
	
	/* Tar emot bokstav som input fr�n spelaren
	 * 
	 * while 	k�rs tills input har matats in korrekt
	 * 
	 * try 		testar att "plocka ut" den char som �r lagrad i f�rsta indexplatsen i inmatad string med text
	 * 			om detta misslyckas(spelaren matade in tom rad med text) k�rs catch som meddelar om detta. 
	 * 			"Utplockad" char omvandlas till versal f�r l�ttare j�mf�relse i annan metod. V�rdet i char omvandlas
	 * 			och lagras i string f�r l�ttare j�mf�relse i if-satsen som f�ljer. N�r jag gjorde det med metoderna som finns
	 * 			i Character klassen s� funkade det men syntaxen var sv�rl�st s� jag valde att anv�nda string ist�llet
	 * 
	 * if		j�mf�r stringen med inmatad bokstav med string som inneh�ller alla till�tna bokst�ver. Om till�ten bokstav
	 * 			bryts while-loopen och inmatad char returneras. Om ej till�ten bokstav ombes spelaren att g�ra nytt f�rs�k
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

	
	/* Tar emot hel string som input fr�n spelaren
	 * 
	 * Omvandlar ordet till versaler f�r enklare j�mf�relse med det korrekta ordet
	 * 
	 * Returnerar sedan ordet*/
	public static String inputWord() {
		
		System.out.print("\nEnter word to guess: ");
		
		guessWord = reader.nextLine();
		guessWord = guessWord.toUpperCase();
		
		return guessWord;
	}
	
	
	/* Skriver ut den "h�ngda gubben" 
	 * 
	 * Switchen testar hur m�nga felaktiga svar spelaren givit och skriver ut mer av h�ngningen ju mer felaktiga svar 
	 * 
	 * If satsen skriver ut ett antal radbrytningar som en enkel l�sning f�r att rensa konsollen. Detta g�rs ej f�rsta omg�ngen s� v�lkomst-text och instruktioner
	 * hamnar d�r ist�llet*/
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
					+ "\n|   -�^�-          |"
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
					+ "\n|   -�^�-          |"
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
					+ "\n|   -�^�-          |"
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
					+ "\n|   -�^�-          |"
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
					+ "\n|   -�^�-          |"
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
					+ "\n|   -�^�-          |"
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
					+ "\n|   -�^�-          |"
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
					+ "\n|   -�^�-          |"
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
					+ "\n|   -�^�-          |"
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
					+ "\n|   -�^�-          |"
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
					+ "\n|   -�^�-          |"
					+ "\n|__________________|");
			break;
		
		}
		
		
	}

}
