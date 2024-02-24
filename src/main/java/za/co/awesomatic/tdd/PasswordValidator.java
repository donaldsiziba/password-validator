package za.co.awesomatic.tdd;

import za.co.awesomatic.tdd.dto.PasswordValidationRequest;
import za.co.awesomatic.tdd.dto.PasswordValidationResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toCollection;

public class PasswordValidator implements Function<PasswordValidationRequest, PasswordValidationResponse> {
    private final List<ValidationRule> rules = List.of(
        new RegexValidationRule(".*[A-Z].*", "The password should have at least one uppercase character"),
        new RegexValidationRule(".*[a-z].*", "The password should have at least one lowercase character"),
        new RegexValidationRule(".*[0-9].*", "The password should have at least one digit"),
        new RegexValidationRule(".*[^a-zA-Z0-9].*", "The password should have at least one special character"),
        new RegexValidationRule(".{12,}", "The password should be at least 12 characters long"),
        new RegexValidationRule("\\S+$", "The password should not have any whitespaces"),
        new PasswordHistoryValidationRule("The password should not match any of the previous passwords")
    );

    @Override
    public PasswordValidationResponse apply(PasswordValidationRequest request) {
        List<String> messages = rules.stream().filter(rule -> rule.test(request))
                                              .map(ValidationRule::getMessage)
                                              .collect(toCollection(ArrayList::new));
        return new PasswordValidationResponse(messages.isEmpty(), messages);
    }
}
