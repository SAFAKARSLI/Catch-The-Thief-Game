package temp;

import java.util.ArrayList;

public class Thief extends Police{

	private int frame = (int) Math.floor(Math.random() * 100) + 1;
	private int whereToHide = 0;
	private ArrayList<Integer> allFrames= new ArrayList<Integer>();
	
	
	
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
		allFrames.add(frame);
	}

	public void move() {
		ArrayList<Integer> neighFrames = neighNumbers(getFrame());
		int newFrame = neighFrames.get((int) Math.floor(Math.random() * neighFrames.size()));
		allFrames.add(newFrame);
		setFrame(newFrame);
	}
	
	
	public boolean hide() {
		
		int hide = (int) Math.round(Math.random());
		if(hide == 0) {
			return false;
		}
		setWhereToHide(getFrame());
		return true;
	}
}
