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
        return "empty";

    }

    @Override
    public void toset() {
        seatchecking.getSeatcheckbox().setVisible(true);
        seatchecking.getSeatstatus().setVisible(true);
        seatchecking.getSeatcheckbox().setEnabled(true);
        seatchecking.getSeatcheckbox().setSelected(false);
        seatchecking.getSeatstatus().setText("예약가능");
        System.out.println("reserok");
    }
}
