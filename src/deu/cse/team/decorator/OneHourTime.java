package deu.cse.team.decorator;
 
public class OneHourTime extends TimeDecorator {
	public OneHourTime(Time time) {
		this.time = time;
	}
 
	public String getDescription() {
		return time.getDescription() + ", 1시간";
	}
 
	public int time() {
		return 60 + time.time();
	}
}
