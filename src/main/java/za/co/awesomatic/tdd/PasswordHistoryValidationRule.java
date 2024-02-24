package za.co.awesomatic.tdd;

import za.co.awesomatic.tdd.dto.PasswordValidationRequest;

public class PasswordHistoryValidationRule implements ValidationRule {
    private final String message;

    public PasswordHistoryValidationRule(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public boolean test(PasswordValidationRequest request) {
        return request.getPreviousPasswords().contains(request.getPassword());
    }
}
