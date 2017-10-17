package validation;

import org.junit.Test;
import security.Password;

import static org.junit.Assert.*;

/**
 * Created by roshanalwis on 8/31/17.
 */
public class PasswordTestCase {
    @Test
    public void passwordTest() throws Exception {
        String password = "123@456aQ";
        String hashedPassword = "lFmqjG8R6FIzQvb8Gx48TnHUu4LGKXRm4AB4J68kn8M=$RGoDth4O4d7UNamg45meCiXg0idnd7ngv91RsFOmJ4k=";
        assertEquals(true, Password.check(password, hashedPassword));
    }
}
