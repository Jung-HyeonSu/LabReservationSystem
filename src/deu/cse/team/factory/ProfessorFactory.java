package deu.cse.team.factory;
/**
 *
 * @author eotkd
 */
public class ProfessorFactory implements AccountFactory{
    String Division = null;
    boolean allow = true;

    @Override
    public Allowed createAllowed() {
        Allowed allowed = new AllowedOk();
        this.allow=allowed.getPermission();
        return new AllowedOk();
    }

   @Override
    public Division createDivision() {
        Division d= new DivisionProfessor();
        this.Division = d.getDivision();
        return new DivisionProfessor();
    }
}