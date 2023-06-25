import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;


public class LitecartAddNewProduct {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void litecartAddNewProduct() throws InterruptedException {
        String myNewProductName = "myNewProductName_" + generateRandomString();

        String relativePath = "src/main/resources/cat.jpg";
        File file = new File(relativePath);
        String absolutePath = file.getAbsolutePath();

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[alt='My Store']")));

        driver.findElement(By.cssSelector("a[href$='catalog']")).click();
        driver.findElement(By.cssSelector("a[href$='edit_product']")).click();
        driver.findElement(By.xpath("//*[text()=' Enabled']")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("name[en]")).sendKeys(myNewProductName);
        driver.findElement(By.name("code")).sendKeys("089");
        driver.findElement(By.cssSelector("[value='1-3']")).click();
        driver.findElement(By.name("quantity")).sendKeys("800");
        driver.findElement(By.name("new_images[]")).sendKeys(absolutePath);

        driver.findElement(By.cssSelector("a[href='#tab-information']")).click();
        driver.findElement(By.name("manufacturer_id")).sendKeys("ACME Corp.");

        driver.findElement(By.name("keywords")).sendKeys("some keywords");
        driver.findElement(By.name("short_description[en]")).sendKeys("some description");
        driver.findElement(By.name("head_title[en]")).sendKeys("some head_titles");
        driver.findElement(By.name("meta_description[en]")).sendKeys("some meta_descriptions");

        driver.findElement(By.cssSelector("a[href='#tab-prices']")).click();
        Thread.sleep(1000);
        driver.findElement(By.name("purchase_price")).sendKeys("90");
        driver.findElement(By.name("purchase_price_currency_code")).sendKeys("Euros");

        driver.findElement(By.name("save")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[text()='" + myNewProductName + "']"));

    }

    public String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            char randomChar = (char) ('a' + random.nextInt(26));
            sb.append(randomChar);
        }
        return sb.toString();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
