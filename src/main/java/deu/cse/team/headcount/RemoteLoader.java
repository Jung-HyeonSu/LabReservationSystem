package deu.cse.team.headcount;

public class RemoteLoader {
 
	public static void main(String[] args) {
		RemoteControl remoteControl = new RemoteControl();
 
		HeadcountConfirm headcountConfirm = new HeadcountConfirm();

  
		IndividualCommand individual = 
				new IndividualCommand(headcountConfirm);
		TeamCommand team = 
				new TeamCommand(headcountConfirm);


 
		remoteControl.setCommand(0, individual, team);
  
		System.out.println(remoteControl);
 
		remoteControl.A_ButtonWasPushed(0);
		remoteControl.B_ButtonWasPushed(0);
	}
}
