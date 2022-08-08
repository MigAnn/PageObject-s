package test;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import page.TransferMoneyPage;

import static data.DataHelper.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyFromFirstCard() {

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        loginPage.validLogin(authInfo).validVerify(verificationCode);

        int amount = 10;
        var cardInfo = DataHelper.getFirstCardNumber(amount);

        var dashboard = new DashboardPage();
        dashboard.changeCard(1).shouldMoneyInfo(cardInfo);
        int balanceFirstCard = dashboard.getCardBalance("0");
        int balanceSecondCard = dashboard.getCardBalance("1");

        assertEquals(balanceFirstCard, balanceFirstCard);
        assertEquals(balanceSecondCard, balanceSecondCard);
    }

    @Test
    void shouldTransferMoneyFromSecondCard() {

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        loginPage.validLogin(authInfo).validVerify(verificationCode);

        int amount = 10000;
        var cardInfo = DataHelper.getFirstCardNumber(amount);

        var dashboard = new DashboardPage();
        dashboard.changeCard(0).shouldMoneyInfo(cardInfo);
        int balanceFirstCard = dashboard.getCardBalance("1");
        int balanceSecondCard = dashboard.getCardBalance("0");

        assertEquals(balanceFirstCard, balanceFirstCard);
        assertEquals(balanceSecondCard, balanceSecondCard);
    }
    @Test
    void shouldTransferNegativebalance() {

        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        loginPage.validLogin(authInfo).validVerify(verificationCode);

        int amount = 100000;
        var cardInfo = DataHelper.getFirstCardNumber(amount);

        var dashboard = new DashboardPage();
        dashboard.changeCard(0).shouldMoneyInfo(cardInfo);
        int balanceFirstCard = dashboard.getCardBalance("1");
        int balanceSecondCard = dashboard.getCardBalance("0");

        assertEquals(balanceFirstCard, balanceFirstCard);
        assertEquals(balanceSecondCard, balanceSecondCard);
    }


}
