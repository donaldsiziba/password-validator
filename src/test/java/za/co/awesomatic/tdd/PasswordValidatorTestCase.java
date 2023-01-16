package za.co.awesomatic.tdd;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import za.co.awesomatic.tdd.dto.PasswordValidationRequest;
import za.co.awesomatic.tdd.dto.PasswordValidationResponse;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PasswordValidatorTestCase {
    @Parameterized.Parameters(name= "{index}: validate({0})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"userp@ssw0rd", false, new String[] {"Password should have at least one uppercase character"}},
                {"USERP@SSW0RD", false, new String[] {"Password should have at least one lowercase character"}},
                {"UserP@ssword", false, new String[] {"Password should have at least one digit"}},
                {"UserPassw0rd", false, new String[] {"Password should have at least one special character"}},
                {"UserP@sw0rd", false, new String[] {"Password should be at least 12 characters long"}},
                {"User   P@ss w0rd", false, new String[] {"Password should not have any whitespaces"}},
                {"UserP@ssw0rD", true, new String[] {}},
                {"userpasword", false, new String[] {"Password should have at least one uppercase character", "Password should have at least one digit",
                        "Password should have at least one special character", "Password should be at least 12 characters long"}}
        });
    }

    private String password;
    private boolean valid;
    private String[] messages;

    PasswordValidator passwordValidator;

    public PasswordValidatorTestCase(String password, boolean valid, String[] messages) {
        this.password = password;
        this.valid = valid;
        this.messages = messages;
    }

    @Before
    public void setUp() {
        passwordValidator = new PasswordValidator();
    }

    @Test
    public void validatePassword() {
        PasswordValidationResponse result = passwordValidator.apply(new PasswordValidationRequest(password));

        assertEquals(result.isValid(), valid);
        assertArrayEquals(result.getMessages().toArray(), messages);
    }
}
