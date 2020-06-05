package xueqiu;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    AndroidDriver driver;
    WebDriverWait wait;

    public BasePage(){

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "127.0.0.1:7555");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        desiredCapabilities.setCapability("resetKeyboard", true);
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("recreateChromeDriverSessions", true);

        URL remoteUrl = null;
        try {
            remoteUrl = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        this.driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        wait = new WebDriverWait(this.driver, 20);
    }

    public BasePage(AndroidDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 20);
    }

    public void click(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void click(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

//    public void sendKeys(WebElement element, String keyword){
//        wait.until(ExpectedConditions.visibilityOf(element));
//        element.sendKeys(keyword);
//    }

    public void sendKeys(By by, String keyword){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(keyword);
    }

    public List<MobileElement> getList(By by){
        List<MobileElement> elements = driver.findElements(by);
        return elements;
    }

    public String getText(By by){
        return driver.findElement(by).getText();
    }

    public boolean isExist(By by){
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void quit(){
        driver.quit();
    }

}
