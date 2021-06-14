package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Police {

    private int frame;
    private ArrayList<Integer> allFrames = new ArrayList<Integer>();
    private String name;

    public Police(int frame,String name) {
        this.frame = frame;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getAllFrames() {
        return this.allFrames;
    }

    public void setAllFrames(ArrayList<Integer> allFrames) {
        this.allFrames = allFrames;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    //Returning array that contains all the number of neighbourhood frames
    public ArrayList<Integer> neighNumbers() {
        ArrayList<Integer> neighNumbers = new ArrayList<Integer>();
        if(isValid(this.frame-10)) { neighNumbers.add(this.frame-10); }
        if(isValid(this.frame+10)) { neighNumbers.add(this.frame+10); }

        //Right edge
        if(this.frame % 10 == 0) {
            if(isValid(this.frame+9))  { neighNumbers.add(this.frame+9);  }
            if(isValid(this.frame-1))  { neighNumbers.add(this.frame-1);  }
            if(isValid(this.frame-11)) { neighNumbers.add(this.frame-11); }

        }

        //Left edge
        else if((this.frame -1) % 10 == 0) {
            if(isValid(this.frame-9))  { neighNumbers.add(this.frame-9);  }
            if(isValid(this.frame+1))  { neighNumbers.add(this.frame+1);  }
            if(isValid(this.frame+11)) { neighNumbers.add(this.frame+11); }
        }

        else {
            if(isValid(this.frame-11)) { neighNumbers.add(this.frame-11); }
            if(isValid(this.frame+11)) { neighNumbers.add(this.frame+11); }
            if(isValid(this.frame-1))  { neighNumbers.add(this.frame-1);  }
            if(isValid(this.frame+1))  { neighNumbers.add(this.frame+1);  }
            if(isValid(this.frame-9))  { neighNumbers.add(this.frame-9);  }
            if(isValid(this.frame+9))  { neighNumbers.add(this.frame+9);  }
        }

        return neighNumbers;
    }

    public void move() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your current frame (" + getFrame()+")\n"
                + "Preferable frames => " + neighNumbers() +"\n"+getName()+"'s turn. Where do you wanna check?");
        int nextFrame = scanner.nextInt();
        if(neighNumbers().contains(nextFrame)) {
            setFrame(nextFrame);
        }
        else {
            System.out.println("You can't check "+nextFrame+"'s frame!!!");
            move();
        }

    }

    public boolean start(int frame) {
        if(isValid(frame)) {
            setFrame(frame);
            return true;
        }
        return false;
    }


    public boolean isValid(int number) {
        return (number > 0) && (number < 100);
    }

    public void anyRedFrame(Police police, int thiefFrame) {

        if(police.neighNumbers().contains(thiefFrame)){
            System.out.println(">> RED FRAME!!! <<\n");
        }
        else{
            System.out.println(">> (White Frame) No clue or enemy around you... <<\n");
        }
    }
}