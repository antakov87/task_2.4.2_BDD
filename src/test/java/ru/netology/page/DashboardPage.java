package ru.netology.page;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement headingPage = $("[data-test-id=dashboard]");
    private SelenideElement buttonFirstCard = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private SelenideElement buttonSecondCard = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");
    private final SelenideElement buttonReload = $("[data-test-id=\"action-reload\"]");

    public DashboardPage() {
        headingPage.shouldBe(visible);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage transfer(String cardId) {
        $("[data-test-id='" + cardId + "'] [data-test-id=action-deposit]").click();
        return new TransferPage();
    }

    public int getCardBalance(String cardId) {
        return extractBalance($("[data-test-id='" + cardId + "']").getText());
    }
}