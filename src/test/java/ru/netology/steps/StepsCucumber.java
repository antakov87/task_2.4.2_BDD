package ru.netology.steps;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import com.codeborne.selenide.Selenide;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferPage;
import ru.netology.page.VerificationPage;
import static org.junit.Assert.assertEquals;

public class StepsCucumber {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static TransferPage transferPage;


    @Пусть ("пользователь залогинен с именем {string} и паролем {string} на странице {string} и ввел проверочный код {string}")
    public void authorizationOnTheWeb(String login, String password, String url, String verificationCode) {
        loginPage = Selenide.open(url, LoginPage.class);
        verificationPage = LoginPage.validLogin(login, password);
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою карту id {string} с главной страницы")
    public void transferOfFunds(int amount, String cardInfo, String idCard) {
        transferPage = dashboardPage.transfer(idCard);
        transferPage.transferMoney(amount, cardInfo);
    }

    @Тогда("баланс его первой карты c id {string} из списка на главной странице должен стать {int} рублей")
    public void resultCard(String idCard, int extractBalance) {
        assertEquals(extractBalance, dashboardPage.getCardBalance(idCard));
    }
}
