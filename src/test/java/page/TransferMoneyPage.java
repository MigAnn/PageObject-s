package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferMoneyPage {
    private SelenideElement amountForTransfer = $("[data-test-id='amount'] .input__control");
    private SelenideElement cardNumberSelector = $("[data-test-id='from'] .input__control");
    private SelenideElement buttonTransfer = $("[data-test-id='action-transfer']");
    private SelenideElement error = $("[data-test-id=error-notification]");


    public DashboardPage shouldMoneyInfo(DataHelper.MoneyTransfer moneyTransfer) {
        amountForTransfer.setValue(String.valueOf(moneyTransfer.getAmount()));
        cardNumberSelector.setValue(moneyTransfer.getCardNumber());
        buttonTransfer.click();
        return new DashboardPage();
    }
    public void checkBalance(DataHelper.MoneyTransfer moneyTransfer) {
        shouldMoneyInfo(moneyTransfer);
        error.shouldHave(exactText("На карте № " + moneyTransfer.getCardNumber() + " недостаточно средств")).shouldBe(visible);
    }
}