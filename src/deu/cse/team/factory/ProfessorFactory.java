package deu.cse.team.factory;
/**
 *
 * @author eotkd
 */
public class ProfessorFactory implements AccountFactory{

   @Override
    public Allowed createAllowed() {
        return new AllowedOk();
    }

   @Override
    public Division createDivision() {
        return new DivisionProfessor();
    }
}