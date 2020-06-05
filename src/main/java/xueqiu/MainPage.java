package xueqiu;

import org.openqa.selenium.By;

public class MainPage extends BasePage{

    public MainPage() {
    }

    public SearchPage toSearch(){
        click(By.id("com.xueqiu.android:id/home_search"));
        return new SearchPage(driver);
    }
    public StockPage toStock(){
        click(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TabHost/android.widget.LinearLayout/android.widget.TabWidget/android.widget.RelativeLayout[2]/android.widget.ImageView"));
        return new StockPage(driver);
    }
}
