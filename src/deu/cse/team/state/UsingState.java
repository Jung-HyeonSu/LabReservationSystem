package deu.cse.team.state;

public class UsingState implements State {
    SeatChecking gumballMachine;
 
    public UsingState(SeatChecking gumballMachine) {
        this.gumballMachine = gumballMachine;
    }
 
    @Override
	public String using() {
		return ("사용중");
        }
 
    @Override
	public String empty() {
		return ("빈자리");
	}
        
       public String toString() {
		return "this seat is using";
	}

}
