//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.Selenide;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.junit.After;
//import org.junit.Before;
//
//public abstract class BaseTest {
//    public void setUp(){
//        WebDriverManager.chromedriver().setup();
//        Configuration.browser = "chrome";
//        Configuration.browserSize = "1920x1080";
//        Configuration.headless = false;//конфигурация для Jenkins, создает виртуальный экран
//    }
//    @Before
//    public void init(){
//        setUp();
//    }
//
//    @After
//    public void tearDown(){
//        Selenide.closeWebDriver();
//    }
//
//}
