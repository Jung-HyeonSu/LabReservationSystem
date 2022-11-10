package deu.cse.team.decorator;
 
public class HalfTime extends TimeDecorator {
	public HalfTime(Time time) {
		this.time = time;
	}
 
	public String getDescription() {
		return time.getDescription() + ", 30분";
	}
 
	public int time() {
		return 30 + time.time();
	}
}
