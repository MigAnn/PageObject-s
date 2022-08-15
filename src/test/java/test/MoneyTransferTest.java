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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoneyTransferTest {
    @BeforeEach
    void openPage() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        loginPage.validLogin(authInfo).validVerify(verificationCode);

    }

    @Test
    void shouldTransferMoneyFromFirstCard() {

        int amount = 10;
        var cardInfo = DataHelper.getFirstCardNumber();

        var dashboard = new DashboardPage();
        int balanceFirstCard = dashboard.getCardBalance("0");
        int balanceSecondCard = dashboard.getCardBalance("1");

        dashboard.changeCard(1).shouldMoneyInfo(cardInfo, amount);
        int finalBalanceFirstCard = dashboard.getCardBalance("0");
        int finalBalanceSecondCard = dashboard.getCardBalance("1");

        assertTrue(finalBalanceFirstCard > 0 && finalBalanceSecondCard > 0);

        assertEquals(finalBalanceFirstCard, (balanceFirstCard - amount));
        assertEquals(finalBalanceSecondCard, (balanceSecondCard + amount));
    }

    @Test
    void shouldTransferMoneyFromSecondCard() {

        int amount = 10;
        var cardInfo = DataHelper.getSecondCardNumber();

        var dashboard = new DashboardPage();
        int finalBalanceFirstCard = dashboard.getCardBalance("1");
        int finalBalanceSecondCard = dashboard.getCardBalance("0");

        dashboard.changeCard(0).shouldMoneyInfo(cardInfo, amount);
        int balanceFirstCard = dashboard.getCardBalance("1");
        int balanceSecondCard = dashboard.getCardBalance("0");

        assertTrue(finalBalanceFirstCard > 0 && finalBalanceSecondCard > 0);

        assertEquals(finalBalanceFirstCard, balanceFirstCard + amount);
        assertEquals(finalBalanceSecondCard, balanceSecondCard - amount);
    }

    @Test
    void shouldTransferNegativeBalance() {
        var cardInfoFirst = DataHelper.getFirstCardNumber();
        var cardInfoSecond = DataHelper.getSecondCardNumber();

        var dashboard = new DashboardPage();
        int amount = (dashboard.getCardBalance("1") + 1000);

        dashboard.changeCard(1).noNegativeBalance(cardInfoFirst);
        int finalBalanceFirstCard = dashboard.getCardBalance("0");
        int finalBalanceSecondCard = dashboard.getCardBalance("1");

        assertTrue(finalBalanceFirstCard > 0 && finalBalanceSecondCard > 0);

        assertEquals(finalBalanceFirstCard, finalBalanceFirstCard + amount);
        assertEquals(finalBalanceSecondCard, finalBalanceSecondCard - amount);

    }
}
