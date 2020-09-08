/* Game Launcher 
 * v 1.0 
 * 2020-09-07*/

package gamePack;

import java.util.Scanner;

public class GameLauncher {

	public static boolean 		quitGame = false;									/* Om spelet ska avslutas */

	public static boolean		show_Word = false;									/* Om ordet ska visas f�r spelaren eller ej */

	public static Scanner		reader = new Scanner(System.in);

	public static WordHandler 	word = new WordHandler();							/* Objekt ur WordHandler klass. Inneh�ller ord att gissa och sk�ter ordrelaterade saker */

	
	
	
	public static void main(String[] args) {

		word.setWord();																/* Ordet som ska gissas v�ljs ut */
		
		welcomePrint();																/* V�lkomst-text */

		
		while (!quitGame) {															/* Sant om avslutas/omg�ngen vunnen/f�rlorad */
			
			GameMethods.hangedManPrint();											/* Bild p� h�ngd gubbe */
			
			if (show_Word) {														/* Om spelaren v�ljer att ordet ska visas */
				System.out.println("\nThe word is: " + word.getWord());
			}
			
			attemptsPrint();														/* antal f�rs�k kvar */
			
			word.printGuessed();													/* felaktiga gissningar */
			
			word.printDisplay();													/* Skriver ut det dolda ordet eller bokst�ver som korrekt gissats */
			
			menuPrint();												
			
			menuSelection();														/* Tar emot input fr�n spelare som st�r f�r menyval */
			
			
			if(GameMethods.wrongAnswers >= 10) {									/* Spelet f�rlorat om felaktiga svar �verstiger 10 */
				
				GameMethods.hangedManPrint();
				quitGame = true;
				failPrint();											
				word.printGuessed();
				word.printDisplay();
			}
		}
		
		
		if(GameMethods.wrongAnswers < 10 && GameMethods.wrongAnswers >= 0) { 		/* Spelet vunnet om felaktiga svar f�rre �n 10 st�rre �n 0 */
			
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

	
	/* F�r val i menyn
	 * 
	 * while	k�rs till input korrekt inmatad
	 * 
	 * try 		f�rs�ker omvandla inmatad str�ng till int
	 * 
	 * switch 	testar om inmatad int matchar n�got av alternativen. Om inte(default) f�r spelaren mata in p� nytt*/
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
				quitGame = word.guessLetter(tempChar);						/* Returnerar false tills alla bokst�ver/ordet har korrekt gissats */
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
				GameMethods.wrongAnswers = -1;								/* S�tter v�rdet p� felaktiga svar till -1 s� text f�r vinst hoppas �ver */
				break;
			
			default:
				System.out.println("\nYour input must be a number between 1-3\n");

			}
		}
	}

}
