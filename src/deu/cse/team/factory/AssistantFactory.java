package deu.cse.team.factory;
/**
 *
 * @author eotkd
 */
public class AssistantFactory implements AccountFactory{
    @Override
    public Allowed createAllowed() {
        return new AllowedOk();
    }

   @Override
    public Division createDivision() {
       return new DivisionAssiatant();
    }
}