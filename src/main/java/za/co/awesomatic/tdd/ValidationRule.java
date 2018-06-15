package za.co.awesomatic.tdd;

import za.co.awesomatic.tdd.vo.ValidationData;

import java.util.function.Predicate;

public interface ValidationRule extends Predicate<ValidationData> {
    String getMessage();
}
