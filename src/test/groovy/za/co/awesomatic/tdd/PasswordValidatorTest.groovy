package za.co.awesomatic.tdd

import spock.lang.Specification
import spock.lang.Unroll
import za.co.awesomatic.tdd.dto.PasswordValidationRequest
import za.co.awesomatic.tdd.dto.PasswordValidationResponse

class PasswordValidatorTest extends Specification {
    PasswordValidator passwordValidator

    def setup() {
        passwordValidator = new PasswordValidator()
    }

    @Unroll
    def "validate the following password - #password"() {
        given: "the following password: #password"

        when: "the password is validated"
        PasswordValidationResponse result = passwordValidator.apply(new PasswordValidationRequest(password, previousPasswords))

        then: "the result should be #valid"
            result.valid == valid

        and: "the following message should be returned to the user: #messages"
            result.messages == messages

        where:
            password           | previousPasswords       || valid | messages
            'userp@ssw0rd'     | [] as Set               || false | ['The password should have at least one uppercase character']
            'USERP@SSW0RD'     | [] as Set               || false | ['The password should have at least one lowercase character']
            'UserP@ssword'     | [] as Set               || false | ['The password should have at least one digit']
            'UserPassw0rd'     | [] as Set               || false | ['The password should have at least one special character']
            'UserP@sw0rd'      | [] as Set               || false | ['The password should be at least 12 characters long']
            'User   P@ss w0rd' | [] as Set               || false | ['The password should not have any whitespaces']
            'UserP@ssw0rD'     | [] as Set               || true  | []
            'userpasword'      | [] as Set               || false | ['The password should have at least one uppercase character','The password should have at least one digit','The password should have at least one special character','The password should be at least 12 characters long']
            'UserP@ssw0rD'     | ['UserP@ssw0rD'] as Set || false | ['The password should not match any of the previous passwords']
    }
}
