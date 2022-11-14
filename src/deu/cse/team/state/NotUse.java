package deu.cse.team.state;

public class NotUse implements State {

    SeatChecking seatchecking;

    public NotUse(SeatChecking seatchecking) {
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
        return "notusing";

    }

    @Override
    public void toset() {        
        seatchecking.getSeatcheckbox().setVisible(false);
        seatchecking.getSeatstatus().setVisible(false);
    }
}
