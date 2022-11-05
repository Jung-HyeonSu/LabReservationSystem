package deu.cse.team.factory;
/**
 *
 * @author eotkd
 */
public class AssistantFactory implements AccountFactory{
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
        Division d= new DivisionAssiatant();
        this.Division = d.getDivision();
        return new DivisionAssiatant();
    }
}