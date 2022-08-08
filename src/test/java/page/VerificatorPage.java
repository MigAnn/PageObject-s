package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.awt.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificatorPage {
    private SelenideElement codeVerification = $("[data-test-id='code'] input");
    private SelenideElement buttonVerification = $("[data-test-id='action-verify']");

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeVerification.setValue(verificationCode.getCode());
        buttonVerification.click();
        return new DashboardPage();
    }
}
