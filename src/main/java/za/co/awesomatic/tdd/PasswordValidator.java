package za.co.awesomatic.tdd;

import za.co.awesomatic.tdd.vo.ValidationData;
import za.co.awesomatic.tdd.vo.ValidationResult;

public interface PasswordValidator {
    ValidationResult validate(ValidationData validationData);
}
