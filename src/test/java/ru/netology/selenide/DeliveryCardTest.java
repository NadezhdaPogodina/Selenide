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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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

        $("[]").setValue(currentDate);
       // $(By.className("input_control")).click();


        String currentDate = generateDate(4, "dd.MM.yyyy");

        $("[placeholder='Дата встречи']").setValue(currentDate);

        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+89856432137");
        $(By.className("checkbox__box")).click();
        $(By.className("button__text")).click();
        $(By.className("notification__content"));

        //  String expected = "Успешно!Ваша встреча успешно забронирована на 12.06.2023";
        $x("//div[contains(@class,'notification_content')]").shouldBe(Condition.visible, Duration.ofSeconds(8));
        $("[data-test-id='notification'] input").shouldHave(Condition.exactText("Ваша встреча успешно забронирована на" + currentDate));
        // String actual = String.valueOf($("[notification='Успешно!Встреча успешно забронирована на 12.06.2023']"));

        // assertEquals(expected, actual);
    }

}



