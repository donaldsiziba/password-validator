package za.co.awesomatic.tdd

import spock.lang.Specification
import spock.lang.Unroll
import za.co.awesomatic.tdd.vo.ValidationData
import za.co.awesomatic.tdd.vo.ValidationResult

class PasswordValidatorTest extends Specification {
    RuleBasedPasswordValidator passwordValidator

    def setup() {
        passwordValidator = new RuleBasedPasswordValidator()
    }

    @Unroll
    def "validate the following password - #password"() {
        given: "the following password: #password"

        when: "the password is validated"
            ValidationResult result = passwordValidator.validate(new ValidationData(password))

        then: "the result should be #valid"
            result.valid == valid

        and: "the following message should be returned to the user: #messages"
            result.messages == messages

        where:
            password           || valid | messages
            'userp@ssw0rd'     || false | ['Password should have at least one uppercase character']
            'USERP@SSW0RD'     || false | ['Password should have at least one lowercase character']
            'UserP@ssword'     || false | ['Password should have at least one digit']
            'UserPassw0rd'     || false | ['Password should have at least one special character']
            'UserP@sw0rd'      || false | ['Password should be at least 12 characters long']
            'User   P@ss w0rd' || false | ['Password should not have any whitespaces']
            'UserP@ssw0rD'     || true  | []
            'userpasword'      || false | ['Password should have at least one uppercase character','Password should have at least one digit','Password should have at least one special character','Password should be at least 12 characters long']
    }
}
