package deu.cse.team.strategy;

/**
 *
 * 
 */
public class Class911 extends LectureRoom{
    
 
    public Class911(){
        allowedBehavior = new AllowedAssistant();

    };
    
    public String display() {
        return "911관리권한";
    };
    
}


