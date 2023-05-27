import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class GoggleTest {
    private WebDriver driver;
    //private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        //wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void newSearch() {
        driver.get("https://google.com");
        driver.findElement(By.name("q")).sendKeys("самый простой тестовый запрос\n");
        //driver.findElement(By.name("btnK")).;
        //wait.until(titleIs("самый простой тестовый запрос - Поиск в Google"));
        //element(byName("q")).setValue("самый простой тестовый запрос").pressEnter();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
