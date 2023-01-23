package za.co.awesomatic.tdd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import za.co.awesomatic.tdd.dto.PasswordValidationRequest;
import za.co.awesomatic.tdd.dto.PasswordValidationResponse;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PasswordValidatorTestCase {
    @Parameterized.Parameters(name= "{index}: validate({0})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"userp@ssw0rd", new String[] {}, false, new String[] {"The password should have at least one uppercase character"}},
                {"USERP@SSW0RD", new String[] {}, false, new String[] {"The password should have at least one lowercase character"}},
                {"UserP@ssword", new String[] {}, false, new String[] {"The password should have at least one digit"}},
                {"UserPassw0rd", new String[] {}, false, new String[] {"The password should have at least one special character"}},
                {"UserP@sw0rd", new String[] {}, false, new String[] {"The password should be at least 12 characters long"}},
                {"User   P@ss w0rd", new String[] {}, false, new String[] {"The password should not have any whitespaces"}},
                {"UserP@ssw0rD", new String[] {}, true, new String[] {}},
                {"userpasword", new String[] {}, false, new String[] {"The password should have at least one uppercase character", "The password should have at least one digit",
                        "The password should have at least one special character", "The password should be at least 12 characters long"}},
                {"UserP@ssw0rD", new String[] {"UserP@ssw0rD"}, false, new String[] {"The password should not match any of the previous passwords"}}
        });
    }

    private String password;
    private String[] previousPasswords;
    private boolean valid;
    private String[] messages;

    PasswordValidator passwordValidator;

    public PasswordValidatorTestCase(String password, String[] previousPasswords, boolean valid, String[] messages) {
        this.password = password;
        this.previousPasswords = previousPasswords;
        this.valid = valid;
        this.messages = messages;
    }

    @Before
    public void setUp() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    public void validatePassword() {
        PasswordValidationResponse result = passwordValidator.apply(new PasswordValidationRequest(password, new HashSet<>(Arrays.asList(previousPasswords))));

        assertEquals(result.isValid(), valid);
        assertArrayEquals(result.getMessages().toArray(), messages);
    }
}
