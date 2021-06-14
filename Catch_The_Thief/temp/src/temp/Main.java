package temp;

import java.util.Scanner;


public class Main {
	
//	Use the link below for a better experience
//	https://www.okhool.com/image/catalog/okhool-egitim-blog/yuzluk-tablo-ornek-ne-ise-yarar-1-sinif.jpg
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	
	System.out.println("WELCOME TO 'CATCH THE THIEF' GAME\n"
			+ "   \nIn this game you and your friend are the police. When you are on guard duty, you encounter with a crime. There is a\n"
			+ "thief who stole jeweleries from a woman and ran away. You two dedicated cops go after the thief as you\n"
			+ "supposed to... And suddenly the thief disappears on wheatfield. You jump into the field and look for the thief. "
			+ "Here is where the game begins...\n\n"
			+ "Game has 2 rounds:\n"
			+ "\n1- Catch the thief\n"
			+ "   We assume that the field is divided by 100 identical squares. You and your friend start from any frame\n"
			+ "you want. You are able to check the squares surrounded you if the thief is hiding there. You\n"
			+ "make your moves one by one. But be carreful. After every 2 moves you made, thief make his move same as you do.\n"
			+ "If thief catchs you before you catch him you lose the game. Sometimes be defansive to make your best shot"
			+ "\n\n2- Find the jewelries\n"
			+ "Successful cops are not just supposed to catch the bad guys but also taking after them. When you catch the thief, you \n"
			+ "don't see the jewelries on him. He must be hid the jewelries somewhere he stepped on. For the getting maximum score'\n"
			+ "you have one last shot to find the jewelries.");

	
	String start ="(12-19-82-89)";
	boolean exit = false;
	//Game starts
	while(!exit) {
		int lap = 1;
		System.out.println("\n-Round 1- (Catch The Thief)\n\n"
				+ "You are only able the start from any corner. But thief can be anywhere but outermost frames...\n"
				+ "Choose where you (Player 1) want to start..."+start);
		int start1 = scanner.nextInt();
		Police police1 = new Police(start1);
		
		//Deciding initial frames
		System.out.println("Choose where you (Player 2) want to start..."+start);
		int start2 = scanner.nextInt();
		Police police2 = new Police(start2);
		Thief thief = new Thief(46);
		System.out.println("\nPlayer 1 started from --> " +start1
				+ "\nPlayer 2 started from --> "+ start2);
		
		//Round 1/0_
		System.out.println("\nDuring the game you'll encounter with the terms of 'Yellow Frame' and 'Red Frame'. Yellow frame means\n"
				+ "if thief were any frame surrounded you. Red frame means thief is on any frame surrounded you. When you encounter with \n"
				+ "the Red frame alert be carreful. If you miss the shot, thief'll be able to win the game...");
		boolean round1Quit = false;
		while(!round1Quit) {
			System.out.println("\n\nRound 1/0"+lap);
			lap++;
			//Thief's move
			System.out.println("\nThief moved from " + thief.getFrame()+ "'s frame.");
			thief.move();
			System.out.println("after thief move");
			if(thief.getFrame()==police1.getFrame() || thief.getFrame()==police2.getFrame()) {
				System.out.println("Thief put you two clumsy cops to sleep and ran away. He never ever seen again...");
				System.out.println("You loose the game."
						+ "\nDo you wanna play again?(y/n)");
				String again = scanner.next();
				if(again == "n") {
					round1Quit=true;
					exit=true;
				}else {
					round1Quit=true;
				}
				
			}
			//Player 1's move
			if(thief.getAllFrames().size()==0) {
				System.out.println("You haven't discovered any yellow frame yet..");
			}
			else {
				System.out.println("Yellow Frames = " + thief.getAllFrames());
			}
			System.out.println("Your current frame (" + police1.getFrame()+")\n"
					+ "Preferable frames => " + police1.neighNumbers(police1.getFrame())
					+ "\nPlayer 1's turn. Where do you wanna check?");
			
			int nextFrame = scanner.nextInt();
			police1.move(nextFrame);
			
			if(thief.getFrame()==police1.getFrame()) {
				System.out.println("Player 1 cought the thief."
						+ "\nYou won the game!!!");
				System.out.println("Do you wanna play again?(y/n)");
				String again = scanner.next();
				if(again.equals("n")) {
					round1Quit=true;
					exit=true;
				}else {
					round1Quit=true;
				}
			}
			int turn = 2;
			//Search for thief
			police1.searchForRedOrYellowFrame(nextFrame, thief.getAllFrames());
			
			
			//Player 2's move
			System.out.println("Yellow Frames = " + thief.getAllFrames() +
					  "\nYour current frame (" + police2.getFrame()+")\n"
					+ "Preferable frames => " + police2.neighNumbers(police2.getFrame())
					+ "\nPlayer 2's turn. Where do you wanna check?");
			nextFrame = scanner.nextInt();
			police2.move(nextFrame);
			if(thief.getFrame()==police2.getFrame()) {
				System.out.println("Player 2 cought the thief."
						+ "\nYou won the game!!!");
				String again = scanner.next();
				if(again == "n") {
					round1Quit=true;
					exit=true;
				}else {
					round1Quit=true;
				}
			}
			
			turn = 1;
			//Search for thief
			police2.searchForRedOrYellowFrame(nextFrame, thief.getAllFrames());
			
			
			
			//Checks if the thief stepped onto the police range...
			if(police1.searchForRedOrYellowFrame(police1.getFrame(), thief.getAllFrames())=="red") {
				System.out.println("The Thief stepped onto the Player 1's range!!!");
			}
			else if(police2.searchForRedOrYellowFrame(police2.getFrame(), thief.getAllFrames()) == "red") {
				System.out.println("The Thief stepped onto the Player 2's range!!!");
			}
		}
	}
	}
	
	
	
	
	
	
}






