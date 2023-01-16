package za.co.awesomatic.tdd;

import za.co.awesomatic.tdd.dto.PasswordValidationRequest;

import java.util.function.Predicate;

public interface ValidationRule extends Predicate<PasswordValidationRequest> {
    String getMessage();
}
