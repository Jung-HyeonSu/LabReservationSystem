package deu.cse.team.state;

public class UsingState implements State {

    SeatChecking seatchecking;

    public UsingState(SeatChecking seatchecking) {
        this.seatchecking = seatchecking;
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
        return "예약완료";
    }

    public void toset() {
        seatchecking.getSeatcheckbox().setEnabled(false);
        seatchecking.getSeatcheckbox().setSelected(false);
        seatchecking.getSeatstatus().setText("예약완료");
        
    }

}
