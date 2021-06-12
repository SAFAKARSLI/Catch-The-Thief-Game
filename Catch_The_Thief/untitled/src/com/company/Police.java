package com.company;

import java.util.ArrayList;

public class Police {

    private int frame;
    private ArrayList<Integer> allFrames = new ArrayList<Integer>();


    public Police(int frame) {
        this.frame = frame;
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
    public ArrayList<Integer> neighNumbers(int number) {
        ArrayList<Integer> neighNumbers = new ArrayList<Integer>();

        if(isValid(number-10)) { neighNumbers.add(number-10); }
        if(isValid(number+10)) { neighNumbers.add(number+10); }
        if(isValid(number-11)) { neighNumbers.add(number-11); }
        if(isValid(number+11)) { neighNumbers.add(number+11); }
        if(isValid(number-1))  { neighNumbers.add(number-1);  }
        if(isValid(number+1))  { neighNumbers.add(number+1);  }
        if(isValid(number-9))  { neighNumbers.add(number-9);  }
        if(isValid(number+9))  { neighNumbers.add(number+9);  }

        return neighNumbers;
    }

    public void move(int frame) {
        if(neighNumbers(getFrame()).contains(frame)) {
            allFrames.add(frame);
            setFrame(frame);
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
        return (number >= 10) && ((number - 1) % 10 != 0) && (number % 10 != 0) && (number <= 90);
    }

    public void anyRedFrame(int playerFrame, int thiefFrame) {

        ArrayList<Integer> array = neighNumbers(playerFrame);
        if(array.contains(thiefFrame)){
            System.out.println(">> RED FRAME!!! <<\n");
        }
        else{
            System.out.println(">> (White Frame) No clue or enemy around you... <<\n");
        }
    }
}