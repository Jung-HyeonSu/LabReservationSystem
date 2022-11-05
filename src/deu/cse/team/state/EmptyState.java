package deu.cse.team.state;

public class EmptyState implements State {
    SeatChecking seatchecking;
 
    public EmptyState(SeatChecking seatchecking) {
        this.seatchecking = seatchecking;
    }
 
	@Override
	public String using() {
		return "use";
        }
 
    @Override
	public String empty() {
		return "empty";
	}
        
         public String toString() {
		return "this seat is empty";
	}
}
