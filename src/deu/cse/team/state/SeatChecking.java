package deu.cse.team.state;

public class SeatChecking {
 
	State emptyState;
	State usingState;
 
	State state;
	String seatNum;
 
	public SeatChecking(String seatNum) {
		usingState = new UsingState(this);
		emptyState = new EmptyState(this);

		this.seatNum = seatNum;
 		if (seatNum.equals("123123")) {
			state = usingState;
		} else {
			state = emptyState;
		}
	}
        public void using() {
		state = usingState;
	}
 
	public void empty() {
		state = emptyState;
	}
        
	String getSeatNum() {
		return seatNum;
	}

	void setState(State state) {
		this.state = state;
	}

    public State getEmptyState() {
        return emptyState;
    }

    public State getUsingState() {
        return usingState;
    }

    public State getState() {
        return state;
    }
    
    public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("This seat is " + state + "\n");
		return result.toString();
	}
}
