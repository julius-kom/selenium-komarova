import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddToBasket {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void addToBasket() {

        for (int i=1; i<4; i++) {//Добавляем
            driver.get("http://localhost/litecart/en/");
            driver.findElement(By.cssSelector("#box-most-popular li:first-child")).click();
            driver.findElement(By.name("add_cart_product")).click();
            String countString = String.valueOf(i);
            wait.until(ExpectedConditions.
                    textToBePresentInElement(driver.findElement(By.cssSelector("span.quantity")), countString));
        }
        driver.findElement(By.cssSelector("a[href$='checkout'].link")).click();//переходим в корзину
        
        List<WebElement> items = driver.findElements(By.cssSelector("#order_confirmation-wrapper tr td.item"));//список в таблице
        int lines = items.size();

        for (int i = 0; i < lines; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(By.name("remove_cart_item"))).click();
            wait.until(ExpectedConditions
                    .jsReturnsValue("return document.readyState == 'complete';"));
        }

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[href='http://localhost/litecart/en/']")));

    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
