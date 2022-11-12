package deu.cse.team.state;

public class Main {

	public static void main(String[] args) {
		SeatChecking seatChecking = new SeatChecking(true);

		System.out.println(seatChecking);

		seatChecking.empty();

		System.out.println(seatChecking);
                
                seatChecking.using();

		System.out.println(seatChecking);
                seatChecking.empty();
                System.out.println(seatChecking);

	}
}
