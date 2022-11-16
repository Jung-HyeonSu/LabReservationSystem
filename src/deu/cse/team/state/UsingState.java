package deu.cse.team.state;

public class UsingState implements State {

    SeatChecking seatchecking;

    public UsingState(SeatChecking seatchecking) {
        this.seatchecking = seatchecking;
    }

    @Override
    public void using() {
        seatchecking.getSeatcheckbox().setVisible(true);
        seatchecking.getSeatstatus().setVisible(true);
        seatchecking.getSeatcheckbox().setEnabled(false);
        seatchecking.getSeatcheckbox().setSelected(false);
        seatchecking.getSeatstatus().setText("예약완료");
    }

    @Override
    public void empty() {
        seatchecking.setState(seatchecking.getEmptyState());
    }

    @Override
    public void notuse() {
        seatchecking.setState(seatchecking.getNotusing());
    }

    public String toString() {
        return "예약완료";
    }

    public void toset() {
        using();
    }
}
