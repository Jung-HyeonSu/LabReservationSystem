package deu.cse.team.decorator;

public abstract class Time {
	String description = "추가된 시간:";
  
	public String getDescription() {
		return description;
	}
 
	public abstract int time();
}
