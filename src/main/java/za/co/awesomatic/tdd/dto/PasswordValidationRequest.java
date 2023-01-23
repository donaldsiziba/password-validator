package za.co.awesomatic.tdd.dto;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PasswordValidationRequest {
    private final String password;
    private final Set<String> previousPasswords;

    public PasswordValidationRequest(final String password, Set<String> previousPasswords) {
        this.password = password;
        this.previousPasswords = previousPasswords;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getPreviousPasswords() {
        return Collections.unmodifiableSet(previousPasswords);
    }
}
