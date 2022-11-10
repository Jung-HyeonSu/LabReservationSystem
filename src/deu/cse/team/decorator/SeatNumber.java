package deu.cse.team.decorator;

public class SeatNumber extends Time {
  
	public SeatNumber() {
		description = "24";
	}
        
        public SeatNumber(String seat_number) {
		description = seat_number+"번 예약";
	}
  
	public int time() {
		return 0;
	}
}
