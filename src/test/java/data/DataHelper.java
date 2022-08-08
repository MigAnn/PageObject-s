package data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }
    public static AuthInfo getOtherAuthInfo() {
        return new AuthInfo("petya", "12345");
    }

    @Value
    public static class VerificationCode {
          String code;
    }

    public static VerificationCode getVerificationCodeFor(DataHelper.AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static MoneyTransfer getFirstCardNumber(int amount) {
        return new MoneyTransfer("5559 0000 0000 0001","0",amount);

    }

    public static MoneyTransfer getSecondCardNumber(int amount) {
        return new MoneyTransfer("5559 0000 0000 0002","1",amount);

    }

    @Value
    public static class MoneyTransfer {
        private String cardNumber;
        private String id;
        private int amount;
    }
}