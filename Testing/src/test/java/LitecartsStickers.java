import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LitecartsStickers {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void loginPanel() {
        driver.get("http://localhost/litecart/en/");

        List<WebElement> duckList = driver.findElements(By.cssSelector("ul.listing-wrapper >li"));
        for (WebElement element : duckList) {
            List<WebElement> stickerList = element.findElements(By.cssSelector("div.sticker"));
            if (stickerList.size() > 1) {
                System.out.println("Стикеров больше 1");
            } else if (stickerList.size() < 1) {
                System.out.println("Стикеров меньше 1");
            } else if (stickerList.size() ==1) {
                System.out.println("Ровно один стикер");
            }
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
