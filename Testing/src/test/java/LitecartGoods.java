import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class LitecartGoods {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void litecartGoodsText() {
        driver.get("http://localhost/litecart/en/");
        String nameFromMainPage = driver.findElement(By.cssSelector("#box-campaigns .name")).getText();
        driver.findElement(By.cssSelector("#box-campaigns .name")).click();
        String nameFromDuckPage = driver.findElement(By.cssSelector("h1.title")).getText();
        Assertions.assertEquals(nameFromMainPage, nameFromDuckPage );
    }

    @Test
    public void litecartPriceValues() {
        driver.get("http://localhost/litecart/en/");
        String regularPriceFromMainPage = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getText();
        String salePriceFromMainPage = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getText();
        driver.findElement(By.cssSelector("#box-campaigns .name")).click();
        String regularPriceFromDuckPage = driver.findElement(By.cssSelector(".regular-price")).getText();
        String salePriceFromDuckPage = driver.findElement(By.cssSelector(".campaign-price")).getText();
        Assertions.assertEquals(regularPriceFromMainPage, regularPriceFromDuckPage);
        Assertions.assertEquals(salePriceFromMainPage, salePriceFromDuckPage);
    }

    @Test
    public void litecartRegularPrice() {
        driver.get("http://localhost/litecart/en/");
        // проверяем, что цвет серый на Главной странице
        String grayAttributesFromMainPage = driver.findElement(By.cssSelector("#box-campaigns .regular-price"))
                .getCssValue("color");
        int[] grayColorsFromMainPage = getColor(grayAttributesFromMainPage);
        Assertions.assertTrue(grayColorsFromMainPage[0] == grayColorsFromMainPage[1] &&
                grayColorsFromMainPage[1] == grayColorsFromMainPage[2]);

        //проверяем, что заполнен text-decoration-color на Главной странице
        String ifCrossedRegularPriceMain = driver.findElement(By.cssSelector("#box-campaigns .regular-price"))
                .getCssValue("text-decoration-color");
        Assertions.assertNotEquals(ifCrossedRegularPriceMain, "none");

        driver.findElement(By.cssSelector("#box-campaigns .name")).click();

        // проверяем, что цвет серый на Уточной странице
        String grayAttributesFromDuckPage = driver.findElement(By.cssSelector(".regular-price"))
                .getCssValue("color");
        int[] grayColorsFromDuckPage = getColor(grayAttributesFromDuckPage);
        Assertions.assertTrue(grayColorsFromDuckPage[0] == grayColorsFromDuckPage[1] &&
                grayColorsFromDuckPage[1] == grayColorsFromDuckPage[2]);
        //проверяем, что заполнен text-decoration-color на Уточной странице
        String ifCrossedRegularPriceDuck = driver.findElement(By.cssSelector(".regular-price"))
                .getCssValue("text-decoration-color");
        Assertions.assertNotEquals(ifCrossedRegularPriceDuck, "none");
    }

    @Test
    public void litecartSalePrice() {
        driver.get("http://localhost/litecart/en/");
        // проверяем, что цвет красный на Главной странице
        String redAttributesFromMainPage = driver.findElement(By.cssSelector("#box-campaigns .campaign-price"))
                .getCssValue("color");
        int[] redColorsFromMainPage = getColor(redAttributesFromMainPage);
        Assertions.assertTrue(redColorsFromMainPage[1] ==0 && redColorsFromMainPage[2] ==0);
        //проверяем, что текст жирный на Главной странице
        String ifBoldSalePriceFromMainPage = driver.findElement(By.cssSelector("#box-campaigns .campaign-price"))
                .getCssValue("font-weight");
        Assertions.assertEquals(ifBoldSalePriceFromMainPage, "700");

        driver.findElement(By.cssSelector("#box-campaigns .name")).click();
        // проверяем, что цвет красный на Уточной странице
        String redAttributesFromDuckPage = driver.findElement(By.cssSelector(".campaign-price"))
                .getCssValue("color");
        int[] redColorsFromDuckPage = getColor(redAttributesFromDuckPage);
        Assertions.assertTrue(redColorsFromDuckPage[1] ==0 && redColorsFromDuckPage[2] ==0);

        //проверяем, что текст жирный на Уточной странице
        String ifBoldSalePriceFromDuckPage = driver.findElement(By.cssSelector(".campaign-price"))
                .getCssValue("font-weight");
        Assertions.assertEquals(ifBoldSalePriceFromDuckPage, "700");
    }

    @Test
    public void litecartSizeOfText() {
        driver.get("http://localhost/litecart/en/");
        //на Главной
        String sizeOfRegularPriceFromMainPage = driver.findElement(By.cssSelector("#box-campaigns .regular-price"))
                .getCssValue("font-size");
        String sizeOfSalePriceFromMainPage = driver.findElement(By.cssSelector("#box-campaigns .campaign-price"))
                .getCssValue("font-size");

        Assertions.assertTrue(getSizeOfText(sizeOfRegularPriceFromMainPage) < getSizeOfText(sizeOfSalePriceFromMainPage));

        System.out.println(sizeOfRegularPriceFromMainPage);
        System.out.println(getSizeOfText(sizeOfRegularPriceFromMainPage));

        driver.findElement(By.cssSelector("#box-campaigns .name")).click();
        //на Уточной
        String sizeOfRegularPriceFromDuckPage = driver.findElement(By.cssSelector(".regular-price"))
                .getCssValue("font-size");
        String sizeOfSalePriceFromDuckPage = driver.findElement(By.cssSelector(".campaign-price"))
                .getCssValue("font-size");

        Assertions.assertTrue(getSizeOfText(sizeOfRegularPriceFromDuckPage) < getSizeOfText(sizeOfSalePriceFromDuckPage));
    }


    /**
     * Форматирует String из атрибута color в int [R, G, B]
      * @param colorsString принимает строку (rgba 219, 21, 219, 0)
     * @return возвращает массив int
     */
    public int[] getColor(String colorsString) {
        String[] value = colorsString.replaceAll("[rgba() ]", "").split(",");
        int color[] = new int[3];
        for (int i =0; i < 3; i++) {
            color[i] = Integer.parseInt(value[i]);
        }
        return color;
    }

    /**
     * Форматирует размер текста в float
     * @param size размер в пикселях px
     */
    public float getSizeOfText(String size) {
        size = size.replaceAll("px", "");
        float num = Float.parseFloat(size);
        return num;
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
