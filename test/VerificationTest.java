import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class VerificationTest {
    FileBoardBuilder f = new FileBoardBuilder();
    Board b;
    Verification v = new Verification();
    
    @Test
    public void checkUserCommandDataBaseTest(){
    	//TRUE
    	assertEquals("create", v.checkUserCommandDataBase("create"));
    	assertEquals("list", v.checkUserCommandDataBase("LIsT"));
    	
    	//FALSE
    	assertNotEquals("show", v.checkUserCommandDataBase("hello"));
    	assertNotEquals("create", v.checkUserCommandDataBase("remove"));
    }
    
    @Test
    public void checkPlayerCommandDataBaseTest(){
    	//TRUE
    	assertEquals("create", v.checkPlayerCommandDataBase("create"));
    	assertEquals("list", v.checkPlayerCommandDataBase("LIsT"));
    	
    	//FALSE
    	assertNotEquals("show", v.checkPlayerCommandDataBase("hello"));
    	assertNotEquals("create", v.checkPlayerCommandDataBase("remove"));
    }
}
