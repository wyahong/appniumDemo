package webview;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebViewTest {

    private static AppiumDriver driver;

    @BeforeAll
    static void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName","android");
        desiredCapabilities.setCapability("deviceName","Android6");
        desiredCapabilities.setCapability("appPackage","com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("noReset", "true");
        desiredCapabilities.setCapability("dontStopAppOnReset", "true");
        desiredCapabilities.setCapability("chromedriverExecutable","C:\\Testing\\webdriver\\79\\chromedriver.exe");
        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10,  TimeUnit.SECONDS);
    }

    @Test
    void testTrade(){

        driver.findElement(By.xpath("//*[@text='交易']")).click();

        driver.context(driver.getContextHandles().toArray()[driver.getContextHandles().size()-1].toString());

        driver.findElement(By.cssSelector(".trade_home_info_3aI")).click();

        //切换窗口到开户页
        driver.getWindowHandles().forEach(w -> {
            if(driver.getTitle().equals("平安证券 极速开户"));
                driver.switchTo().window(w);
        });

        driver.findElement(By.cssSelector("#phone-number")).sendKeys("13711119999");
        driver.findElement(By.cssSelector("#code")).sendKeys("1212");
        driver.findElement(By.cssSelector(".btn-submit")).click();
    }

    @AfterAll
    static void tearDown(){
        driver.quit();
    }
}
