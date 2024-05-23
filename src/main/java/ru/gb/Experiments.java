package ru.gb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Experiments {
    public static void main(String[] args) {
        String pathToChromeDriver = "src\\main\\resources\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        //ChromeOptions options = new ChromeOptions(); // Создаем об-т, который далее передадим в констр. драйвера
        //options.addArguments("--start-maximized");
        //options.addArguments("--headless");
        //WebDriver driver = new ChromeDriver(options);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); // Передача настроек напрямую

        driver.get("https://www.google.com");
        System.out.println("Page title: " + driver.getTitle());

        driver.quit();
    }
}
