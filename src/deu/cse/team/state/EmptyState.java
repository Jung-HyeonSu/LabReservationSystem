package deu.cse.team.state;

public class EmptyState implements State {

    SeatChecking seatchecking;

    public EmptyState(SeatChecking seatchecking) {
        this.seatchecking = seatchecking;
    }

    @Override
    public void using() {
        seatchecking.setState(seatchecking.getUsingState());
    }

    @Override
    public void empty() {
        seatchecking.getSeatcheckbox().setVisible(true);
        seatchecking.getSeatstatus().setVisible(true);
        seatchecking.getSeatcheckbox().setEnabled(true);
        seatchecking.getSeatcheckbox().setSelected(false);
        seatchecking.getSeatstatus().setText("예약가능");
    }

    @Override
    public void notuse() {
        seatchecking.setState(seatchecking.getNotusing());
    }

    public String toString() {
        return "empty";

    }

    @Override
    public void toset() {
        empty();
    }

}
