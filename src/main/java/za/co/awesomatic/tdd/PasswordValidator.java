package za.co.awesomatic.tdd;

import za.co.awesomatic.tdd.dto.PasswordValidationRequest;
import za.co.awesomatic.tdd.dto.PasswordValidationResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toCollection;

public class PasswordValidator implements Function<PasswordValidationRequest, PasswordValidationResponse> {
    private List<ValidationRule> rules = new ArrayList<ValidationRule>() {{
        add(new RegexValidationRule(".*[A-Z].*", "Password should have at least one uppercase character"));
        add(new RegexValidationRule(".*[a-z].*", "Password should have at least one lowercase character"));
        add(new RegexValidationRule(".*[0-9].*", "Password should have at least one digit"));
        add(new RegexValidationRule(".*[^a-zA-Z0-9].*", "Password should have at least one special character"));
        add(new RegexValidationRule(".{12,}", "Password should be at least 12 characters long"));
        add(new RegexValidationRule("\\S+$", "Password should not have any whitespaces"));
    }};

    @Override
    public PasswordValidationResponse apply(PasswordValidationRequest request) {
        List<String> messages = rules.stream().filter(rule -> rule.negate().test(request))
                                              .map(ValidationRule::getMessage)
                                              .collect(toCollection(ArrayList::new));
        return new PasswordValidationResponse(messages.isEmpty(), messages);
    }
}
