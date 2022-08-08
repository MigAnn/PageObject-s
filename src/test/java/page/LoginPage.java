package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private SelenideElement loginToLogIn = $("[data-test-id='login'] input");
    private SelenideElement passwordToLogIn = $("[data-test-id='password'] input");
    private SelenideElement buttonToLogIn = $("[data-test-id='action-login']");
    private SelenideElement errorMessage = $("[data-test-id=error-notification] .notification__content");


    public void openPage() {
        open("http://localhost:9999/");
    }

    public VerificatorPage validLogin(DataHelper.AuthInfo info) {
        loginToLogIn.setValue(info.getLogin());
        passwordToLogIn.setValue(info.getPassword());
        buttonToLogIn.click();
        return new VerificatorPage();
    }
    public void shouldErrorMessage() {
        errorMessage.shouldHave(exactText("Ошибка! Неверно указан логин или пароль"));
    }

}
