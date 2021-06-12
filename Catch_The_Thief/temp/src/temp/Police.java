package temp;

import java.util.ArrayList;

public class Police {
	
	private int frame;
	private ArrayList<Integer> allFrames = new ArrayList<Integer>();
	private boolean cought = false;
	
	
	public Police(int frame) {
		this.frame = frame;
	}
	
	public ArrayList<Integer> getAllFrames() {
		return allFrames;
	}


	public void setAllFrames(ArrayList<Integer> allFrames) {
		this.allFrames = allFrames;
	}


	public boolean isCought() {
		return cought;
	}

	public void setCought(boolean cought) {
		this.cought = cought;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}
	
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

	public boolean move(int frame) {
			if(neighNumbers(getFrame()).contains(frame)) {
				allFrames.add(frame);
				setFrame(frame);
				return true;
			}
			return false;
}
	
	public boolean start(int frame) {
		if(isValid(frame)) {
			setFrame(frame);
			return true;
		}
		return false;
	}
	
	
	public boolean isValid(int number) {
		
		if((number < 10) || ((number-1)%10 == 0) || (number%10 == 0) || (number>90)) {
			return false;
		}
		return true;
	}
	
	public String searchForRedOrYellowFrame(int playerFrame, ArrayList<Integer> enemyFrames) {
		
		ArrayList<Integer> array = neighNumbers(playerFrame);
		if(array.contains(enemyFrames.get(enemyFrames.size()-1))) {
			System.out.println("Red Frame");
			return "red";
		}
		for(int i = 0;i<array.size();i++) {
			if(array.contains(enemyFrames.get(i))) {
				System.out.println("(Yellow Frame) Thief were at "+enemyFrames.get(i));
				return "yellow";
			}
		}
		System.out.println("(White Frame) No clue or enemy around you...");
		return "white";
		
	}
}