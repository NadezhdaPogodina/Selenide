package ru.netology.selenide;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Value;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.annotations.Test;

import javax.xml.xpath.XPath;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static java.awt.SystemColor.text;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        $("notification__content");


        $x("//div[contains(@class, 'notification__content')]").shouldBe(Condition.visible, Duration.ofSeconds(15));
        $x("//div[contains(@class, 'notification__content')]").shouldHave(Condition.exactText("Встреча успешно забронирована на " + currentDate));


    }

}

