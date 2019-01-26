package za.co.awesomatic.tdd;

import za.co.awesomatic.tdd.vo.ValidationData;
import za.co.awesomatic.tdd.vo.ValidationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RuleBasedPasswordValidator implements PasswordValidator {
    private List<ValidationRule> rules = new ArrayList<ValidationRule>() {{
        add(new RegexValidationRule(".*[A-Z].*", "Password should have at least one uppercase character"));
        add(new RegexValidationRule(".*[a-z].*", "Password should have at least one lowercase character"));
        add(new RegexValidationRule(".*[0-9].*", "Password should have at least one digit"));
        add(new RegexValidationRule(".*[!\"#$%&'()*+,-.\\:;<=>?@[/]^_`{|}~].*", "Password should have at least one special character"));
        add(new RegexValidationRule(".{12,}", "Password should be at least 12 characters long"));
        add(new RegexValidationRule("\\S+$", "Password should not have any whitespaces"));
    }};

    @Override
    public ValidationResult validate(final ValidationData validationData) {
        List<String> messages = rules.stream().filter(rule -> rule.test(validationData))
                                              .map(ValidationRule::getMessage)
                                              .collect(Collectors.toList());
        return new ValidationResult(messages.isEmpty(), messages);
    }
}
