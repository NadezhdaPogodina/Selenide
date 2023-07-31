package ru.netology.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;


public class DeliveryCardTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }


    @Test
    void shouldDeliverCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Москва");

        String currentDate = generateDate(5, "dd.MM.yyyy");

        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[placeholder='Дата встречи']").setValue(currentDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+89856432137");
        $(By.className("checkbox__box")).click();
        $(By.className("button__text")).click();


        $x("//div[contains(@class, 'notification__content')]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $x("//div[contains(@class, 'notification__content')]").shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));


    }

}

