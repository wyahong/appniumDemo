package xueqiu;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索
 */
public class SearchPage extends BasePage{

    private By nameLocator = By.id("name");

    public SearchPage(AndroidDriver driver) {
        super(driver);
    }

    public SearchPage search(String keyword){
        sendKeys(By.id("com.xueqiu.android:id/search_input_text"), keyword);
        return this;
    }

    public List<String> getSearchList(){
        List<String> nameList = new ArrayList<>();
        for(MobileElement element : getList(nameLocator)){
            nameList.add(element.getText());
        }
        return nameList;
    }

    public double getPrice(){
        click(nameLocator);
        return Double.valueOf(driver.findElement(By.id("current_price")).getText());
    }
}
