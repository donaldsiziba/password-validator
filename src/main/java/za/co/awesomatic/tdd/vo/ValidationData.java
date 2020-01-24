package za.co.awesomatic.tdd.vo;

public class ValidationData {
    private String password;

    public ValidationData() {}

    public ValidationData(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
