package mock.rest.test;

public class StandardWebContextLoader extends GenericWebContextLoader {

    public StandardWebContextLoader() {
        super("src/main/webapp", false);
    }
}
