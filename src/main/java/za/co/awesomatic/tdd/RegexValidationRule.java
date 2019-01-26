package za.co.awesomatic.tdd;

import za.co.awesomatic.tdd.vo.ValidationData;

public class RegexValidationRule implements ValidationRule {
    private final String regex;
    private final String message;

    public RegexValidationRule(final String regex, final String message) {
        this.regex = regex;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean test(ValidationData validationData) {
        return !validationData.getPassword().matches(regex);
    }
}
