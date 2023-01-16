package za.co.awesomatic.tdd.dto;

public class PasswordValidationRequest {
    private final String password;

    public PasswordValidationRequest(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
