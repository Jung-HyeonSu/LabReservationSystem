package deu.cse.team.decorator;
 
public class QuarterTime extends TimeDecorator {
	public QuarterTime(Time time) {
		this.time = time;
	}
 
	public String getDescription() {
		return time.getDescription() + ", 15분";
	}
 
	public int time() {
		return 15 + time.time();
	}
}
