/* Game Launcher 
 * v 1.0 
 * 2020-09-07*/

package gamePack;

import java.util.Scanner;

public class GameLauncher {

	public static boolean 		quitGame = false;									/* Om spelet ska avslutas */

	public static boolean		show_Word = false;									/* Om ordet ska visas för spelaren eller ej */

	public static Scanner		reader = new Scanner(System.in);

	public static WordHandler 	word = new WordHandler();							/* Objekt ur WordHandler klass. Innehåller ord att gissa och sköter ordrelaterade saker */

	
	
	
	public static void main(String[] args) {

		word.setWord();																/* Ordet som ska gissas väljs ut */
		
		welcomePrint();																/* Välkomst-text */

		
		while (!quitGame) {															/* Sant om avslutas/omgången vunnen/förlorad */
			
			GameMethods.hangedManPrint();											/* Bild på hängd gubbe */
			
			if (show_Word) {														/* Om spelaren väljer att ordet ska visas */
				System.out.println("\nThe word is: " + word.getWord());
			}
			
			attemptsPrint();														/* antal försök kvar */
			
			word.printGuessed();													/* felaktiga gissningar */
			
			word.printDisplay();													/* Skriver ut det dolda ordet eller bokstäver som korrekt gissats */
			
			menuPrint();												
			
			menuSelection();														/* Tar emot input från spelare som står för menyval */
			
			
			if(GameMethods.wrongAnswers >= 10) {									/* Spelet förlorat om felaktiga svar överstiger 10 */
				
				GameMethods.hangedManPrint();
				quitGame = true;
				failPrint();											
				word.printGuessed();
				word.printDisplay();
			}
		}
		
		
		if(GameMethods.wrongAnswers < 10 && GameMethods.wrongAnswers >= 0) { 		/* Spelet vunnet om felaktiga svar färre än 10 större än 0 */
			
			GameMethods.hangedManPrint();
			winPrint();													
			attemptsPrint();
			word.printGuessed();
			word.printDisplay();
		}

	}
	
	
	
	
	
	
	public static void winPrint() {
		System.out.println("\nYou have won the game!");
	}
	
	
	public static void failPrint() {
		System.out.println("\nYou have lost the game! The correct word was: " + word.getWord());
	}
	
	
	public static void attemptsPrint() {
		System.out.println("\nATTEMPTS LEFT: " + (10 - GameMethods.wrongAnswers));
	}

	
	public static void welcomePrint() {

		System.out.println("WELCOME TO HANGMAN\n"
				+ "Try to guess the secret word. "
				+ "You have 10 attempts at guessing the letters in the word. "
				+ "\nIf you wrongly guess the whole word it will cost you 2 attempts. "
				+ "\nEnter your coice from the menu below.");
	}

	
	public static void menuPrint() {

		System.out.println("\n\n1 - Guess letter\n" + "2 - Guess word\n" + "3 - Show word(for testing)\n" + "4 - Quit game");
		System.out.print("\nEnter choice: ");

	}

	
	/* För val i menyn
	 * 
	 * while	körs till input korrekt inmatad
	 * 
	 * try 		försöker omvandla inmatad sträng till int
	 * 
	 * switch 	testar om inmatad int matchar något av alternativen. Om inte(default) får spelaren mata in på nytt*/
	public static void menuSelection() {

		boolean waitingIn = true;
		int menuChoice = 0;
		char tempChar;
		String tempString;
		

		while (waitingIn) {

			try {
				menuChoice = Integer.parseInt(reader.nextLine());
			} catch (NumberFormatException e) {

			}

			switch (menuChoice) {

			case 1: 														/* 1. Gissa en bokstav */
				tempChar = GameMethods.inputLetter();
				quitGame = word.guessLetter(tempChar);						/* Returnerar false tills alla bokstäver/ordet har korrekt gissats */
				waitingIn = false;
				break;
			
			case 2: 														/* 2. Gissa ordet */
				tempString = GameMethods.inputWord();
				quitGame = word.guessWord(tempString);						/* Returnerar false tills ordet har korrekt gissats */
				waitingIn = false;
				break;
			
			case 3: 														/* 3. Visa ordet */
				show_Word = true;
				waitingIn = false;
				break;
			
			case 4:															/* 4. Avsluta spelet */
				quitGame = true;
				waitingIn = false;
				GameMethods.wrongAnswers = -1;								/* Sätter värdet på felaktiga svar till -1 så text för vinst hoppas över */
				break;
			
			default:
				System.out.println("\nYour input must be a number between 1-3\n");

			}
		}
	}

}
