import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class GoggleTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @Test
    public void newSearch() {
        driver.get("https://google.com");
        driver.findElement(By.name("q")).sendKeys("selenium" + Keys.ENTER);
//        driver.findElement(By.name("q")).sendKeys("selenium");
//        driver.findElement(By.name("btnK")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
