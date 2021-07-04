package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RegistrationTest {

    @Test
    void correctRegistration() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[name=name]").setValue("Василий Пупкин");
        $("[name='phone']").setValue("+79012345678");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));

    }

    @Test
    void incorrectTelNumber() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[name=name]").setValue("Василий Пупкин");
        $("[name='phone']").setValue("+7901234");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible);

    }

    @Test
    void incorrectName() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[name=name]").setValue("flkvkjewfvkj");
        $("[name='phone']").setValue("+79012345678");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible);

    }

    @Test
    void incorrectСity() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("лваомоцу");
        $("[name=name]").setValue("Василий Пупкин");
        $("[name='phone']").setValue("+79012345678");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Доставка в выбранный город недоступна")).shouldBe(visible);

    }
}



