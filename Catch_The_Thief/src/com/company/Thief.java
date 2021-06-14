package com.company;

import java.util.ArrayList;

public class Thief {

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

    public int getWhereToHide() {
        return whereToHide;
    }

    public void setWhereToHide(int whereToHide) {
        this.whereToHide = whereToHide;
    }

    public Thief(int frame) {
        this.frame = frame;
    }

    public void move() {
        Police thief = new Police(this.frame,"thief");
        hide();
        ArrayList<Integer> neighFrames = thief.neighNumbers();
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
        int hide = (int) Math.round(Math.random());
        if(hide == 1) {
            setHidden(true);
            setWhereToHide(getFrame());
        }
    }

    //Checks if there is any cops around preferred frame
    public void isAnyCop(int thiefFrame, Police police1, Police police2) {

        if(police1.neighNumbers().contains(thiefFrame)) {
            System.out.println("(RED FRAME) The thief stepped into the "+police1.getName()+"'s range!!!");
        }
        if(police2.neighNumbers().contains(thiefFrame)){
            System.out.println("(RED FRAME) The thief stepped into the "+ police2.getName() +"'s range!!!");
        }

    }
}