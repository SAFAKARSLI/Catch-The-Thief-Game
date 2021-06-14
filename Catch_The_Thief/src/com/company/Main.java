package com.company;


import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    //	Use the link below for a better experience
    //  https://www.okhool.com/image/catalog/okhool-egitim-blog/yuzluk-tablo-ornek-ne-ise-yarar-1-sinif.jpg

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWELCOME TO 'CATCH THE THIEF' GAME\n"
                + "   \nIn this game, you and your friend are the police. When you were on guard duty, you encounter a crime. There is a\n"
                + "thief who stole jewelry from a woman and ran away. You two dedicated cops are going after the thief as you\n"
                + "are supposed to... And suddenly the thief disappears on wheat field. You are jumping into the field and looking for the thief. "
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



        //Game starts
        while(true){
            int lap = 1;

            //Deciding initial frames

            System.out.println("\nName of Player 1:");
            String name1 = scanner.nextLine();
            System.out.println("\nName of Player 2:");
            String name2 = scanner.nextLine();
            System.out.println("\n-Round 1- (Catch The Thief)\n\n"
                    + "You are only able the start from any corner. But thief can be anywhere...");
            int start1 = isCorner(name1);
            int start2 = isCorner(name2);

            //Creating players
            Police police1 = new Police(start1,name1);
            Police police2 = new Police(start2,name2);

            Thief thief = new Thief(randomInitialFrame());
            System.out.println("\n"+police1.getName()+" started from --> " +start1
                    + "\n"+police2.getName()+" started from --> "+ start2);

            //Round 1/0_
            System.out.println("\nDuring the game you'll encounter with the terms of such as 'Yellow Frame', 'Red Frame' and 'White Frame: " +
                    "\nYellow Frame = That frame had been visited by thief before. The game will let you know what are those." +
                    "\nRed Frame = The thief is somewhere around you." +
                    "\nWhite Frame = No need to worries.");

            while(true) {

                System.out.println("\n\nRound 1/0"+lap);
                lap++;
                //Thief's move
                System.out.println("\nThief moved from " + thief.getFrame()+ "nd frame.");
                thief.move();
                thief.isAnyCop(thief.getFrame(), police1, police2);
                //If thief won
                if(thief.getFrame()==police1.getFrame() || thief.getFrame()==police2.getFrame()) {
                    System.out.println("\nIIIIIIIIIIIIIIIIIIIII\nThief put you two clumsy cops to sleep and ran away. He never ever seen again...");
                    System.out.println("You loose the game.");
                    boolean newGame = again();
                    if(!newGame) {
                        break;
                    }
                }

                //Player 1's move
                printRound(police1,police2,thief.getAllFrames());
                police1.move();
                //If won
                if(thief.getFrame()==police1.getFrame()) {
                    lastShot(thief.getWhereToHide(), thief.getAllFrames(), thief.isHidden());
                    break;
                }
                //Search for thief
                police1.anyRedFrame(police1, thief.getFrame());


                //Player 2's move
                printRound(police1,police2,thief.getAllFrames());
                police2.move();
                if(thief.getFrame()==police2.getFrame()) {
                    lastShot(thief.getWhereToHide(), thief.getAllFrames(), thief.isHidden());
                    break;
                }
                //Search for thief
                police2.anyRedFrame(police2, thief.getFrame());
            }
            boolean newGame = again();
            if(!newGame){
                break;
            }
        }


    }

    public static void printRound(Police police1, Police police2, ArrayList<Integer> thiefFrame) {

        System.out.println("\n--------------------------\n" +
                police1.getName()+"'s Current Frame => "+police1.getFrame()
                +"\n"+police2.getName()+"'s Current Frame => "+police2.getFrame()
                +"\nYellow Frames => "+thiefFrame
                +"\n--------------------------\n");

    }

    public static void lastShot(int jewelries, ArrayList<Integer> allFrames, boolean isHidden) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("GOTCHA!!!\nYou caught the thief. \n");
        if(isHidden){

            System.out.println(allFrames + "\nNow you have one more shot to get higher score...\nThief hid the jewelries where he passed. Make your best shot\n");
            int guess = scanner.nextInt();

            if(guess == jewelries) {
                System.out.println("CONGRATULATIONS!!! You won the game with the possible highest score!!!");
            }
            else {
                System.out.println("Jewelries was hidden at "+jewelries+
                        "\nYour answer was "+guess+
                        "\nYou missed the highest score chance. Congratulations!!! You WON the game...");
            }

        }
        else {
            System.out.println("You caught the thief before he hide the jewelries. That means your high score will be doubled");
        }
    }

    public static boolean again() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you wanna play again?(y/n)");
        String again = scanner.next();
        return !again.equals("n");
    }

    public static int isCorner(String police) {
        Scanner scanner = new Scanner(System.in);
        int answer;
        while(true){

            System.out.println("\n"+police+"'s initial frame...(1-10-91-100) :");
            if(scanner.hasNextInt()){
                answer = scanner.nextInt();
                if((answer == 1) || (answer == 10) || (answer == 91) || (answer == 100)) {
                    break;
                }
            }
            System.out.println("You can only start from any corner. Please provide a valid number!!!");
        }
        return answer;
    }

    public static int randomInitialFrame() {
        int random = (int) Math.floor(Math.random() * 4) + 1;

        switch (random) {
            case 1:
                return 45;
            case 2:
                return 46;
            case 3:
                return 55;
            default:
                return 56;
        }

    }
}