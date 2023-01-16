package za.co.awesomatic.tdd.dto;

import java.util.Collections;
import java.util.List;

public class PasswordValidationResponse {
    private final boolean valid;
    private final List<String> messages;

    public PasswordValidationResponse(final boolean valid, final List<String> messages) {
        this.valid = valid;
        this.messages = messages;
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }
}
