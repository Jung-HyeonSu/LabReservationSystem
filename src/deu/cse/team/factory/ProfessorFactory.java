package deu.cse.team.factory;
/**
 *
 * @author eotkd
 */
public class ProfessorFactory implements AccountFactory{

   @Override
    public Power createPower() {
        return new PowerOk();
    }

   @Override
    public Division createDivision() {
        return new DivisionProfessor();
    }
}