package deu.cse.team.state;

public class NotUse implements State {

    SeatChecking seatchecking;

    public NotUse(SeatChecking seatchecking) {
        this.seatchecking = seatchecking;
    }

    @Override
    public void using() {
        seatchecking.setState(seatchecking.getUsingState());
    }

    @Override
    public void empty() {
        seatchecking.setState(seatchecking.getEmptyState());
    }

    @Override
    public void notuse() {
        seatchecking.getSeatcheckbox().setVisible(false);
        seatchecking.getSeatstatus().setVisible(false);
    }

    public String toString() {
        return "notusing";

    }

    @Override
    public void toset() {
        notuse();
    }
}
