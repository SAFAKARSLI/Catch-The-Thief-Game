package com.company;


import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    //	Use the link below for a better experience
//	    https://www.okhool.com/image/catalog/okhool-egitim-blog/yuzluk-tablo-ornek-ne-ise-yarar-1-sinif.jpg

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWELCOME TO 'CATCH THE THIEF' GAME\n"
                + "   \nIn this game, you and your friend are the police. When you were on guard duty, you encounter a crime. There is a\n"
                + "thief who stole jewelry from a woman and ran away. You two dedicated cops are going after the thief as you\n"
                + "are supposed to... And suddenly the thief disappears on wheatfield. You are jumping into the field and looking for the thief. "
                + "Here is where the game begins...\n\n"
                + "The game has 2 rounds:\n"
                + "\n1- Catch the thief\n"
                + "   We assume that the field is divided by 100 identical squares. You and your friend are able to start from any corner\n"
                + "you want. You are able to check the squares surrounded you whether the thief is hiding there. You\n"
                + "make your moves one by one. But be careful. After every 2 moves you made, the thief makes his move same as you do.\n"
                + "If thief catches you before you catch him you lose the game. Sometimes you may be supposed to be defensive to make your best shot."
                + "\n\n2- Find the jewelries\n"
                + "Successful cops are not just supposed to catch the bad guys but also taking after them. If you can catch the thief before he\n"
                + "hide the jewelry, you will have possible highest score. But if he hid the jewelry, then you need to find where they are.\n" +
                " For the getting maximum score, catch the thief as soon as possible.\n");


        String start ="(12-19-82-89)";
        //Game starts
        while(true){
            int lap = 1;

            //Deciding initial frames
            System.out.println("\n-Round 1- (Catch The Thief)\n\n"
                    + "You are only able the start from any corner. But thief can be anywhere but outermost frames...");

            System.out.println("\nPlayer 1:");
            int start1 = isValidAnswer1();
            Police police1 = new Police(start1);
            System.out.println("\nPlayer 2:");
            int start2 = isValidAnswer1();
            Police police2 = new Police(start2);

            Thief thief = new Thief(46);
            System.out.println("\nPlayer 1 started from --> " +start1
                    + "\nPlayer 2 started from --> "+ start2);

            //Round 1/0_
            System.out.println("\nDuring the game you'll encounter with the terms of such as 'Yellow Frame',  'Red Frame' and 'White Frame: " +
                    "\nYellow Frame = Yellow frames had been visited by thief before. The game'll let you know what are those." +
                    "\nRed Frame = The thief is somewhere around you." +
                    "\nWhite Frame = No need to worries.");

            while(true) {

                System.out.println("\n\nRound 1/0"+lap);
                lap++;
                //Thief's move
                System.out.println("\nThief moved from " + thief.getFrame()+ "nd frame.");
                thief.move();
                thief.isAnyCop(thief.getFrame(), police1.getFrame(), police2.getFrame());
                //If thief won
                if(thief.getFrame()==police1.getFrame() || thief.getFrame()==police2.getFrame()) {
                    System.out.println("\nIIIIIIIIIIIIIIIIIIIII\nThief put you two clumsy cops to sleep and ran away. He never ever seen again...");
                    System.out.println("You loose the game.");
                    boolean newGame = again();
                    if(!newGame) {
                        break;
                    }
                }
                int turn =1;
                //Player 1's move
                printRound(police1.getFrame(),police2.getFrame(),thief.getAllFrames());
                isValidAnswer2(police1,police2,1);
                //If won
                if(thief.getFrame()==police1.getFrame()) {
                    lastShot(thief.getWhereToHide(), thief.getAllFrames(), thief.isHidden());
                    break;
                }
                //Search for thief
                police1.anyRedFrame(police1.getFrame(), thief.getFrame());


                //Player 2's move
                printRound(police1.getFrame(),police2.getFrame(),thief.getAllFrames());
                isValidAnswer2(police1,police2,2);
                if(thief.getFrame()==police2.getFrame()) {
                    lastShot(thief.getWhereToHide(), thief.getAllFrames(), thief.isHidden());
                    break;
                }
                //Search for thief
                police2.anyRedFrame(police2.getFrame(), thief.getFrame());
            }
            boolean newGame = again();
            if(!newGame){
                break;
            }
        }


    }

    public static void printRound(int player1Frame, int player2Frame, ArrayList<Integer> thiefFrame) {

        System.out.println("\n--------------------------\n" +
                "P1 Current Frame => "+player1Frame
                +"\nP2 Current Frame => "+player2Frame
                +"\nYellow Frames => "+thiefFrame
                +"\n--------------------------\n");

    }

    public static boolean lastShot(int jewelries, ArrayList<Integer> allFrames, boolean isHidden) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("GOTCHA!!!\nYou caught the thief. \n");
        if(isHidden){
            System.out.println(allFrames + "\nNow you have one more shot to get higher score...\nThief hid the jewelries where he passed. Make your best shot\n");
            int guess = scanner.nextInt();
            if(guess == jewelries) {
                System.out.println("CONGRATULATIONS!!!. You won the game with the possible highest score!!!");
                return true;
            }
            System.out.println("Jewelries was hidden at "+jewelries+
                    "\nYour answer ("+guess+")");
            System.out.println("You missed the highest score chance. Congratulations!!! You WON the game...");
            return false;

        }
        else {
            System.out.println("You caught the thief before he hide the jewelries. That means your high score will be doubled");
            return true;
        }
    }

    public static boolean again() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you wanna play again?(y/n)");
        String again = scanner.next();
        return !again.equals("n");
    }

    public static int isValidAnswer1() {
        Scanner scanner = new Scanner(System.in);
        int answer;
        while(true){
            answer = 0;
            System.out.println("Choose where you want to start...(12-19-82-89)");
            if(scanner.hasNextInt()){
                answer = scanner.nextInt();
                if((answer == 12) || (answer == 19) || (answer == 82) || (answer == 89)) {
                    break;
                }
            }
            System.out.println("Invalid Answer!!!");
        }

        return answer;
    }

    public static void isValidAnswer2(Police police1, Police police2, int turn){
        Scanner scanner = new Scanner(System.in);
        //If player 1
        if(turn == 1) {
            while(true) {
                System.out.println("Your current frame (" + police1.getFrame()+")\n"
                        + "Preferable frames => " + police1.neighNumbers(police1.getFrame()) +"\nPlayer 1's turn. Where do you wanna check?");
                int nextFrame = scanner.nextInt();
                if(contains(police1.neighNumbers(police1.getFrame()),nextFrame)){
                    police1.move(nextFrame);
                    break;
                }
                System.out.println("Invalid Answer!!!\n");
            }

        }
        //If player 2
        else if(turn ==2) {

            while(true) {
                System.out.println("Your current frame (" + police2.getFrame()+")\n"
                        + "Preferable frames => " + police2.neighNumbers(police2.getFrame()) + "\nPlayer 2's turn. Where do you wanna check?");
                int nextFrame = scanner.nextInt();
                if(contains(police2.neighNumbers(police2.getFrame()),nextFrame)) {
                    police2.move(nextFrame);
                    break;
                }
                System.out.println("Invalid Answer!!!\n");
            }

        }
    }

    public static boolean contains(ArrayList<Integer> array, int answer) {
        if(array.contains(answer)) {
            return true;
        }
        return false;
    }
}