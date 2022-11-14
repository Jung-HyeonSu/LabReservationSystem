package deu.cse.team.state;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class SeatChecking {

    State emptyState;
    State usingState;
    State notusing;

    public State getNotusing() {
        return notusing;
    }
    JCheckBox seatcheckbox;

    public JCheckBox getSeatcheckbox() {
        return seatcheckbox;
    }

    public JLabel getSeatstatus() {
        return seatstatus;
    }
    JLabel seatstatus;
    State state;
    boolean status;

    public SeatChecking( JCheckBox seatcheckbox, JLabel seatstatus) {
        usingState = new UsingState(this);
        emptyState = new EmptyState(this);
        notusing =  new NotUse(this);
        this.seatcheckbox=seatcheckbox;
        this.seatstatus=seatstatus;
        state = emptyState;
//        if (status) { //예약이면 true
//            state = usingState;
//        } else {
//            state = emptyState;
//        }
    }

    public void using() {
        state = usingState;
    }

    public void empty() {
        state = emptyState;
    }

    public boolean getStatus() {
        return status;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getEmptyState() {
        return emptyState;
    }

    public State getUsingState() {
        return usingState;
    }

    public State getState() {
        return state;
    }

    public void toset() {
        state.toset();
//        System.out.println("complete");

    }

    public String toString(JCheckBox seatcheckbox) {
        StringBuffer result = new StringBuffer();
        result.append(state);
        System.out.println(result.toString());
        return result.toString();
    }
}
