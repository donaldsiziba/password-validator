package za.co.awesomatic.tdd.vo;

import java.util.Collections;
import java.util.List;

public class ValidationResult {
    private boolean valid;
    private List<String> messages;

    public ValidationResult() {}

    public ValidationResult(final boolean valid, final List<String> messages) {
        this.valid = valid;
        this.messages = messages;
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
