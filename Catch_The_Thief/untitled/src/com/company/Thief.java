package com.company;

import java.util.ArrayList;

public class Thief extends Police{

    private int frame;
    private int whereToHide = 0;
    private ArrayList<Integer> allFrames= new ArrayList<Integer>();
    private boolean isHidden= false;

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getFrame() {
        return this.frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public ArrayList<Integer> getAllFrames() {
        return allFrames;
    }

    public void setAllFrames(ArrayList<Integer> allFrames) {
        this.allFrames = allFrames;
    }


    public int getWhereToHide() {
        return whereToHide;
    }

    public void setWhereToHide(int whereToHide) {
        this.whereToHide = whereToHide;
    }

    public Thief(int frame) {
        super(frame);
        this.frame = frame;
    }

    public void move() {
        hide();
        ArrayList<Integer> neighFrames = neighNumbers(getFrame());
        int newFrame = neighFrames.get((int) Math.floor(Math.random() * neighFrames.size()));
        if(this.allFrames.contains(newFrame)){
            move();
        }
        else {
            this.allFrames.add(getFrame());
            setFrame(newFrame);
        }
    }


    public void hide() {
        setHidden(true);
        int hide = (int) Math.round(Math.random());
        if(hide == 1) {
            setWhereToHide(getFrame());
        }
    }

    //Checks if there is any cops around preferred frame
    public void isAnyCop(int thiefFrame, int cop1Frame, int cop2Frame) {
        ArrayList<Integer> array1 = neighNumbers(cop1Frame);
        ArrayList<Integer> array2 = neighNumbers(cop2Frame);
        if(array1.contains(thiefFrame)) {
            System.out.println("(RED FRAME) The thief stepped onto the Player 1's range!!!");
        }
        if(array2.contains(thiefFrame)){
            System.out.println("(RED FRAME) The thief stepped onto the Player 2's range!!!");
        }

    }
}