
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class LitecartNewUser {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void litecartNewUser() throws InterruptedException {
        String mailAddress = generateRandomString() + "@email.rru";
        String password = "9876543211";
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector("[name='login_form'] a")).click();
        driver.findElement(By.name("firstname")).sendKeys("firstname");
        driver.findElement(By.name("lastname")).sendKeys("lastname");
        driver.findElement(By.name("address1")).sendKeys("address1");
        driver.findElement(By.name("postcode")).sendKeys("35696");
        driver.findElement(By.name("city")).sendKeys("Hoover");

        driver.findElement(By.cssSelector(".selection")).click();
        driver.findElement(By.cssSelector(".select2-search__field")).sendKeys("United States" + Keys.ENTER);
        driver.findElement(By.cssSelector("select[name='zone_code']")).sendKeys("Alaska");
        driver.findElement(By.name("email")).sendKeys(mailAddress);
        driver.findElement(By.name("phone")).sendKeys("+1234567891");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.cssSelector("[name='create_account']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#box-account a[href$='logout']")));

        driver.findElement(By.cssSelector("#box-account a[href$='logout']")).click();

        driver.findElement(By.name("email")).sendKeys(mailAddress);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#box-account a[href$='logout']")));
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

    public String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            char randomChar = (char) ('a' + random.nextInt(26));
            sb.append(randomChar);
        }
        return sb.toString();
    }
}