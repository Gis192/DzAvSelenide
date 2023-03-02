package ru.netology;

import org.junit.jupiter.api.Test;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import java.time.Duration;
import static com.codeborne.selenide.Condition.appear;
import com.codeborne.selenide.Condition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class CardDeliveryTest {
    private String sendData;


        public String dataGenerator(int days){
            return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }


    @Test
    void test() {
       sendData = dataGenerator(3);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");

        $("[data-test-id='city'] input").setValue("Ставрополь");
        $("[data-test-id='date'] input").doubleClick().sendKeys(sendData);
        $("[data-test-id='name'] input").setValue("Сергей Роликов");
        $("[data-test-id='phone'] input").setValue("+79993332211");
        $("[data-test-id='agreement']").click();
        $(".button__text").click();
        $("div[data-test-id='notification']").should(appear, Duration.ofSeconds(15)).shouldHave(Condition.text("Успешно!"));
        $("div[data-test-id='notification']").should(appear, Duration.ofSeconds(15)).shouldHave(Condition.text("Встреча успешно забронирована на " + sendData));


    }
}
