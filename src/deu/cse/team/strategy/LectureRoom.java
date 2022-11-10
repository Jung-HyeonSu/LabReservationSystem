package deu.cse.team.strategy;

public abstract class LectureRoom {
	protected AllowedBehavior allowedBehavior;
        protected int classNumber;
	public abstract String display();
        
        public void setAllowedBehavior(AllowedBehavior ab) {
		allowedBehavior = ab;
	}

	public String performAllowed() {
		return allowedBehavior.allowed();
	}

        public void setClassNumber(int classNumber) {
            this.classNumber = classNumber;
        }
        
	
}
