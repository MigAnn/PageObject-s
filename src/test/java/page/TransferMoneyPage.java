package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferMoneyPage {
    private SelenideElement amountForTransfer = $("[data-test-id='amount'] .input__control");
    private SelenideElement cardNumberSelector = $("[data-test-id='from'] .input__control");
    private SelenideElement buttonTransfer = $("[data-test-id='action-transfer']");
    private SelenideElement error = $("[data-test-id=error-notification]");


    public DashboardPage shouldMoneyInfo(DataHelper.MoneyTransfer moneyTransfer, int amount) {
        amountForTransfer.setValue(String.valueOf(amount));
        cardNumberSelector.setValue(moneyTransfer.getCardNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }
    public void noNegativeBalance(DataHelper.MoneyTransfer moneyTransfer) {
        error.shouldHave(exactText("На карте № " + moneyTransfer.getCardNumber() + " недостаточно средств")).shouldBe(visible);
    }
}